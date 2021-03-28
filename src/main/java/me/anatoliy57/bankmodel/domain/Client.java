package me.anatoliy57.bankmodel.domain;

import lombok.ToString;
import lombok.Value;
import me.anatoliy57.bankmodel.enums.TypeOperation;
import me.anatoliy57.bankmodel.util.IdGenerator;

@Value
@ToString
public class Client {

    private static IdGenerator idGenerator = new IdGenerator();

    long id;
    TypeOperation type;
    int amount;
    int serviceTime;

    public Client(TypeOperation type, int amount, int serviceTime) {
        this.type = type;
        this.amount = amount;
        this.serviceTime = serviceTime;

        this.id = idGenerator.generateId();
    }
}
