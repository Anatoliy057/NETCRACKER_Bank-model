package me.anatoliy57.bankmodel.model;

import me.anatoliy57.bankmodel.domain.pojo.Message;
import me.anatoliy57.bankmodel.domain.values.Client;
import me.anatoliy57.bankmodel.model.log.Logger;

import java.util.Comparator;
import java.util.Optional;
import java.util.PriorityQueue;

public class WaitQueue {

    private final Logger logger;

    private final PriorityQueue<Client> queue;

    public WaitQueue(Logger logger) {
        this.logger = logger;

        queue = new PriorityQueue<>(Comparator.comparingInt(Client::getAmount));
    }

    public synchronized void addClient(Client client) {
        queue.add(client);
    }

    public synchronized Optional<Client> takeClient(Teller teller) {
        if (queue.isEmpty()) {
            return Optional.empty();
        }
        Client client = queue.peek();
        if (teller.isPossibleToService(client)) {
            logger.log(Message.outOfPriorityQueue(client));
            return Optional.of(queue.poll());
        }
        return Optional.empty();
    }

    public synchronized int size() {
        return queue.size();
    }
}
