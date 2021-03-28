package me.anatoliy57.bankmodel.view.log.abstraction;

import me.anatoliy57.bankmodel.domain.Client;
import me.anatoliy57.bankmodel.enums.ClientWaitStage;

import static me.anatoliy57.bankmodel.enums.ClientWaitStage.ENTER;
import static me.anatoliy57.bankmodel.enums.ClientWaitStage.OUT;

public interface WaitQueueLogger {

    void logEnter(Client client);

    void logOut(Client client);
}
