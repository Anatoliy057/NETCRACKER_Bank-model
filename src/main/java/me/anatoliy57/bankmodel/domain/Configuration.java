package me.anatoliy57.bankmodel.domain;

import lombok.*;

/**
 * An entity that stores the configuration parameters of the simulation of the bank work
 *
 * @author Udarczev Anatoliy
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Configuration {

    /** */
    /** Number of tellers */
    private int numberTellers;
    /** Average time for sleep between clients creating */
    private int averageNumberClients;
    /** Maximum difference from average time for sleep */
    private int numberClientsScatter;
    /** Average service time */
    private int averageServiceTime;
    /** Maximum difference from average service time */
    private int serviceTimeScatter;
    /** Average time for sleep between clients creating */
    private int averageAmount;
    /** Maximum difference from average amount money */
    private int amountScatter;
    /** Amount money in cash box on start */
    private int amountCashBox;
    /** Time to simulation bank work */
    private int timeToSimulation;

    /**
     * Init default values
     */
    public void init() {
        averageAmount = 30;
        amountCashBox = 10;
        serviceTimeScatter = (int) (averageServiceTime * 0.3) + 1;
        numberClientsScatter = (int) (averageNumberClients * 0.3) + 1;
        amountScatter = (int) (averageAmount * 0.3) + 1;
    }
}
