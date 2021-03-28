package me.anatoliy57.bankmodel.view.log;

import me.anatoliy57.bankmodel.domain.Client;
import me.anatoliy57.bankmodel.domain.FormatterMessage;
import me.anatoliy57.bankmodel.view.log.abstraction.ClientFlowLogger;

public class ConsoleClientFlowLogger implements ClientFlowLogger {

    public static final String PREFIX = "CLIENT FLOW: ";

    public void log(Client client) {
        String log = PREFIX;

        log += FormatterMessage.created(client);

        System.out.println(log);
    }
}
