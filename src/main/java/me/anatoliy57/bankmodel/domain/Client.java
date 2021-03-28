package me.anatoliy57.bankmodel.domain;

import lombok.ToString;
import lombok.Value;
import me.anatoliy57.bankmodel.enums.TypeOperation;
import me.anatoliy57.bankmodel.util.IdGenerator;

/**
 * The essence of a bank client who either puts money in the cashier, or withdraw it through the service
 *
 * @see IdGenerator
 * @see TypeOperation
 *
 * @author Udarczev Anatoliy
 */
@Value
@ToString
public class Client {

    /** Generates a client ID when it is created */
    private static IdGenerator idGenerator = new IdGenerator();

    /** ID of client */
    long id;
    /** The type of operation that the client wants to make at the bank */
    TypeOperation type;
    /** Amount of money */
    int amount;
    /** Service time */
    int serviceTime;

    public Client(TypeOperation type, int amount, int serviceTime) {
        this.type = type;
        this.amount = amount;
        this.serviceTime = serviceTime;

        this.id = idGenerator.generateId();
    }
}
