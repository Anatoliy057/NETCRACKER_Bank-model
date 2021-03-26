package me.anatoliy57.bankmodel;

import me.anatoliy57.bankmodel.model.Bank;
import me.anatoliy57.bankmodel.model.log.ConsoleLogger;
import me.anatoliy57.bankmodel.model.log.Logger;

public class Application {

    public static void main(String[] args) throws InterruptedException {
        Configuration config = new Configuration();
        Logger logger = new ConsoleLogger();

        Bank bank = new Bank(config, logger);
        bank.start();

        Thread.sleep(20 * 1000);

        bank.stop();
    }
}
