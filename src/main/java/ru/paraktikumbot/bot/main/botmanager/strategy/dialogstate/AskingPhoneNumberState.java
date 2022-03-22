package ru.paraktikumbot.bot.main.botmanager.strategy.dialogstate;

import net.glxn.qrgen.core.scheme.VCard;
import org.springframework.stereotype.Component;
import ru.paraktikumbot.bot.main.api.Api;
import ru.paraktikumbot.bot.main.botmanager.model.DialogState;
import ru.paraktikumbot.bot.main.botmanager.service.DialogStateService;
import ru.paraktikumbot.bot.main.common.model.Update;
import ru.paraktikumbot.bot.main.telegramapi.outcomedata.SendMessageParams;

@Component
public class AskingPhoneNumberState implements DialogStateStrategy {

    private final Api api;
    private final DialogStateService dialogStateService;

    public AskingPhoneNumberState(Api api, DialogStateService dialogStateService) {
        this.api = api;
        this.dialogStateService = dialogStateService;
    }

    @Override
    public boolean apply(Update update) {
        return dialogStateService.checkDialogState(update.getChatId()) == DialogState.ASKING_PHONE_NUMBER;
    }

    @Override
    public void process(Update update) {
        System.out.println("Phone number " + update.getMessageText());
        dialogStateService.putDialogState(update.getChatId(),
                DialogState.ASKING_WEBSITE);

        VCard vCard = (VCard) dialogStateService.getDialogData(update.getChatId());
        vCard.setPhoneNumber(update.getMessageText());

        SendMessageParams sendMessageParams = new SendMessageParams()
                .setText("Введите вебсайт")
                .setChatId(update.getChatId());
        api.sendMessage(sendMessageParams);
    }
}
