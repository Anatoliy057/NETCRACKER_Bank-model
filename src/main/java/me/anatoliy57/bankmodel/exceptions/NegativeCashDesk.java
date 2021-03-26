package me.anatoliy57.bankmodel.exceptions;

import lombok.Getter;

@Getter
public class NegativeCashDesk extends BankException {

    private final int cashDesk;
    private final int amount;

    public NegativeCashDesk(int cashDesk, int amount) {
        super(String.format("Impossible to withdraw %d from cash desk in which %d", amount, cashDesk));

        this.cashDesk = cashDesk;
        this.amount = amount;
    }
}
