package me.anatoliy57.bankmodel.util;

/**
 * Generator unique ids
 *
 * @author Udarczev Anatoliy
 */
public class IdGenerator {

    private long id = -1;

    public synchronized long generateId() {
        id++;
        return id;
    }
}
