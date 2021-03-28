package me.anatoliy57.bankmodel.view;

import me.anatoliy57.bankmodel.view.log.abstraction.CashBoxLogger;
import me.anatoliy57.bankmodel.view.log.abstraction.ClientsFlowLogger;
import me.anatoliy57.bankmodel.view.log.abstraction.TellerLogger;
import me.anatoliy57.bankmodel.view.log.abstraction.WaitQueueLogger;

/**
 * Logger factory for bank simulation elements
 *
 * @see TellerLogger
 * @see CashBoxLogger
 * @see ClientsFlowLogger
 * @see WaitQueueLogger
 *
 */
public interface LoggerFactory {

    /**
     * @param id teller id
     * @return teller logger
     */
    TellerLogger factoryTellerLogger(long id);

    /**
     * @return cash box logger
     */
    CashBoxLogger factoryCashBoxLogger();

    /**
     * @return client flow logger
     */
    ClientsFlowLogger factoryClientFlowLogger();

    /**
     * @return wait queue logger
     */
    WaitQueueLogger factoryWaitQueueLogger();
}
