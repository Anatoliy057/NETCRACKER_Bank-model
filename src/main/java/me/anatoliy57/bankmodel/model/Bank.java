package me.anatoliy57.bankmodel.model;

import lombok.Getter;
import me.anatoliy57.bankmodel.domain.Configuration;
import me.anatoliy57.bankmodel.view.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * The bank class is responsible for initializing all elements of the simulation of the bank works,
 * starting the simulation and stopping it.
 *
 * @see CashBox
 * @see WaitQueue
 * @see ClientsFlow
 * @see Teller
 *
 * @author Udarczev Anatoliy
 */
public class Bank {


    /** Cash box of the bank */
    @Getter
    private final CashBox cashBox;
    /** Waiting queue for clients who cannot be served */
    private final WaitQueue waitQueue;
    /** Clients flow */
    private final ClientsFlow clientsFlow;

    /** List of tellers if bank */
    private final List<Teller> tellers;
    /** List of all threads of bank modeling */
    private final List<Thread> threads;

    /**
     * @param config configuration for bank simulation
     * @param loggerFactory factory of loggers for bank simulation trace
     */
    public Bank(Configuration config, LoggerFactory loggerFactory) {
        waitQueue = new WaitQueue(loggerFactory);

        int n = config.getNumberTellers();
        tellers = new ArrayList<>(n);
        cashBox = new CashBox(config, tellers, waitQueue, loggerFactory);
        for (int i = 0; i < n; i++) {
            tellers.add(new Teller(cashBox, waitQueue, loggerFactory));
        }

        threads = new ArrayList<>();
        clientsFlow = new ClientsFlow(config, tellers, loggerFactory);

        waitQueue.setCashBox(cashBox);
    }

    /**
     * Runs a bank simulation
     */
    public void start() {
        tellers.forEach(t -> threads.add(new Thread(t)));
        threads.add(new Thread(clientsFlow));

        threads.forEach(Thread::start);
    }

    /**
     * Stops a bank simulation
     */
    public void stop() {
        threads.forEach(Thread::interrupt);
    }
}
