package me.anatoliy57.bankmodel.controller;

import me.anatoliy57.bankmodel.domain.Configuration;

import java.util.Scanner;

/**
 * Controller for the enter some configuration parameters
 * for simulating the work of the bank from the console
 *
 * @see Configuration
 *
 * @author Udarczev Anatoliy
 */
public class ConsoleConfigurationController {

    /** Configuration for bank simulation */
    private final Configuration config;

    /** Console stream */
    private final Scanner scanner;

    public ConsoleConfigurationController(Configuration config) {
        this.config = config;

        scanner = new Scanner(System.in);
    }

    public void readAverageServiceTime() {
        out("Enter the average service time (seconds)");
        config.setAverageServiceTime(readInt());
    }

    public void readNumberTellers() {
        out("Enter the number of tellers");
        config.setNumberTellers(readInt());
    }

    public void readAverageNumberClientsPerMinute() {
        out("Enter the average number of clients per minute");
        config.setAverageNumberClients((int) Math.round(60000.0/readInt()));
    }

    public void readTimeToSimulation() {
        out("Enter simulation time (seconds)");
        config.setTimeToSimulation(readInt());
    }

    private int readInt() {
        return scanner.nextInt();
    }

    private void out(String msg) {
        System.out.println(msg);
    }
}
