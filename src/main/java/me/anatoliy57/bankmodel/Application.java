package me.anatoliy57.bankmodel;

import me.anatoliy57.bankmodel.controller.ConsoleConfigurationController;
import me.anatoliy57.bankmodel.domain.Configuration;
import me.anatoliy57.bankmodel.model.Bank;
import me.anatoliy57.bankmodel.view.ConsoleLoggerFactory;
import me.anatoliy57.bankmodel.view.LoggerFactory;

public class Application {

    public static void main(String[] args) throws InterruptedException {
        Configuration config = new Configuration();
        ConsoleConfigurationController controller = new ConsoleConfigurationController(config);

        controller.readNumberTellers();
        controller.readAverageServiceTime();
        controller.readAverageNumberClientsPerMinute();
        controller.readTimeToSimulation();
        config.init();

        LoggerFactory factory = new ConsoleLoggerFactory();
        Bank bank = new Bank(config, factory);

        bank.start();
        Thread.sleep(config.getTimeToSimulation() * 1000);
        bank.stop();
    }
}
