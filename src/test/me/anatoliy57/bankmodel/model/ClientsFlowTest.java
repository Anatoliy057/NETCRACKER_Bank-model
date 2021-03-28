package me.anatoliy57.bankmodel.model;

import me.anatoliy57.bankmodel.domain.Configuration;
import me.anatoliy57.bankmodel.view.ConsoleLoggerFactory;
import me.anatoliy57.bankmodel.view.LoggerFactory;
import me.anatoliy57.bankmodel.view.log.ConsoleClientsFlowLogger;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import static org.mockito.Mockito.*;

class ClientsFlowTest {

    void setup(List<Teller> tellers) {
        Configuration configuration = Configuration.builder()
                .averageNumberClients(50)
                .numberTellers(3)
                .averageServiceTime(0)
                .timeToSimulation(30)
                .build();
        configuration.init();

        ConsoleClientsFlowLogger logger = mock(ConsoleClientsFlowLogger.class);
        LoggerFactory loggerFactory = mock(ConsoleLoggerFactory.class);
        when(loggerFactory.factoryClientFlowLogger()).thenReturn(logger);

        clientsFlow = new ClientsFlow(configuration, tellers, loggerFactory);
    }

    private ClientsFlow clientsFlow;

    @Test
    public void run_oneTeller_giveClientToTeller() {
        Teller teller = mock(Teller.class);
        doAnswer(i -> {
            Thread.currentThread().interrupt();
            return null;
        }).when(teller).addToQueue(any());
        List<Teller> tellers = Arrays.asList(teller, teller);
        setup(tellers);

        clientsFlow.run();

        verify(teller, times(1)).addToQueue(any());
    }
}