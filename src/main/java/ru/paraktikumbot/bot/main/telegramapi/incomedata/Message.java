package ru.paraktikumbot.bot.main.telegramapi.incomedata;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Message {

    @JsonProperty(value = "message_id")
    private Integer messageId;
    private String text;
    private Chat chat;

    public List<MessageEntity> getEntities() {
        return entities;
    }

    public Message setEntities(List<MessageEntity> entities) {
        this.entities = entities;
        return this;
    }

    private List<MessageEntity> entities;

    public Message setMessageId(Integer messageId) {
        this.messageId = messageId;
        return this;
    }

    public Message setText(String text) {
        this.text = text;
        return this;
    }

    public Message setChat(Chat chat) {
        this.chat = chat;
        return this;
    }

    public Integer getMessageId() {
        return messageId;
    }

    public String getText() {
        return text;
    }

    public Chat getChat() {
        return chat;
    }
}
