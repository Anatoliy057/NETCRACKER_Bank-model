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

/**
 * Teller who serves clients from its queue and if it can't service client will redirect him to wait queue
 *
 * @author Udarczev Anatoliy
 */
@Getter
public class Teller implements Runnable {

    /**  Generates a teller ID when it is created */
    private final static IdGenerator idGenerator = new IdGenerator();

    /** Teller id */
    private final long id;

    /** Bank cash box */
    private final CashBox cashBox;
    /** Queue of waiting clients */
    private final WaitQueue waitQueue;

    /** Logger for trace of clients in teller service */
    private final TellerLogger logger;
    /** Queue to teller */
    private final LinkedList<Client> queue;

    /** Object for waiting new clients */
    private final Object waiter = new Object();

    /**
     * @param cashBox bank cash box
     * @param waitQueue queue of waiting clients
     * @param loggerFactory logger factory
     */
    public Teller(CashBox cashBox, WaitQueue waitQueue, LoggerFactory loggerFactory) {
        id = idGenerator.generateId();

        this.waitQueue = waitQueue;
        this.cashBox = cashBox;

        logger = loggerFactory.factoryTellerLogger(id);
        queue = new LinkedList<>();
    }

    /**
     * Add client to queue
     *
     * @param client the client to be added to the queue
     */
    public void addToQueue(Client client) {
        synchronized (queue) {
            logger.logEnter(client);
            queue.addLast(client);
            wakeUp();
        }
    }

    /**
     * Get client from queue
     *
     * @return client that getting from queue
     */
    private Optional<Client> pollClient() {
        synchronized (queue) {
            return Optional.ofNullable(queue.poll());
        }
    }

    /**
     * @return number of clients in the queue
     */
    public int size() {
        synchronized (queue) {
            return queue.size();
        }
    }

    /**
     * The runnable method for thread of servicing clients
     */
    @Override
    public void run() {
        while (true) {
            if (Thread.interrupted()) {
                return;
            }

            Optional<Client> optClient;
            Client currentClient = null;

            // Transaction on cash box
            try {
                cashBox.lock();

                optClient = waitQueue.poll();
                if (optClient.isPresent()) {
                    currentClient = optClient.get();
                    logger.logServicing(currentClient);
                    doOperation(currentClient);

                } else {
                    optClient = pollClient();
                    if (optClient.isPresent()) {
                        currentClient = optClient.get();
                        if (cashBox.isPossibleToService(currentClient)) {
                            logger.logServicing(currentClient);
                            doOperation(currentClient);

                        } else {
                            logger.logRejected(currentClient);
                            waitQueue.put(currentClient);
                            currentClient = null;
                        }
                    }
                }
            } finally {
                cashBox.unlock();
            }

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

    /**
     * Perform client operation
     *
     * @param client client whose operation has been performed
     */
    @SneakyThrows
    private void doOperation(Client client) {
        TypeOperation type = client.getType();
        switch (type) {
            case WITHDRAW -> cashBox.withdrawCash(client.getAmount());
            case PUT -> cashBox.putCash(client.getAmount());
            default -> throw new NullPointerException("Type of operation is null");
        }
    }

    /**
     * Wake up the client for client service
     */
    public void wakeUp() {
        synchronized (waiter) {
            waiter.notify();
        }
    }

    /**
     * @param client served client
     * @throws InterruptedException if thread interrupted
     */
    private void service(Client client) throws InterruptedException {
        Thread.sleep(client.getServiceTime() * 1000);
    }
}
