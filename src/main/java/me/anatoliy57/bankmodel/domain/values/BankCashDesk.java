package me.anatoliy57.bankmodel.domain.values;

import lombok.Getter;
import me.anatoliy57.bankmodel.Configuration;
import me.anatoliy57.bankmodel.exceptions.NegativeCashDesk;

import java.util.concurrent.locks.ReentrantLock;

public class BankCashDesk {

    @Getter
    private int cashDesk;
    private ReentrantLock locker = new ReentrantLock();

    public BankCashDesk(Configuration config) {
        this.cashDesk = config.getAmountCashDesk();
    }

    public void putCash(int amount) {
        cashDesk += amount;
    }

    public void withdrawCash(int amount) throws NegativeCashDesk {
        if (!canWithdrawCash(amount)) {
            throw new NegativeCashDesk(cashDesk, amount);
        }

        cashDesk -= amount;
    }

    public boolean canWithdrawCash(int amount) {
        return cashDesk - amount >= 0;
    }

    public boolean safelyWithdrawCash(int amount) {
        if (canWithdrawCash(amount)) {
            cashDesk -= amount;
            return true;
        }

        return false;
    }

    public void lock() {
        locker.lock();
    }

    public void unlock() {
        locker.unlock();
    }
}
