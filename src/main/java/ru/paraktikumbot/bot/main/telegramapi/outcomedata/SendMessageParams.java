package ru.paraktikumbot.bot.main.telegramapi.outcomedata;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SendMessageParams {
    @JsonProperty(value = "chat_id")
    private Integer chatId;
    private String text;

    public SendMessageParams setChatId(Integer chatId) {
        this.chatId = chatId;
        return this;
    }

    public SendMessageParams setText(String text) {
        this.text = text;
        return this;
    }

    public Integer getChatId() {
        return chatId;
    }

    public String getText() {
        return text;
    }

    public SendMessageParams() {}

    public SendMessageParams(Integer chatId, String text) {
        this.chatId = chatId;
        this.text = text;
    }
}
