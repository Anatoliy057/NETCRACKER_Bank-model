package me.anatoliy57.bankmodel.view.log.abstraction;

import me.anatoliy57.bankmodel.domain.Client;
import me.anatoliy57.bankmodel.model.WaitQueue;
import me.anatoliy57.bankmodel.view.log.ConsoleWaitQueueLogger;

/**
 * Interface of logger that trace of clients in wait queue
 *
 * @see ConsoleWaitQueueLogger
 * @see WaitQueue
 *
 * @author Udarczev Anatoliy
 */
public interface WaitQueueLogger {

    /**
     * Log entering client to wait queue
     *
     * @param client entered client
     */
    void logEnter(Client client);

    /**
     * Log outing client from wait queue
     *
     * @param client outed client
     */
    void logOut(Client client);
}
