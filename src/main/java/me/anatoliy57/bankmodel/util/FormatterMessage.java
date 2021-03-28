package me.anatoliy57.bankmodel.util;

import lombok.experimental.UtilityClass;
import me.anatoliy57.bankmodel.domain.Client;
import me.anatoliy57.bankmodel.enums.TypeOperation;

/**
 * Converts data to string messages for logging
 *
 * @author Udarczev Anatoliy
 */
@UtilityClass
public class FormatterMessage {

    /**
     * @param client client that enter in queue of teller
     * @return message for log
     */
    public static String gotInQueue(Client client) {
        return String.format("Client %d enter", client.getId());
    }

    /**
     * @param client client that created in clients flow
     * @return message for log
     */
    public static String created(Client client) {
        return String.format("New %s", client);
    }

    /**
     * @param client client who has been rejected by the teller because a client operation is not yet possible
     * @return message for log
     */
    public static String rejected(Client client) {
        return String.format("Client %d rejected", client.getId());
    }

    /**
     * @param client servicing client by the teller
     * @return message for log
     */
    public static String servicing(Client client) {
        return String.format("Client %d servicing", client.getId());
    }

    /**
     * @param client serviced client already
     * @return message for log
     */
    public static String serviced(Client client) {
        return String.format("Client %d served", client.getId());
    }

    /**
     * @param client client that outed from wait queue
     * @return message for log
     */
    public static String outFromWaitQueue(Client client) {
        return String.format("Client %d out",client.getId());
    }

    /**
     * @param client client that entered in wait queue
     * @return message for log
     */
    public static String enterInWaitQueue(Client client) {
        return String.format("Client %d enter", client.getId());
    }


    /**
     * @param amount amount money
     * @param src cash of cash box
     * @param op operation on the cash box
     * @return message for log
     */
    public static String cashBoxChanges(int src, int amount, TypeOperation op) {
        int res;
        char symbolOp;
        switch (op) {
            case PUT ->  {
                symbolOp = '+';
                res = src + amount;
            }
            case WITHDRAW -> {
                symbolOp = '-';
                res = src - amount;
            }
            default -> throw new NullPointerException("Type operation is null");
        }

        return String.format("%d %s %d = %d", src, symbolOp, amount, res);
    }
}
