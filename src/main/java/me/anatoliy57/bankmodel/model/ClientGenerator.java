package me.anatoliy57.bankmodel.model;

import me.anatoliy57.bankmodel.domain.Configuration;
import me.anatoliy57.bankmodel.domain.Client;
import me.anatoliy57.bankmodel.enums.TypeOperation;
import me.anatoliy57.bankmodel.util.Util;

import java.util.Random;

/**
 * Generates client instances depending on the bank simulation configuration
 *
 * @author Udarczev Anatoliy
 */
public class ClientGenerator {

    /** Average service time */
    private final int averageServiceTime;
    /** Maximum difference from average service time */
    private final int serviceTimeScatter;
    /** Average amount money for operation */
    private final int averageAmount;
    /** Maximum difference from average amount money */
    private final int amountScatter;

    /** Object for creating "random" values of client parameters */
    private final Random random;

    /**
     * @param config configuration for bank simulation
     */
    public ClientGenerator(Configuration config) {
        averageServiceTime = config.getAverageServiceTime();
        serviceTimeScatter = config.getServiceTimeScatter();
        averageAmount = config.getAverageAmount();
        amountScatter = config.getAmountScatter();

        random = new Random();
    }

    /**
     * @return generated client
     */
    public Client generate() {
        int serviceTime = generateInt(averageServiceTime, serviceTimeScatter);
        int amount = generateInt(averageAmount, amountScatter);

        int bound = TypeOperation.values().length;
        TypeOperation type = TypeOperation.values()[random.nextInt(bound)];

        return new Client(type, amount, serviceTime);
    }

    private int generateInt(int average, int scatter) {
        return Util.generateAverageInt(average, scatter, random);
    }
}
