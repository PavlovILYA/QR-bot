package ru.paraktikumbot.bot.main.botmanager.strategy;

import ru.paraktikumbot.bot.main.common.model.Update;

public class HelpCommand implements BotCommandStrategy {

    @Override
    public boolean apply(Update update) {
        return false;
    }

    @Override
    public void process(Update update) {

    }
}
