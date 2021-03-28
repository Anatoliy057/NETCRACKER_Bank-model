package me.anatoliy57.bankmodel;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.ToString;

import java.util.Scanner;

@Getter
@ToString
public class Configuration {

    private int numberTellers;

    private int averageNumberClients;
    private int numberClientsScatter;
    private int averageServiceTime;
    private int serviceTimeScatter;
    private int averageAmount;
    private int amountScatter;
    private int amountCashDesk;
    private int timeToSimulation;

    @Getter(AccessLevel.NONE)
    private final Scanner scanner;

    public Configuration() {
        scanner = new Scanner(System.in);
        init();

        serviceTimeScatter = (int) (averageServiceTime * 0.3) + 1;
        numberClientsScatter = (int) (averageNumberClients * 0.3) + 1;
        averageAmount = 30;
        amountScatter = (int) (averageAmount * 0.3) + 1;
        amountCashDesk = 10;
    }

    private void init() {
        readNumberTellers();
        readAverageServiceTime();
        readAverageNumberClientsPerMinute();
        readTimeToSimulation();
    }

    private void readAverageServiceTime() {
        out("Enter the average service time (seconds)");
        averageServiceTime = readInt();
    }

    private void readNumberTellers() {
        out("Enter the number of tellers");
        numberTellers = readInt();
    }

    private void readAverageNumberClientsPerMinute() {
        out("Enter the average number of clients per minute");
        averageNumberClients = (int) Math.round(60000.0/readInt());
    }

    private void readTimeToSimulation() {
        out("Enter simulation time (seconds)");
        timeToSimulation = readInt();
    }

    private int readInt() {
        return scanner.nextInt();
    }

    private void out(String msg) {
        System.out.println(msg);
    }
}
