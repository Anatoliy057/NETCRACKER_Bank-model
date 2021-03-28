package me.anatoliy57.bankmodel.model;

import me.anatoliy57.bankmodel.domain.Configuration;
import me.anatoliy57.bankmodel.domain.Client;
import me.anatoliy57.bankmodel.enums.TypeOperation;
import me.anatoliy57.bankmodel.exceptions.NegativeCashBoxException;
import me.anatoliy57.bankmodel.view.LoggerFactory;
import me.anatoliy57.bankmodel.view.log.abstraction.CashBoxLogger;

import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Bank cash box
 *
 * @author Udarczev Anatoliy
 */
public class CashBox {

    /** List of tellers for notifying */
    private final List<Teller> listeners;
    /** Waiting queue to check exists waiting clients */
    private final WaitQueue waitQueue;

    /** Amount money in cash box */
    private int cashDesk;
    /** Logger for trace cash box */
    private final CashBoxLogger logger;

    /** Locker for creating transactions above the cash box */
    private final ReentrantLock locker;

    /**
     * @param config configuration for bank simulation
     * @param listeners list of tellers for notifying
     * @param waitQueue waiting queue to check exists waiting clients
     * @param loggerFactory Logger factory
     */
    public CashBox(Configuration config, List<Teller> listeners, WaitQueue waitQueue, LoggerFactory loggerFactory) {
        this.listeners = listeners;
        this.waitQueue = waitQueue;

        cashDesk = config.getAmountCashBox();
        logger = loggerFactory.factoryCashBoxLogger();

        locker = new ReentrantLock();
    }

    /**
     * Put money in the cash box
     *
     * @param amount amount money
     */
    public void putCash(int amount) {
        logger.logPut(cashDesk, amount);

        cashDesk += amount;

        if (!waitQueue.isEmpty()) {
            listeners.forEach(Teller::wakeUp);
        }
    }

    /**
     * Withdraw money from cash box
     *
     * @param amount amount money
     * @throws NegativeCashBoxException if after withdraw money cash box will be less 0
     */
    public void withdrawCash(int amount) throws NegativeCashBoxException {
        if (!canWithdrawCash(amount)) {
            throw new NegativeCashBoxException(cashDesk, amount);
        }

        logger.logWithdraw(cashDesk, amount);

        cashDesk -= amount;
    }

    /**
     * @param client servicing client
     * @return is it possible to service client
     */
    boolean isPossibleToService(Client client) {
        TypeOperation op = client.getType();
        return switch (op) {
            case WITHDRAW -> canWithdrawCash(client.getAmount());
            case PUT -> true;
        };
    }

    /**
     * @param amount amount money
     * @return is it possible withdraw
     */
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
