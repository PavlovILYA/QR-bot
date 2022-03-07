package ru.paraktikumbot.bot.main.common.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.paraktikumbot.bot.main.botmanager.service.BotManagerService;
import ru.paraktikumbot.bot.main.telegramapi.service.TelegramRequestService;
import ru.paraktikumbot.bot.main.common.model.Update;

@RestController
@RequestMapping("/")
public class MainController {
    private final TelegramRequestService telegramRequestService; // -
    private final BotManagerService botManagerService;

    public MainController(TelegramRequestService telegramRequestService, BotManagerService botManagerService) {
        this.telegramRequestService = telegramRequestService;
        this.botManagerService = botManagerService;
    }

    @PostMapping("/")
    public void main(@RequestBody Update update) {

        botManagerService.process(update);

//        System.out.println("    UpdateId: " + update.getUpdateId());
//        System.out.println("   MessageId: " + update.getMessageId());
//        System.out.println(" MessageText: " + update.getMessageText());
//        System.out.println("      ChatId: " + update.getMessage().getChat().getId());
//        System.out.println("    ChatType: " + update.getMessage().getChat().getType());
//        System.out.println("ChatUsername: " + update.getMessage().getChat().getUsername());
//        telegramRequestService.sendMessage(update.getMessage().getChat().getId(), "Hello, I am Pavlov's Bot");
//        System.out.println("----------------------------");
    }


}
