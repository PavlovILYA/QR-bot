package ru.paraktikumbot.bot.main.botmanager.strategy;

import ru.paraktikumbot.bot.main.botmanager.model.BotCommand;
import ru.paraktikumbot.bot.main.common.model.Update;
import ru.paraktikumbot.bot.main.api.Api;
import ru.paraktikumbot.bot.main.telegramapi.helpers.BotCommandHelper;
import ru.paraktikumbot.bot.main.telegramapi.outcomedata.SendMessageParams;

public class StartCommand implements BotCommandStrategy {

    private final Api api;
    private final BotCommandHelper helper;

    public StartCommand(Api api, BotCommandHelper botCommandHelper) {
        this.api = api;
        this.helper = botCommandHelper;
    }

    @Override
    public boolean apply(Update update) {
        return helper.checkCommand(BotCommand.START, update);
    }

    @Override
    public void process(Update update) {
        System.out.println("Start command strategy " + update.getMessage().getText());
        SendMessageParams sendMessageParams = new SendMessageParams()
                .setText("Привет!")
                .setChatId(update.getMessage().getChat().getId());
        api.sendMessage(sendMessageParams);
    }
}
