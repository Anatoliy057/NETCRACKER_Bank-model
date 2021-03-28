package me.anatoliy57.bankmodel.view.log.abstraction;

import me.anatoliy57.bankmodel.model.CashBox;
import me.anatoliy57.bankmodel.view.log.ConsoleCashBoxLogger;

/**
 * Interface of logger that trace changes cash box
 *
 * @see ConsoleCashBoxLogger
 * @see CashBox
 *
 * @author Udarczev Anatoliy
 */
public interface CashBoxLogger {

    /**
     * Log PUT operation
     *
     * @param src amount money in cash box before operation
     * @param amount amount money of operation
     */
    void logPut(int src, int amount);

    /**
     * Log WITHDRAW operation
     *
     * @param src amount money in cash box before operation
     * @param amount amount money of operation
     */
    void logWithdraw(int src, int amount);
}
