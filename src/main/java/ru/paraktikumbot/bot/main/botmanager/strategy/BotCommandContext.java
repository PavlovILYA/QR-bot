package ru.paraktikumbot.bot.main.botmanager.strategy;

import ru.paraktikumbot.bot.main.common.model.Update;

import java.util.List;

public class BotCommandContext {

    private final List<BotCommandStrategy> stratagies;

    public BotCommandContext(List<BotCommandStrategy> stratagies) {
        this.stratagies = stratagies;
    }

    public void process(Update update) {
        for (BotCommandStrategy strategy : stratagies) {
            if (strategy.apply(update)) {
                strategy.process(update);
                return;
            }
        }
    }
}
