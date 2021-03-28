package me.anatoliy57.bankmodel.view.log.abstraction;

public interface CashDeskLogger {

    void logPut(int src, int amount);

    void logWithdraw(int src, int amount);
}
