package me.anatoliy57.bankmodel.exceptions;

import lombok.Getter;

@Getter
public class NegativePutCashBoxException extends BankException {

    private final int amount;

    public NegativePutCashBoxException(int amount) {
        super("Can not give negative amount money: " + amount);
        this.amount = amount;
    }
}
