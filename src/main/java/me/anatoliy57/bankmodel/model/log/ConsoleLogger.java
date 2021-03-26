package me.anatoliy57.bankmodel.model.log;

import me.anatoliy57.bankmodel.domain.pojo.ClientInQueue;
import me.anatoliy57.bankmodel.domain.pojo.ClientMessage;
import me.anatoliy57.bankmodel.domain.pojo.Message;

public class ConsoleLogger implements Logger {

    @Override
    public void log(Message message) {
        String msg;
        if (message instanceof ClientMessage) {
            ClientMessage clientMessage = (ClientMessage) message;

            msg = String.format("Type: %s, client id: %d, client type: %s",
                    clientMessage.getStage(),
                    clientMessage.getClient().getId(),
                    clientMessage.getClient().getType()
            );
        } else if (message instanceof ClientInQueue) {
            ClientInQueue clientInQueue = (ClientInQueue) message;

            msg = String.format("Queue id: %d, type: %s, client id: %d, client type: %s",
                    clientInQueue.getIdQueue(),
                    clientInQueue.getStage(),
                    clientInQueue.getClient().getId(),
                    clientInQueue.getClient().getType()
            );
        } else {
            msg = "WHAT?";
        }

        System.out.println(msg);
    }
}
