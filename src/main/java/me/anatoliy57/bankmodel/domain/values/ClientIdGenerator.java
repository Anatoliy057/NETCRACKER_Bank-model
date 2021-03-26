package me.anatoliy57.bankmodel.domain.values;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ClientIdGenerator {

    private static long id = -1;

    public synchronized static long generateId() {
        id++;
        return id;
    }
}
