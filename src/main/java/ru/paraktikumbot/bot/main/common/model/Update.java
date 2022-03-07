package ru.paraktikumbot.bot.main.common.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.paraktikumbot.bot.main.telegramapi.incomedata.Message;

public class Update {

    @JsonProperty(value = "update_id")
    private Integer updateId;
    private Message message;

    public Integer getUpdateId() {
        return updateId;
    }

    public Integer getMessageId() {
        return message.getMessageId();
    }

    public String getMessageText() {
        return message.getText();
    }

    public Message getMessage() {
        return message;
    }

    public Update setUpdateId(Integer updateId) {
        this.updateId = updateId;
        return this;
    }

    public Update setMessage(Message message) {
        this.message = message;
        return this;
    }  
}
