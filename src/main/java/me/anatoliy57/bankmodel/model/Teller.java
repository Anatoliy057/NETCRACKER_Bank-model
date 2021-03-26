package me.anatoliy57.bankmodel.model;

import lombok.Getter;
import lombok.SneakyThrows;
import me.anatoliy57.bankmodel.enums.TypeOperation;
import me.anatoliy57.bankmodel.domain.pojo.Message;
import me.anatoliy57.bankmodel.domain.values.BankCashDesk;
import me.anatoliy57.bankmodel.domain.values.Client;
import me.anatoliy57.bankmodel.model.log.Logger;

import java.util.LinkedList;
import java.util.Optional;

@Getter
public class Teller implements Runnable {

    private final int id;
    private final Logger logger;
    private final Bank bank;
    private final BankCashDesk cashDesk;
    private final LinkedList<Client> queue;
    private final WaitQueue waitQueue;

    private final Object waiter = new Object();

    public Teller(int id, Bank bank, WaitQueue waitQueue, Logger logger) {
        this.id = id;
        this.bank = bank;
        this.cashDesk = bank.getCashDesk();
        this.waitQueue = waitQueue;
        this.logger = logger;
        this.queue = new LinkedList<>();
    }

    public void addToQueue(Client client) {
        synchronized (queue) {
            logger.log(Message.gotInQueue(id, client));
            queue.addLast(client);
            wakeup();
        }
    }

    private Optional<Client> pollClient() {
        synchronized (queue) {
            return Optional.ofNullable(queue.poll());
        }
    }

    public int size() {
        synchronized (queue) {
            return queue.size();
        }
    }

    boolean isPossibleToService(Client client) {
        TypeOperation op = client.getType();
        return switch (op) {
            case WITHDRAW -> cashDesk.canWithdrawCash(client.getAmount());
            case PUT -> true;
        };
    }

    @Override
    public void run() {
        while (true) {
            if (Thread.interrupted()) {
                return;
            }

            Optional<Client> optClient;
            Client currentClient = null;

            cashDesk.lock();

            optClient = waitQueue.takeClient(this);
            if (optClient.isPresent()) {
                currentClient = optClient.get();
                doOperation(currentClient);
                logger.log(Message.servicing(id, currentClient));
            } else {
                optClient = pollClient();
                if (optClient.isPresent()) {
                    currentClient = optClient.get();
                    if (isPossibleToService(currentClient)) {
                        doOperation(currentClient);
                        logger.log(Message.servicing(id, currentClient));
                    } else {
                        waitQueue.addClient(currentClient);
                        logger.log(Message.rejected(id, currentClient));
                        currentClient = null;
                    }
                }
            }

            cashDesk.unlock();


            try {
                if (currentClient == null) {
                    synchronized (waiter) {
                        waiter.wait();
                    }
                } else {
                    service(currentClient);
                    logger.log(Message.serviced(id, currentClient));
                }
            } catch (InterruptedException ignored) {
                return;
            }
        }
    }

    public void wakeup() {
        synchronized (waiter) {
            waiter.notify();
        }
    }

    @SneakyThrows
    private void doOperation(Client client) {
        TypeOperation type = client.getType();
        switch (type) {
            case WITHDRAW -> cashDesk.withdrawCash(client.getAmount());
            case PUT -> bank.putCash(client.getAmount());
            default -> throw new NullPointerException("Type of operation is null");
        }
    }

    private void service(Client client) throws InterruptedException {
        Thread.sleep(client.getServiceTime() * 1000);
    }
}
