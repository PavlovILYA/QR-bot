package ru.paraktikumbot.bot.main.botmanager.strategy;

import ru.paraktikumbot.bot.main.common.model.Update;

public interface BotCommandStrategy {

    boolean apply(Update update); // выбирает нужную страттегию

    void process(Update update);  // выполняет логику

}
