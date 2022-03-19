package ru.paraktikumbot.bot.main.botmanager.strategy.dialogstate;

import net.glxn.qrgen.core.image.ImageType;
import net.glxn.qrgen.core.scheme.GeoInfo;
import net.glxn.qrgen.javase.QRCode;
import org.springframework.stereotype.Component;
import ru.paraktikumbot.bot.main.api.Api;
import ru.paraktikumbot.bot.main.botmanager.model.DialogState;
import ru.paraktikumbot.bot.main.botmanager.service.DialogStateService;
import ru.paraktikumbot.bot.main.common.model.Update;
import ru.paraktikumbot.bot.main.telegramapi.outcomedata.SendMessageParams;
import ru.paraktikumbot.bot.main.telegramapi.outcomedata.SendPhotoParams;

import java.io.File;
import java.util.Arrays;

@Component
public class AskingCoordsState implements DialogStateStrategy {

    private final Api api;
    private final DialogStateService dialogStateService;

    public AskingCoordsState(Api api, DialogStateService dialogStateService) {
        this.api = api;
        this.dialogStateService = dialogStateService;
    }

    @Override
    public boolean apply(Update update) {
        return dialogStateService.checkDialogState(update.getChatId()) == DialogState.ASKING_COORDS;
    }

    @Override
    public void process(Update update) {
        System.out.println("Coords " + update.getMessageText());

        try {
            String[] coords = update.getMessage().getText().split(" ");
            if (coords.length != 2) {
                throw new NumberFormatException();
            }
            double coord = Double.parseDouble(coords[0]);
            coord = Double.parseDouble(coords[1]);

            dialogStateService.putDialogState(update.getChatId(),
                    DialogState.INITIAL);
            GeoInfo geoInfo = new GeoInfo();
            geoInfo.setPoints(Arrays.asList(coords));

            File file = QRCode.from(geoInfo).withCharset("UTF-8").to(ImageType.PNG)
                .withSize(200, 200)
                .file();

            SendPhotoParams sendPhotoParams = new SendPhotoParams()
                .setChatId(update.getChatId())
                .setPhoto(file);
            api.sendPhoto(sendPhotoParams);

        } catch (NumberFormatException e) {
            SendMessageParams sendMessageParams = new SendMessageParams()
                    .setText("НЕВЕРНЫЙ ВВОД! Попробуйте еще раз\n" +
                            "Введите координаты гео-точки (пример: 59.12847 38.00914)")
                    .setChatId(update.getChatId());
            api.sendMessage(sendMessageParams);
        }
    }
}
