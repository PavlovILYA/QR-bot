package ru.paraktikumbot.bot.main.botmanager.strategy.dialogstate;

import net.glxn.qrgen.core.scheme.VCard;
import org.springframework.stereotype.Component;
import ru.paraktikumbot.bot.main.api.Api;
import ru.paraktikumbot.bot.main.botmanager.model.DialogState;
import ru.paraktikumbot.bot.main.botmanager.service.DialogStateService;
import ru.paraktikumbot.bot.main.common.model.Update;
import ru.paraktikumbot.bot.main.telegramapi.outcomedata.SendMessageParams;

@Component
public class AskingTitleState implements DialogStateStrategy {

    private final Api api;
    private final DialogStateService dialogStateService;

    public AskingTitleState(Api api, DialogStateService dialogStateService) {
        this.api = api;
        this.dialogStateService = dialogStateService;
    }

    @Override
    public boolean apply(Update update) {
        return dialogStateService.checkDialogState(update.getChatId()) == DialogState.ASKING_TITLE;
    }

    @Override
    public void process(Update update) {
        System.out.println("Title " + update.getMessageText());
        dialogStateService.putDialogState(update.getChatId(),
                DialogState.ASKING_COMPANY);

        VCard vCard = (VCard) dialogStateService.getDialogData(update.getChatId());
        vCard.setTitle(update.getMessageText());

        SendMessageParams sendMessageParams = new SendMessageParams()
                .setText("Введите организацию/компанию")
                .setChatId(update.getChatId());
        api.sendMessage(sendMessageParams);
    }
}
