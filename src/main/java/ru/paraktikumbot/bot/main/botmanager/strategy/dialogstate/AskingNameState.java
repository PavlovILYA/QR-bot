package ru.paraktikumbot.bot.main.botmanager.strategy.dialogstate;

import net.glxn.qrgen.core.scheme.VCard;
import org.springframework.stereotype.Component;
import ru.paraktikumbot.bot.main.api.Api;
import ru.paraktikumbot.bot.main.botmanager.model.DialogState;
import ru.paraktikumbot.bot.main.botmanager.service.DialogStateService;
import ru.paraktikumbot.bot.main.common.model.Update;
import ru.paraktikumbot.bot.main.telegramapi.outcomedata.SendMessageParams;

@Component
public class AskingNameState implements DialogStateStrategy {

    private final Api api;
    private final DialogStateService dialogStateService;

    public AskingNameState(Api api, DialogStateService dialogStateService) {
        this.api = api;
        this.dialogStateService = dialogStateService;
    }

    @Override
    public boolean apply(Update update) {
        return dialogStateService.checkDialogState(update.getChatId()) == DialogState.ASKING_NAME;
    }

    @Override
    public void process(Update update) {
        System.out.println("Name " + update.getMessageText());
        dialogStateService.putDialogState(update.getChatId(),
                DialogState.ASKING_EMAIL_FOR_VCARD);

        VCard vCard = new VCard();
        vCard.setName(update.getMessage().getText());
        dialogStateService.putDialogData(update.getChatId(), vCard);

        SendMessageParams sendMessageParams = new SendMessageParams()
                .setText("Введите Email")
                .setChatId(update.getChatId());
        api.sendMessage(sendMessageParams);
    }
}
