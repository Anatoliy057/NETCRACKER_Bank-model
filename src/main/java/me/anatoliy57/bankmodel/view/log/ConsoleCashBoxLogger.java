package me.anatoliy57.bankmodel.view.log;

import me.anatoliy57.bankmodel.util.FormatterMessage;
import me.anatoliy57.bankmodel.enums.TypeOperation;
import me.anatoliy57.bankmodel.view.log.abstraction.CashBoxLogger;

import static me.anatoliy57.bankmodel.enums.TypeOperation.PUT;
import static me.anatoliy57.bankmodel.enums.TypeOperation.WITHDRAW;

/**
 * Implementing interface CashBoxLogger for the console
 *
 * @see CashBoxLogger
 *
 * @author Udarczev Anatoliy
 */
public class ConsoleCashBoxLogger implements CashBoxLogger {

    /** Prefix for all logs messages */
    public static final String PREFIX = "CASH DESK: ";

    /**
     * Log operation on cash box
     *
     * @param src amount money in cash box before operation
     * @param amount amount money of operation
     * @param op type operation
     */
    private void log(int src, int amount, TypeOperation op) {
        String log = PREFIX;

        log += FormatterMessage.cashBoxChanges(src, amount, op);

        System.out.println(log);
    }

    /**
     * @see CashBoxLogger#logPut(int, int)
     */
    public void logPut(int src, int amount) {
        log(src, amount, PUT);
    }

    /**
     * @see CashBoxLogger#logWithdraw(int, int)
     */
    public void logWithdraw(int src, int amount) {
        log(src, amount, WITHDRAW);
    }
}
