package ru.paraktikumbot.bot.main.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Update {

    @JsonProperty(value = "update_id")
    private Integer updateId;

    @JsonProperty(value = "message")
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
}
