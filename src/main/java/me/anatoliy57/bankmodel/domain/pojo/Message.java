package me.anatoliy57.bankmodel.domain.pojo;

import me.anatoliy57.bankmodel.enums.ClientStage;
import me.anatoliy57.bankmodel.domain.values.Client;

public interface Message {

    Client getClient();
    ClientStage getStage();

    static Message created(Client client) {
        return clientMsg(client, ClientStage.CREATED);
    }

    private static Message queueMsg(int queueId, Client client, ClientStage stage) {
        return new ClientInQueue(queueId, client, stage);
    }

    private static Message clientMsg(Client client, ClientStage stage) {
        return new ClientMessage(client, stage);
    }

    static Message gotInQueue(int queueId, Client client) {
        return queueMsg(queueId, client, ClientStage.GOT_IN_QUEUE);
    }

    static Message outOfPriorityQueue(Client client) {
        return clientMsg(client, ClientStage.OUT_OF_PRIORITY_QUEUE);
    }

    static Message rejected(int queueId, Client client) {
        return queueMsg(queueId, client, ClientStage.REJECTED);
    }

    static Message servicing(int queueId, Client client) {
        return queueMsg(queueId, client, ClientStage.SERVICING);
    }

    static Message serviced(int queueId, Client client) {
        return queueMsg(queueId, client, ClientStage.SERVICED);
    }
}
