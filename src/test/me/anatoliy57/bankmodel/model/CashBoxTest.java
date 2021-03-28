package me.anatoliy57.bankmodel.model;

import me.anatoliy57.bankmodel.domain.Configuration;
import me.anatoliy57.bankmodel.exceptions.NegativeCashBoxException;
import me.anatoliy57.bankmodel.exceptions.NegativePutCashBoxException;
import me.anatoliy57.bankmodel.view.ConsoleLoggerFactory;
import me.anatoliy57.bankmodel.view.LoggerFactory;
import me.anatoliy57.bankmodel.view.log.ConsoleCashBoxLogger;
import me.anatoliy57.bankmodel.view.log.abstraction.CashBoxLogger;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CashBoxTest {

    private CashBox cashBox;

    public void setup(int cash, boolean queueEmpty, List<Teller> tellers) {
        Configuration configuration = mock(Configuration.class);
        when(configuration.getAmountCashBox()).thenReturn(cash);

        WaitQueue queue = mock(WaitQueue.class);
        when(queue.isEmpty()).thenReturn(queueEmpty);

        CashBoxLogger logger = mock(ConsoleCashBoxLogger.class);
        LoggerFactory loggerFactory = mock(ConsoleLoggerFactory.class);
        when(loggerFactory.factoryCashBoxLogger()).thenReturn(logger);

        cashBox = new CashBox(configuration, tellers, queue, loggerFactory);
    }

    @Test
    public void putCash_emptyCashBox_cashBoxIncrease() throws NegativePutCashBoxException {
        setup(0, true, new ArrayList<>());

        cashBox.putCash(20);

        assertEquals(cashBox.getCash(), 20);
    }

    @Test
    public void putCash_negativeAmount_throwException() {
        setup(0, true, new ArrayList<>());

        assertThrows(NegativePutCashBoxException.class, () -> cashBox.putCash(-100));
    }

    @Test
    public void withdrawCash_cashBoxMoreAmountTaken_cashBoxDecrease() throws NegativeCashBoxException {
        setup(50, true, new ArrayList<>());

        cashBox.withdrawCash(50);

        assertEquals(cashBox.getCash(), 0);
    }

    @Test
    public void withdrawCash_cashBoxLessAmountTaken_throwException() {
        setup(0, true, new ArrayList<>());

        assertThrows(NegativeCashBoxException.class, () -> cashBox.withdrawCash(50));

        assertEquals(cashBox.getCash(), 0);
    }

    @Test
    public void putCash_waitQueueNotEmpty_notifyTellers() throws NegativePutCashBoxException {
        Teller teller = mock(Teller.class);
        List<Teller> tellers =  Arrays.asList(teller, teller, teller);
        setup(0, false, tellers);

        cashBox.putCash(20);

        verify(teller, times(tellers.size())).wakeUp();
    }
}