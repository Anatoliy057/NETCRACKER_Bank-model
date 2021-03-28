package me.anatoliy57.bankmodel.view.log.abstraction;

import me.anatoliy57.bankmodel.domain.Client;

public interface TellerLogger {

    void logEnter(Client client);

    void logRejected(Client client);

    void logServicing(Client client);

    void logServiced(Client client);
}
