package me.anatoliy57.bankmodel.view.log;

import me.anatoliy57.bankmodel.domain.Client;
import me.anatoliy57.bankmodel.util.FormatterMessage;
import me.anatoliy57.bankmodel.view.log.abstraction.ClientsFlowLogger;

/**
 * Implementing interface ClientsFlowLogger for the console
 *
 * @see ClientsFlowLogger
 *
 * @author Udarczev Anatoliy
 */
public class ConsoleClientsFlowLogger implements ClientsFlowLogger {

    /** Prefix for all logs messages */
    public static final String PREFIX = "CLIENT FLOW: ";

    /**
     * @see ClientsFlowLogger#log(Client)
     */
    public void log(Client client) {
        String log = PREFIX;

        log += FormatterMessage.created(client);

        System.out.println(log);
    }
}
