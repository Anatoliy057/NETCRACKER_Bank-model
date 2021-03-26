package me.anatoliy57.bankmodel.model;

import me.anatoliy57.bankmodel.Configuration;
import me.anatoliy57.bankmodel.domain.pojo.Message;
import me.anatoliy57.bankmodel.domain.values.Client;
import me.anatoliy57.bankmodel.domain.values.ClientGenerator;
import me.anatoliy57.bankmodel.model.log.Logger;
import me.anatoliy57.bankmodel.util.Util;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class ClientFlow implements Runnable {

    private final List<Teller> tellers;
    private final Logger logger;

    private final int averageSleep;
    private final int sleepScatter;

    private final ClientGenerator generator;
    private final Random random;

    public ClientFlow(Configuration config, List<Teller> tellers, Logger logger) {
        this.tellers = tellers;
        this.logger = logger;

        averageSleep = config.getAverageNumberClients();
        sleepScatter = config.getNumberClientsScatter();

        generator = new ClientGenerator(config);
        random = new Random();
    }

    @Override
    public void run() {
        try {
            while (true) {
                if (Thread.interrupted()) {
                    return;
                }

                Client client = generator.generate();
                logger.log(Message.created(client));
                Optional<Teller> optTeller = tellers.stream()
                        .min(Comparator.comparingInt(Teller::size));

                Teller teller = optTeller.orElseThrow();
                teller.addToQueue(client);

                sleep();
            }
        } catch (InterruptedException ignored) {

        }
    }

    private void sleep() throws InterruptedException {
        int sleep = Util.generateAverageInt(averageSleep, sleepScatter, random);
        Thread.sleep(sleep);
    }
}
