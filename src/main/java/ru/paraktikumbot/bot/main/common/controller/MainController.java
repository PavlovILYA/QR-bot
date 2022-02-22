package ru.paraktikumbot.bot.main.common.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.paraktikumbot.bot.main.service.TelegramRequestService;
import ru.paraktikumbot.bot.main.common.model.Update;

@RestController
@RequestMapping("/")
public class MainController {
    private final TelegramRequestService telegramRequestService;

    public MainController(TelegramRequestService telegramRequestService) {
        this.telegramRequestService = telegramRequestService;
    }

    @PostMapping("/")
    public void main(@RequestBody Update update) {
        System.out.println("    UpdateId: " + update.getUpdateId());
        System.out.println("   MessageId: " + update.getMessageId());
        System.out.println(" MessageText: " + update.getMessageText());
        System.out.println("      ChatId: " + update.getMessage().getChat().getId());
        System.out.println("    ChatType: " + update.getMessage().getChat().getType());
        System.out.println("ChatUsername: " + update.getMessage().getChat().getUsername());
        telegramRequestService.sendMessage(update.getMessage().getChat().getId(), "Hello, I am Pavlov's Bot");
        System.out.println("----------------------------");
    }


}
