package me.anatoliy57.bankmodel.view.log.abstraction;

import me.anatoliy57.bankmodel.domain.Client;

public interface ClientFlowLogger {

    void log(Client client);
}
