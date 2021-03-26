package me.anatoliy57.bankmodel.model.log;

import me.anatoliy57.bankmodel.domain.pojo.Message;

public interface Logger {

    void log(Message message);
}
