package ru.paraktikumbot.bot.main.model;

import org.springframework.stereotype.Service;

@Service
public class TelegramRequestService {
    public String sendMessage() {
        return "Hello, I am Pavlov's Bot";
    }
}
