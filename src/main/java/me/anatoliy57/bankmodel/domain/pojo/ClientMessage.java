package me.anatoliy57.bankmodel.domain.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.anatoliy57.bankmodel.enums.ClientStage;
import me.anatoliy57.bankmodel.domain.values.Client;

@Getter
@AllArgsConstructor
public class ClientMessage implements Message {

    private Client client;
    private ClientStage stage;
}
