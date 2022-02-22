package ru.paraktikumbot.bot.main.common.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SendMessageParams {
    @JsonProperty(value = "chat_id")
    private Integer chatId;

    @JsonProperty(value = "text")
    private String text;

    public Integer getChatId() {
        return chatId;
    }

    public String getText() {
        return text;
    }

    public SendMessageParams(Integer chatId, String text) {
        this.chatId = chatId;
        this.text = text;
    }
}
