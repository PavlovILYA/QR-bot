package ru.paraktikumbot.bot.main.botmanager.strategy.dialogstate;

import net.glxn.qrgen.core.image.ImageType;
import net.glxn.qrgen.core.scheme.EMail;
import net.glxn.qrgen.javase.QRCode;
import org.springframework.stereotype.Component;
import ru.paraktikumbot.bot.main.api.Api;
import ru.paraktikumbot.bot.main.botmanager.model.DialogState;
import ru.paraktikumbot.bot.main.botmanager.service.DialogStateService;
import ru.paraktikumbot.bot.main.common.model.Update;
import ru.paraktikumbot.bot.main.telegramapi.outcomedata.SendPhotoParams;

import java.io.File;

@Component
public class AskingEmailState implements DialogStateStrategy {

    private final Api api;
    private final DialogStateService dialogStateService;

    public AskingEmailState(Api api, DialogStateService dialogStateService) {
        this.api = api;
        this.dialogStateService = dialogStateService;
    }

    @Override
    public boolean apply(Update update) {
        return dialogStateService.checkDialogState(update.getChatId()) == DialogState.ASKING_EMAIL;
    }

    @Override
    public void process(Update update) {
        System.out.println("Email " + update.getMessageText());
        dialogStateService.putDialogState(update.getChatId(),
                DialogState.INITIAL);
        EMail eMail = new EMail();
        eMail.setEmail(update.getMessageText());

        File file = QRCode.from(eMail).withCharset("UTF-8").to(ImageType.PNG)
                .withSize(200, 200)
                .file();

        SendPhotoParams sendPhotoParams = new SendPhotoParams()
                .setChatId(update.getChatId())
                .setPhoto(file);
        api.sendPhoto(sendPhotoParams);
    }
}
