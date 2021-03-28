package me.anatoliy57.bankmodel.view.log;

import me.anatoliy57.bankmodel.domain.Client;
import me.anatoliy57.bankmodel.domain.FormatterMessage;
import me.anatoliy57.bankmodel.enums.ClientServiceStage;
import me.anatoliy57.bankmodel.view.log.abstraction.TellerLogger;

import static me.anatoliy57.bankmodel.enums.ClientServiceStage.*;

public class ConsoleTellerLogger implements TellerLogger {

    private final String PREFIX;

    public ConsoleTellerLogger(long id) {
        PREFIX = String.format("TELLER %d: ", id);
    }

    private void log(Client client, ClientServiceStage stage) {
        String log = PREFIX;

        log += switch (stage) {
            case ENTER -> FormatterMessage.gotInQueue(client);
            case REJECTED -> FormatterMessage.rejected(client);
            case SERVICING -> FormatterMessage.servicing(client);
            case SERVICED -> FormatterMessage.serviced(client);
        };

        System.out.println(log);
    }

    public void logEnter(Client client) {
        log(client, ENTER);
    }

    public void logRejected(Client client) {
        log(client, REJECTED);
    }

    public void logServicing(Client client) {
        log(client, SERVICING);
    }

    public void logServiced(Client client) {
        log(client, SERVICED);
    }
}
