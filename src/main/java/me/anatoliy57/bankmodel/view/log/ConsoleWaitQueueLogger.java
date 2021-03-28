package me.anatoliy57.bankmodel.view.log;

import me.anatoliy57.bankmodel.domain.Client;
import me.anatoliy57.bankmodel.util.FormatterMessage;
import me.anatoliy57.bankmodel.enums.ClientWaitStage;
import me.anatoliy57.bankmodel.view.log.abstraction.WaitQueueLogger;

import static me.anatoliy57.bankmodel.enums.ClientWaitStage.ENTER;
import static me.anatoliy57.bankmodel.enums.ClientWaitStage.OUT;

/**
 * Implementing interface TellerLogger for the console
 *
 * @see WaitQueueLogger
 *
 * @author Udarczev Anatoliy
 */
public class ConsoleWaitQueueLogger implements WaitQueueLogger {

    /** Prefix for all logs messages */
    private static final String PREFIX = "WAIT QUEUE: ";

    /**
     * Log client stage in wait queue
     *
     * @param client client in wait queue
     * @param stage client stage in wait queue
     */
    private void log(Client client, ClientWaitStage stage) {
        String log = PREFIX;

        log += switch (stage) {
            case OUT -> FormatterMessage.outFromWaitQueue(client);
            case ENTER -> FormatterMessage.enterInWaitQueue(client);
        };

        System.out.println(log);
    }

    /**
     * @see WaitQueueLogger#logEnter(Client)
     */
    public void logEnter(Client client) {
        log(client, ENTER);
    }

    /**
     * @see WaitQueueLogger#logOut(Client)
     */
    public void logOut(Client client) {
        log(client, OUT);
    }
}
