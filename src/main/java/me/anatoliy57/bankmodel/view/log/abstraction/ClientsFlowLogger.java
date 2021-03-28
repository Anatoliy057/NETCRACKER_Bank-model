package me.anatoliy57.bankmodel.view.log.abstraction;

import me.anatoliy57.bankmodel.domain.Client;
import me.anatoliy57.bankmodel.model.ClientsFlow;
import me.anatoliy57.bankmodel.view.log.ConsoleClientsFlowLogger;

/**
 * Interface of logger that trace the generating of clients
 *
 * @see ConsoleClientsFlowLogger
 * @see ClientsFlow
 *
 * @author Udarczev Anatoliy
 */
public interface ClientsFlowLogger {

    /**
     * Log generated client
     *
     * @param client generated client
     */
    void log(Client client);
}
