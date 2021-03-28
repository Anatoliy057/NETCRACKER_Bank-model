package me.anatoliy57.bankmodel.view.log;

import me.anatoliy57.bankmodel.domain.Client;
import me.anatoliy57.bankmodel.domain.FormatterMessage;
import me.anatoliy57.bankmodel.enums.ClientWaitStage;
import me.anatoliy57.bankmodel.view.log.abstraction.WaitQueueLogger;

import static me.anatoliy57.bankmodel.enums.ClientWaitStage.ENTER;
import static me.anatoliy57.bankmodel.enums.ClientWaitStage.OUT;

public class ConsoleWaitQueueLogger implements WaitQueueLogger {

    private static final String PREFIX = "WAIT QUEUE: ";

    private void log(Client client, ClientWaitStage stage) {
        String log = PREFIX;

        log += switch (stage) {
            case OUT -> FormatterMessage.outFromWaitQueue(client);
            case ENTER -> FormatterMessage.enterInWaitQueue(client);
        };

        System.out.println(log);
    }

    public void logEnter(Client client) {
        log(client, ENTER);
    }

    public void logOut(Client client) {
        log(client, OUT);
    }
}
