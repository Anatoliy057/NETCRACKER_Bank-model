package me.anatoliy57.bankmodel.view.log;

import me.anatoliy57.bankmodel.domain.Client;
import me.anatoliy57.bankmodel.util.FormatterMessage;
import me.anatoliy57.bankmodel.enums.ClientServiceStage;
import me.anatoliy57.bankmodel.view.log.abstraction.TellerLogger;

import static me.anatoliy57.bankmodel.enums.ClientServiceStage.*;

/**
 * Implementing interface TellerLogger for the console
 *
 * @see TellerLogger
 *
 * @author Udarczev Anatoliy
 */
public class ConsoleTellerLogger implements TellerLogger {

    /** Prefix for all logs messages */
    private final String PREFIX;

    /**
     * @param id teller id
     */
    public ConsoleTellerLogger(long id) {
        PREFIX = String.format("TELLER %d: ", id);
    }

    /**
     * Log client stage in servicing by teller
     *
     * @param client client of teller
     * @param stage client stage in servicing teller
     */
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

    /**
     * @see TellerLogger#logEnter(Client)
     */
    public void logEnter(Client client) {
        log(client, ENTER);
    }

    /**
     * @see TellerLogger#logRejected(Client)
     */
    public void logRejected(Client client) {
        log(client, REJECTED);
    }

    /**
     * @see TellerLogger#logServicing(Client)
     */
    public void logServicing(Client client) {
        log(client, SERVICING);
    }

    /**
     * @see TellerLogger#logServiced(Client)
     */
    public void logServiced(Client client) {
        log(client, SERVICED);
    }
}
