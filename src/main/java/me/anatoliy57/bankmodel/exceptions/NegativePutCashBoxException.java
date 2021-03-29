package me.anatoliy57.bankmodel.exceptions;

import lombok.Getter;

/**
 * NegativePutCashBoxException throw when putting negative amount money to cash box
 *
 * @author Udarczev Anatoliy
 */
@Getter
public class NegativePutCashBoxException extends BankException {

    /** Negative amount money */
    private final int amount;

    public NegativePutCashBoxException(int amount) {
        super("Can not give negative amount money: " + amount);
        this.amount = amount;
    }
}
