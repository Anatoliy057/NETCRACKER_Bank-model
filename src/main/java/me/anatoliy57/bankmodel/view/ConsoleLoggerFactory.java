package me.anatoliy57.bankmodel.view;

import me.anatoliy57.bankmodel.view.log.*;
import me.anatoliy57.bankmodel.view.log.abstraction.CashBoxLogger;
import me.anatoliy57.bankmodel.view.log.abstraction.ClientsFlowLogger;
import me.anatoliy57.bankmodel.view.log.abstraction.TellerLogger;
import me.anatoliy57.bankmodel.view.log.abstraction.WaitQueueLogger;

/**
 * Implementing interface LoggerFactory for the console
 *
 * @see ConsoleLoggerFactory
 *
 * @author Udarczev Anatoliy
 */
public class ConsoleLoggerFactory implements LoggerFactory {

    /**
     * @see LoggerFactory#factoryTellerLogger(long)
     */
    @Override
    public TellerLogger factoryTellerLogger(long id) {
        return new ConsoleTellerLogger(id);
    }

    /**
     * @see LoggerFactory#factoryCashBoxLogger()
     */
    @Override
    public CashBoxLogger factoryCashBoxLogger() {
        return new ConsoleCashBoxLogger();
    }

    /**
     * @see LoggerFactory#factoryClientFlowLogger()
     */
    @Override
    public ClientsFlowLogger factoryClientFlowLogger() {
        return new ConsoleClientsFlowLogger();
    }

    /**
     * @see LoggerFactory#factoryWaitQueueLogger()
     */
    @Override
    public WaitQueueLogger factoryWaitQueueLogger() {
        return new ConsoleWaitQueueLogger();
    }
}
