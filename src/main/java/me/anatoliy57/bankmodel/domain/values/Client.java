package me.anatoliy57.bankmodel.domain.values;

import lombok.Getter;
import me.anatoliy57.bankmodel.enums.TypeOperation;

@Getter
public class Client {

    private final long id;
    private final TypeOperation type;
    private final int amount;
    private final int serviceTime;

    public Client(TypeOperation type, int amount, int serviceTime) {
        this.id = ClientIdGenerator.generateId();
        this.type = type;
        this.amount = amount;
        this.serviceTime = serviceTime;
    }
}
