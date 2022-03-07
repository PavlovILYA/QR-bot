package ru.paraktikumbot.bot.main.api;

import ru.paraktikumbot.bot.main.telegramapi.outcomedata.SendMessageParams;

public interface Api {
    void sendMessage(SendMessageParams sendMessageParams);
}
