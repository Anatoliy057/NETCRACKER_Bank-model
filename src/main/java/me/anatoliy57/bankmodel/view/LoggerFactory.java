package me.anatoliy57.bankmodel.view;

import me.anatoliy57.bankmodel.view.log.abstraction.CashDeskLogger;
import me.anatoliy57.bankmodel.view.log.abstraction.ClientFlowLogger;
import me.anatoliy57.bankmodel.view.log.abstraction.TellerLogger;
import me.anatoliy57.bankmodel.view.log.abstraction.WaitQueueLogger;

public interface LoggerFactory {

    TellerLogger factoryTellerLogger(long id);

    CashDeskLogger factoryCashDeskLogger();

    ClientFlowLogger factoryClientFlowLogger();

    WaitQueueLogger factoryWaitQueueLogger();
}
