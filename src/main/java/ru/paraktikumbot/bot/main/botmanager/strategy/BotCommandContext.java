package ru.paraktikumbot.bot.main.botmanager.strategy;

import org.springframework.stereotype.Component;
import ru.paraktikumbot.bot.main.api.Api;
import ru.paraktikumbot.bot.main.botmanager.model.BotCommand;
import ru.paraktikumbot.bot.main.common.model.Update;
import ru.paraktikumbot.bot.main.telegramapi.outcomedata.SendMessageParams;

import java.util.List;

@Component
public class BotCommandContext {

    private final List<BotCommandStrategy> strategies;
    private final Api api;
    private boolean isWaitingForQrData = false;

    public BotCommandContext(List<BotCommandStrategy> strategies, Api api) {
        this.strategies = strategies;
        this.api = api;
    }

    public void process(Update update) {

        if (isWaitingForQrData) {
            checkQrData(update);
            return;
        }

        for (BotCommandStrategy strategy : strategies) {
            if (strategy.apply(update)) {
                isWaitingForQrData = strategy.process(update);
                return;
            }
        }
    }

    public void checkQrData(Update update) {
        System.out.println("Qr data " + update.getMessage().getText());
        SendMessageParams sendMessageParams = new SendMessageParams()
                .setText("Ловите QR #")
                .setChatId(update.getMessage().getChat().getId());
        api.sendMessage(sendMessageParams);
    }
}
