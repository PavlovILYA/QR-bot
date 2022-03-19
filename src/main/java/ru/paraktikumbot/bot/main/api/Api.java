package ru.paraktikumbot.bot.main.api;

import ru.paraktikumbot.bot.main.telegramapi.outcomedata.SendMessageParams;
import ru.paraktikumbot.bot.main.telegramapi.outcomedata.SendPhotoParams;

public interface Api {
    void sendMessage(SendMessageParams sendMessageParams);
    void sendPhoto(SendPhotoParams sendPhotoParams);
}
