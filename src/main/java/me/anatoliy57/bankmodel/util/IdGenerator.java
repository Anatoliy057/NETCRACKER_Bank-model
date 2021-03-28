package me.anatoliy57.bankmodel.util;

import lombok.experimental.UtilityClass;

public class IdGenerator {

    private long id = -1;

    public synchronized long generateId() {
        id++;
        return id;
    }
}
