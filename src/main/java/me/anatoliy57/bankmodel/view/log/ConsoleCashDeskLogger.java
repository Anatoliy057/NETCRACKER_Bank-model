package me.anatoliy57.bankmodel.view.log;

import me.anatoliy57.bankmodel.domain.FormatterMessage;
import me.anatoliy57.bankmodel.enums.TypeOperation;
import me.anatoliy57.bankmodel.view.log.abstraction.CashDeskLogger;

import static me.anatoliy57.bankmodel.enums.TypeOperation.PUT;
import static me.anatoliy57.bankmodel.enums.TypeOperation.WITHDRAW;

public class ConsoleCashDeskLogger implements CashDeskLogger {

    public static final String PREFIX = "CASH DESK: ";

    private void log(int src, int amount, TypeOperation op) {
        String log = PREFIX;

        log += FormatterMessage.cashDeskChanges(src, amount, op);

        System.out.println(log);
    }

    public void logPut(int src, int amount) {
        log(src, amount, PUT);
    }

    public void logWithdraw(int src, int amount) {
        log(src, amount, WITHDRAW);
    }
}
