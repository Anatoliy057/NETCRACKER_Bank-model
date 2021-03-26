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

    @Getter(AccessLevel.NONE)
    private final Scanner scanner;

    public Configuration() {
        scanner = new Scanner(System.in);
        init();

        serviceTimeScatter = (int) (averageServiceTime * 0.3) + 1;
        numberClientsScatter = (int) (averageNumberClients * 0.3) + 1;
        averageAmount = 30;
        amountScatter = (int) (averageAmount * 0.3) + 1;
        amountCashDesk = 100;
    }

    private void init() {
        readNumberTellers();
        readAverageServiceTime();
        readAverageNumberClientsPerMinute();
    }

    private void readAverageServiceTime() {
        out("Введите среднее время обслуживание");
        averageServiceTime = readInt();
    }

    private void readNumberTellers() {
        out("Введите количество операционистов");
        numberTellers = readInt();
    }

    private void readAverageNumberClientsPerMinute() {
        out("Введите среднее количество клиентов в минуту");
        averageNumberClients = (int) Math.round(60000.0/readInt());
    }

    private int readInt() {
        return scanner.nextInt();
    }

    private void out(String msg) {
        System.out.println(msg);
    }
}
