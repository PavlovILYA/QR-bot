package ru.paraktikumbot.bot.main.botmanager.strategy;

import org.springframework.stereotype.Component;
import ru.paraktikumbot.bot.main.api.Api;
import ru.paraktikumbot.bot.main.botmanager.model.BotCommand;
import ru.paraktikumbot.bot.main.common.model.Update;
import ru.paraktikumbot.bot.main.telegramapi.helpers.BotCommandHelper;
import ru.paraktikumbot.bot.main.telegramapi.outcomedata.SendMessageParams;

@Component
public class JustMessage implements BotCommandStrategy {

    private final Api api;
    private final BotCommandHelper helper;

    public JustMessage(Api api, BotCommandHelper helper) {
        this.api = api;
        this.helper = helper;
    }

    @Override
    public boolean apply(Update update) {
        return !(helper.checkCommand(BotCommand.START, update) || helper.checkCommand(BotCommand.HELP, update));
    }

    @Override
    public void process(Update update) {
        System.out.println("Just message strategy " + update.getMessage().getText());
        SendMessageParams sendMessageParams = new SendMessageParams()
                .setText("Очень уместное сообщение!")
                .setChatId(update.getMessage().getChat().getId());
        api.sendMessage(sendMessageParams);
    }
}
