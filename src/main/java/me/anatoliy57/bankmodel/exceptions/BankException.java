package me.anatoliy57.bankmodel.exceptions;

/**
 * Parent of all application exceptions
 *
 * @see NegativeCashBoxException
 *
 * @author Udarczev Anatoliy
 */
public class BankException extends Exception {

    public BankException() {
    }

    public BankException(String message) {
        super(message);
    }

    public BankException(String message, Throwable cause) {
        super(message, cause);
    }

    public BankException(Throwable cause) {
        super(cause);
    }
}
