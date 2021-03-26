package me.anatoliy57.bankmodel.model;

import lombok.Getter;
import me.anatoliy57.bankmodel.Configuration;
import me.anatoliy57.bankmodel.domain.values.BankCashDesk;
import me.anatoliy57.bankmodel.model.log.Logger;

import java.util.ArrayList;
import java.util.List;

public class Bank {

    private final Configuration config;
    @Getter
    private final BankCashDesk cashDesk;
    private final WaitQueue waitQueue;
    private final ClientFlow clientFlow;

    private List<Teller> tellers;
    private List<Thread> threads;

    public Bank(Configuration config, Logger logger) {
        this.config = config;

        cashDesk = new BankCashDesk(config);
        waitQueue = new WaitQueue(logger);

        int n = config.getNumberTellers();
        tellers = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            tellers.add(new Teller(i, this, waitQueue, logger));
        }
        threads = new ArrayList<>();

        clientFlow = new ClientFlow(config, tellers, logger);
    }

    public void start() {
        tellers.forEach(t -> threads.add(new Thread(t)));
        threads.add(new Thread(clientFlow));

        threads.forEach(Thread::start);
    }

    public void stop() {
        threads.forEach(Thread::interrupt);
    }

    public void putCash(int amount) {
        cashDesk.putCash(amount);
        if (waitQueue.size() != 0) {
            tellers.forEach(Teller::wakeup);
        }
    }
}
