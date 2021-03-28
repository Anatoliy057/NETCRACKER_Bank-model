package me.anatoliy57.bankmodel.domain;

import lombok.experimental.UtilityClass;
import me.anatoliy57.bankmodel.enums.TypeOperation;

@UtilityClass
public class FormatterMessage {

    public static String gotInQueue(Client client) {
        return String.format("Client %d enter", client.getId());
    }

    public static String created(Client client) {
        return String.format("New %s", client);
    }

    public static String rejected(Client client) {
        return String.format("Client %d rejected", client.getId());
    }

    public static String servicing(Client client) {
        return String.format("Client %d servicing", client.getId());
    }

    public static String serviced(Client client) {
        return String.format("Client %d served", client.getId());
    }

    public static String outFromWaitQueue(Client client) {
        return String.format("Client %d out",client.getId());
    }

    public static String enterInWaitQueue(Client client) {
        return String.format("Client %d enter", client.getId());
    }

    public static String cashDeskChanges(int amount, int src, TypeOperation op) {
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
