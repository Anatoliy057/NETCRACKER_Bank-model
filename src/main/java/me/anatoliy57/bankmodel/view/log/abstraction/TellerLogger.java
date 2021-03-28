package me.anatoliy57.bankmodel.view.log.abstraction;

import me.anatoliy57.bankmodel.domain.Client;
import me.anatoliy57.bankmodel.view.log.ConsoleTellerLogger;
import me.anatoliy57.bankmodel.model.Teller;

/**
 * Interface of logger that trace of clients in teller service
 *
 * @see ConsoleTellerLogger
 * @see Teller
 *
 * @author Udarczev Anatoliy
 */
public interface TellerLogger {

    /**
     * Log entering client to queue of teller
     *
     * @param client entered client
     */
    void logEnter(Client client);

    /**
     * Log rejecting client
     *
     * @param client rejected client
     */
    void logRejected(Client client);

    /**
     * Log client being served
     *
     * @param client servicing client
     */
    void logServicing(Client client);

    /**
     * Log the client who was served
     *
     * @param client served client
    */
    void logServiced(Client client);
}
