package me.anatoliy57.bankmodel.exceptions;

import lombok.Getter;

/**
 * NegativeCashDesk throw when the amount of cash box is negative after given bank operation
 *
 * @author Udarczev Anatoliy
 */
@Getter
public class NegativeCashBoxException extends BankException {

    /** Amount money in cash box */
    private final int cashDesk;
    /** Bank operation amount of money */
    private final int amount;

    public NegativeCashBoxException(int cashDesk, int amount) {
        super(String.format("Impossible to withdraw %d from cash desk in which %d", amount, cashDesk));

        this.cashDesk = cashDesk;
        this.amount = amount;
    }
}
