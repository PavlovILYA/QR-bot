package ru.paraktikumbot.bot.main.botmanager.strategy.dialogstate;

import net.glxn.qrgen.core.image.ImageType;
import net.glxn.qrgen.core.scheme.YouTube;
import net.glxn.qrgen.javase.QRCode;
import org.springframework.stereotype.Component;
import ru.paraktikumbot.bot.main.api.Api;
import ru.paraktikumbot.bot.main.botmanager.model.DialogState;
import ru.paraktikumbot.bot.main.botmanager.service.DialogStateService;
import ru.paraktikumbot.bot.main.common.model.Update;
import ru.paraktikumbot.bot.main.telegramapi.outcomedata.SendPhotoParams;

import java.io.File;

@Component
public class AskingYoutubeState implements DialogStateStrategy {

    private final Api api;
    private final DialogStateService dialogStateService;

    public AskingYoutubeState(Api api, DialogStateService dialogStateService) {
        this.api = api;
        this.dialogStateService = dialogStateService;
    }

    @Override
    public boolean apply(Update update) {
        return dialogStateService.checkDialogState(update.getChatId()) == DialogState.ASKING_YOUTUBE;
    }

    @Override
    public void process(Update update) {
        System.out.println("Youtube " + update.getMessageText());
        dialogStateService.putDialogState(update.getChatId(),
                DialogState.INITIAL);

        YouTube youTube = new YouTube();
        youTube.setVideoId(update.getMessageText());

        File file = QRCode.from(youTube).withCharset("UTF-8").to(ImageType.PNG)
                .withSize(200, 200)
                .file();

        SendPhotoParams sendPhotoParams = new SendPhotoParams()
                .setChatId(update.getChatId())
                .setPhoto(file);
        api.sendPhoto(sendPhotoParams);
    }
}
