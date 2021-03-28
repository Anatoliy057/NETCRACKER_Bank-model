package me.anatoliy57.bankmodel.model;

import me.anatoliy57.bankmodel.domain.Configuration;
import me.anatoliy57.bankmodel.domain.Client;
import me.anatoliy57.bankmodel.view.LoggerFactory;
import me.anatoliy57.bankmodel.util.Util;
import me.anatoliy57.bankmodel.view.log.abstraction.ClientsFlowLogger;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Random;

/**
 * Clients flow that generate clients with random pause and distributes them among tellers
 *
 * @author Udarczev Anatoliy
 */
public class ClientsFlow implements Runnable {

    /** List of tellers */
    private final List<Teller> tellers;
    /** Logger for trace of clients flow works */
    private final ClientsFlowLogger logger;

    /** Average time for sleep between clients creating */
    private final int averageSleep;
    /** Maximum difference from average time for sleep */
    private final int sleepScatter;

    /** Client generator */
    private final ClientGenerator generator;
    /** Object for creating "random" pause value */
    private final Random random;

    /**
     * @param config configuration for bank simulation
     * @param tellers list of tellers
     * @param loggerFactory logger factory
     */
    public ClientsFlow(Configuration config, List<Teller> tellers, LoggerFactory loggerFactory) {
        this.tellers = tellers;

        logger = loggerFactory.factoryClientFlowLogger();
        averageSleep = config.getAverageNumberClients();
        sleepScatter = config.getNumberClientsScatter();

        generator = new ClientGenerator(config);
        random = new Random();
    }

    /**
     * The runnable method for thread of clients flow
     */
    @Override
    public void run() {
        try {
            while (true) {
                if (Thread.interrupted()) {
                    return;
                }

                Client client = generator.generate();
                logger.log(client);
                Optional<Teller> optTeller = tellers.stream()
                        .min(Comparator.comparingInt(Teller::size));

                Teller teller = optTeller.orElseThrow();
                teller.addToQueue(client);

                sleep();
            }
        } catch (InterruptedException ignored) {

        }
    }

    /**
     * Pause between clients generating
     *
     * @throws InterruptedException if thread interrupted
     */
    private void sleep() throws InterruptedException {
        int sleep = Util.generateAverageInt(averageSleep, sleepScatter, random);
        Thread.sleep(sleep);
    }
}
