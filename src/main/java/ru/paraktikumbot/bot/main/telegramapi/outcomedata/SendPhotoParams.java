package ru.paraktikumbot.bot.main.telegramapi.outcomedata;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.File;

public class SendPhotoParams {
    @JsonProperty(value = "chat_id")
    private Integer chatId;
    private File photo;

    public SendPhotoParams() {
    }

    public SendPhotoParams(Integer chatId, File photo) {
        this.chatId = chatId;
        this.photo = photo;
    }

    public Integer getChatId() {
        return chatId;
    }

    public SendPhotoParams setChatId(Integer chatId) {
        this.chatId = chatId;
        return this;
    }

    public File getPhoto() {
        return photo;
    }

    public SendPhotoParams setPhoto(File photo) {
        this.photo = photo;
        return this;
    }
}
