package me.anatoliy57.bankmodel.view;

import me.anatoliy57.bankmodel.view.log.*;
import me.anatoliy57.bankmodel.view.log.abstraction.CashDeskLogger;
import me.anatoliy57.bankmodel.view.log.abstraction.ClientFlowLogger;
import me.anatoliy57.bankmodel.view.log.abstraction.TellerLogger;
import me.anatoliy57.bankmodel.view.log.abstraction.WaitQueueLogger;

public class ConsoleLoggerFactory implements LoggerFactory {

    @Override
    public TellerLogger factoryTellerLogger(long id) {
        return new ConsoleTellerLogger(id);
    }

    @Override
    public CashDeskLogger factoryCashDeskLogger() {
        return new ConsoleCashDeskLogger();
    }

    @Override
    public ClientFlowLogger factoryClientFlowLogger() {
        return new ConsoleClientFlowLogger();
    }

    @Override
    public WaitQueueLogger factoryWaitQueueLogger() {
        return new ConsoleWaitQueueLogger();
    }
}
