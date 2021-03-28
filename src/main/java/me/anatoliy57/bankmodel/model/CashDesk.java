package me.anatoliy57.bankmodel.model;

import lombok.Getter;
import me.anatoliy57.bankmodel.Configuration;
import me.anatoliy57.bankmodel.exceptions.NegativeCashDesk;
import me.anatoliy57.bankmodel.view.LoggerFactory;
import me.anatoliy57.bankmodel.view.log.abstraction.CashDeskLogger;

import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class CashDesk {

    private final List<Teller> listeners;
    private final WaitQueue waitQueue;

    @Getter
    private int cashDesk;
    private CashDeskLogger logger;

    private final ReentrantLock locker;

    public CashDesk(Configuration config, List<Teller> listeners, WaitQueue waitQueue, LoggerFactory loggerFactory) {
        this.listeners = listeners;
        this.waitQueue = waitQueue;

        cashDesk = config.getAmountCashDesk();
        logger = loggerFactory.factoryCashDeskLogger();

        locker = new ReentrantLock();
    }

    public void putCash(int amount) {
        logger.logPut(cashDesk, amount);

        cashDesk += amount;

        if (waitQueue.size() != 0 && waitQueue.size() != 0) {
            listeners.forEach(Teller::wakeup);
        }
    }

    public void withdrawCash(int amount) throws NegativeCashDesk {
        if (!canWithdrawCash(amount)) {
            throw new NegativeCashDesk(cashDesk, amount);
        }

        logger.logPut(cashDesk, amount);

        cashDesk -= amount;
    }

    public boolean canWithdrawCash(int amount) {
        return cashDesk - amount >= 0;
    }

    public void lock() {
        locker.lock();
    }

    public void unlock() {
        locker.unlock();
    }
}
