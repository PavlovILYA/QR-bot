package ru.paraktikumbot.bot.main.botmanager.service;

import org.springframework.stereotype.Service;
import ru.paraktikumbot.bot.main.botmanager.model.DialogState;
import ru.paraktikumbot.bot.main.botmanager.strategy.botcommand.BotCommandContext;
import ru.paraktikumbot.bot.main.botmanager.strategy.dialogstate.DialogStateContext;
import ru.paraktikumbot.bot.main.common.model.Update;

@Service
public class BotManagerService {

    private final BotCommandContext botCommandContext;
    private final DialogStateContext dialogStateContext;
    private final DialogStateService dialogStateService;

    public BotManagerService(BotCommandContext botCommandContext,
                             DialogStateContext dialogStateContext,
                             DialogStateService dialogStateService) {
        this.botCommandContext = botCommandContext;
        this.dialogStateContext = dialogStateContext;
        this.dialogStateService = dialogStateService;
    }

    public void process(Update update) {
        System.out.println("    UpdateId: " + update.getUpdateId());
        System.out.println("   MessageId: " + update.getMessageId());
        System.out.println(" MessageText: " + update.getMessageText());
        System.out.println("      ChatId: " + update.getMessage().getChat().getId());
        System.out.println("    ChatType: " + update.getMessage().getChat().getType());
        System.out.println("ChatUsername: " + update.getMessage().getChat().getUsername());

        if  (dialogStateService.checkDialogState(update.getChatId()) == DialogState.INITIAL) {
            botCommandContext.process(update);
        } else {
            dialogStateContext.process(update);
        }



        System.out.println("----------------------------");
    }



}
