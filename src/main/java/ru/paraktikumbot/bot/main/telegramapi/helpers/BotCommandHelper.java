package ru.paraktikumbot.bot.main.telegramapi.helpers;

import org.springframework.stereotype.Component;
import ru.paraktikumbot.bot.main.botmanager.model.BotCommand;
import ru.paraktikumbot.bot.main.common.model.Update;
import ru.paraktikumbot.bot.main.telegramapi.incomedata.EntityType;
import ru.paraktikumbot.bot.main.telegramapi.incomedata.MessageEntity;

import java.util.List;

@Component
public class BotCommandHelper {
    public boolean checkCommand(BotCommand command, Update update) {
        List<MessageEntity> entities = update.getMessage().getEntities();
        if (entities != null) {
            for (MessageEntity entity : entities) {
                String entityType = entity.getType();
                System.out.println("entityType: " + entityType);
                String text = update.getMessage().getText();
                System.out.println("      text: " + text);
                if (EntityType.BOT_COMMAND.getType().equals(entityType)
                        && command.getCommand().equals(text)) {
                    return true;
                }
            }
        }
        return false;
    }
}
