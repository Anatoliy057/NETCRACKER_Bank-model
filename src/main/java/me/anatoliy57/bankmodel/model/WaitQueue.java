package me.anatoliy57.bankmodel.model;

import me.anatoliy57.bankmodel.domain.Client;
import me.anatoliy57.bankmodel.view.LoggerFactory;
import me.anatoliy57.bankmodel.view.log.abstraction.WaitQueueLogger;

import java.util.Comparator;
import java.util.Optional;
import java.util.PriorityQueue;

public class WaitQueue {

    private final WaitQueueLogger logger;

    private final PriorityQueue<Client> queue;

    public WaitQueue(LoggerFactory loggerFactory) {
        logger = loggerFactory.factoryWaitQueueLogger();

        queue = new PriorityQueue<>(Comparator.comparingInt(Client::getAmount));
    }

    public synchronized void addClient(Client client) {
        queue.add(client);
        logger.logEnter(client);
    }

    public synchronized Optional<Client> takeClient(Teller teller) {
        if (queue.isEmpty()) {
            return Optional.empty();
        }

        Client client = queue.peek();
        if (teller.isPossibleToService(client)) {
            logger.logOut(client);
            return Optional.of(queue.poll());
        }
        return Optional.empty();
    }

    public synchronized int size() {
        return queue.size();
    }
}
