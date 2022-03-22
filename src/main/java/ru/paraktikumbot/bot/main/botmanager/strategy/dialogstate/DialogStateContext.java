package ru.paraktikumbot.bot.main.botmanager.strategy.dialogstate;

import org.springframework.stereotype.Component;
import ru.paraktikumbot.bot.main.common.model.Update;

import java.util.List;

@Component
public class DialogStateContext {
    private final List<DialogStateStrategy> strategies;


    public DialogStateContext(List<DialogStateStrategy> strategies) {
        this.strategies = strategies;
    }

    public void process(Update update) {
        for (DialogStateStrategy strategy : strategies) {
            if (strategy.apply(update)) {
                strategy.process(update);
                return;
            }
        }
    }

}
