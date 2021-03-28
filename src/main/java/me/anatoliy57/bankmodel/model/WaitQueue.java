package me.anatoliy57.bankmodel.model;

import lombok.AccessLevel;
import lombok.Setter;
import me.anatoliy57.bankmodel.domain.Client;
import me.anatoliy57.bankmodel.view.LoggerFactory;
import me.anatoliy57.bankmodel.view.log.abstraction.WaitQueueLogger;

import java.util.Comparator;
import java.util.Optional;
import java.util.PriorityQueue;

/**
 * The queue of waiting clients who cannot be serviced by the bank yet
 *
 * @author Udarczev Anatoliy
 */
public class WaitQueue {

    /** Bank cash box */
    @Setter(AccessLevel.PACKAGE)
    private CashBox cashBox;

    /** Logger for trace of waiting clients in queue*/
    private final WaitQueueLogger logger;

    /** Queue of waiting clients sorted by operation amount money */
    private final PriorityQueue<Client> queue;

    /**
     * @param loggerFactory logger factory
     */
    public WaitQueue(LoggerFactory loggerFactory) {
        logger = loggerFactory.factoryWaitQueueLogger();

        queue = new PriorityQueue<>(Comparator.comparingInt(Client::getAmount));
    }

    /**
     * Add client to wait queue
     *
     * @param client the client to be added to the wait queue
     */
    public synchronized void put(Client client) {
        queue.add(client);
        logger.logEnter(client);
    }

    /**
     * @return client from wait queue
     */
    public synchronized Optional<Client> poll() {
        Client client = queue.peek();
        if (client != null && cashBox.isPossibleToService(client)) {
            logger.logOut(client);
            return Optional.ofNullable(queue.poll());
        }

        return Optional.empty();
    }

    public synchronized boolean isEmpty() {
        return queue.isEmpty();
    }
}
