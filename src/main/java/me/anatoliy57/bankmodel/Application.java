package me.anatoliy57.bankmodel;

import me.anatoliy57.bankmodel.model.Bank;
import me.anatoliy57.bankmodel.view.ConsoleLoggerFactory;
import me.anatoliy57.bankmodel.view.LoggerFactory;

public class Application {

    public static void main(String[] args) throws InterruptedException {
        Configuration config = new Configuration();
        LoggerFactory factory = new ConsoleLoggerFactory();

        Bank bank = new Bank(config, factory);
        bank.start();

        Thread.sleep(config.getTimeToSimulation() * 1000);

        bank.stop();
    }
}
