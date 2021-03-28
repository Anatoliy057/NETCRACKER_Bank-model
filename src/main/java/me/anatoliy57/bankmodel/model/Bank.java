package me.anatoliy57.bankmodel.model;

import lombok.Getter;
import me.anatoliy57.bankmodel.Configuration;
import me.anatoliy57.bankmodel.view.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class Bank {

    private final Configuration config;
    @Getter
    private final CashDesk cashDesk;
    private final WaitQueue waitQueue;
    private final ClientFlow clientFlow;

    private final List<Teller> tellers;
    private final List<Thread> threads;

    public Bank(Configuration config, LoggerFactory loggerFactory) {
        this.config = config;

        waitQueue = new WaitQueue(loggerFactory);

        int n = config.getNumberTellers();
        tellers = new ArrayList<>(n);
        cashDesk = new CashDesk(config, tellers, waitQueue, loggerFactory);
        for (int i = 0; i < n; i++) {
            tellers.add(new Teller(cashDesk, waitQueue, loggerFactory));
        }

        threads = new ArrayList<>();
        clientFlow = new ClientFlow(config, tellers, loggerFactory);
    }

    public void start() {
        tellers.forEach(t -> threads.add(new Thread(t)));
        threads.add(new Thread(clientFlow));

        threads.forEach(Thread::start);
    }

    public void stop() {
        threads.forEach(Thread::interrupt);
    }
}
