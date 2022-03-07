package ru.paraktikumbot.bot.main.botmanager.service;

import org.springframework.stereotype.Service;
import ru.paraktikumbot.bot.main.botmanager.strategy.BotCommandContext;
import ru.paraktikumbot.bot.main.common.model.Update;

@Service
public class BotManagerService {

    private final BotCommandContext context;

    public BotManagerService(BotCommandContext context) {
        this.context = context;
    }

    public void process(Update update) {
        System.out.println("    UpdateId: " + update.getUpdateId());
        System.out.println("   MessageId: " + update.getMessageId());
        System.out.println(" MessageText: " + update.getMessageText());
        System.out.println("      ChatId: " + update.getMessage().getChat().getId());
        System.out.println("    ChatType: " + update.getMessage().getChat().getType());
        System.out.println("ChatUsername: " + update.getMessage().getChat().getUsername());

        context.process(update);

        System.out.println("----------------------------");
    }



}
