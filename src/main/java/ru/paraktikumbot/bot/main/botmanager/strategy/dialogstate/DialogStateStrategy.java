package ru.paraktikumbot.bot.main.botmanager.strategy.dialogstate;

import ru.paraktikumbot.bot.main.common.model.Update;

public interface DialogStateStrategy {

    boolean apply(Update update); // выбирает нужную стратегию

    void process(Update update);  // выполняет логику

}
