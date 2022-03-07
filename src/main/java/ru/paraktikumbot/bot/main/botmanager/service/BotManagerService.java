package ru.paraktikumbot.bot.main.botmanager.service;

import ru.paraktikumbot.bot.main.botmanager.strategy.BotCommandContext;
import ru.paraktikumbot.bot.main.common.model.Update;

public class BotManagerService {

    private final BotCommandContext context;

    public BotManagerService(BotCommandContext context) {
        this.context = context;
    }

    public void process(Update update) {
        // тут можно дополнительно обработать
        context.process(update);
        // и тут
    }



}
