package me.anatoliy57.bankmodel.model;

import lombok.Getter;
import lombok.SneakyThrows;
import me.anatoliy57.bankmodel.enums.TypeOperation;
import me.anatoliy57.bankmodel.domain.Client;
import me.anatoliy57.bankmodel.util.IdGenerator;
import me.anatoliy57.bankmodel.view.LoggerFactory;
import me.anatoliy57.bankmodel.view.log.abstraction.TellerLogger;

import java.util.LinkedList;
import java.util.Optional;

@Getter
public class Teller implements Runnable {

    private final static IdGenerator idGenerator = new IdGenerator();

    private final long id;

    private final CashDesk cashDesk;
    private final WaitQueue waitQueue;

    private final TellerLogger logger;
    private final LinkedList<Client> queue;

    private final Object waiter = new Object();

    public Teller(CashDesk cashDesk, WaitQueue waitQueue, LoggerFactory loggerFactory) {
        id = idGenerator.generateId();

        this.waitQueue = waitQueue;
        this.cashDesk = cashDesk;

        logger = loggerFactory.factoryTellerLogger(id);
        queue = new LinkedList<>();
    }

    public void addToQueue(Client client) {
        synchronized (queue) {
            logger.logEnter(client);
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
                logger.logServicing(currentClient);
                doOperation(currentClient);

            } else {
                optClient = pollClient();
                if (optClient.isPresent()) {
                    currentClient = optClient.get();
                    if (isPossibleToService(currentClient)) {
                        logger.logServicing(currentClient);
                        doOperation(currentClient);

                    } else {
                        logger.logRejected(currentClient);
                        waitQueue.addClient(currentClient);
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
                    logger.logServiced(currentClient);
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
            case PUT -> cashDesk.putCash(client.getAmount());
            default -> throw new NullPointerException("Type of operation is null");
        }
    }

    private void service(Client client) throws InterruptedException {
        Thread.sleep(client.getServiceTime() * 1000);
    }
}
