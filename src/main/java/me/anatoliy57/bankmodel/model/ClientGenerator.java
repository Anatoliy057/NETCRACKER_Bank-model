package me.anatoliy57.bankmodel.model;

import me.anatoliy57.bankmodel.Configuration;
import me.anatoliy57.bankmodel.domain.Client;
import me.anatoliy57.bankmodel.enums.TypeOperation;
import me.anatoliy57.bankmodel.util.Util;

import java.util.Random;

public class ClientGenerator {

    private final Configuration config;
    private final Random random;

    public ClientGenerator(Configuration config) {
        this.config = config;

        random = new Random();
    }

    public Client generate() {
        int scatter;
        int average;

        average = config.getAverageServiceTime();
        scatter = config.getServiceTimeScatter();

        int serviceTime = generateInt(average, scatter);

        average = config.getAverageAmount();
        scatter = config.getAmountScatter();

        int amount = generateInt(average, scatter);

        int bound = TypeOperation.values().length;
        TypeOperation type = TypeOperation.values()[random.nextInt(bound)];

        return new Client(type, amount, serviceTime);
    }

    private int generateInt(int average, int scatter) {
        return Util.generateAverageInt(average, scatter, random);
    }
}
