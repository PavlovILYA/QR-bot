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
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

@Component
public class AskingQrDataState implements DialogStateStrategy {

    private final Api api;
    private final DialogStateService dialogStateService;

    public AskingQrDataState(Api api, DialogStateService dialogStateService) {
        this.api = api;
        this.dialogStateService = dialogStateService;
    }

    @Override
    public boolean apply(Update update) {
        return dialogStateService.checkDialogState(update.getChatId()) == DialogState.ASKING_QR_DATA;
    }

    @Override
    public void process(Update update) {
        dialogStateService.putDialogState(update.getChatId(),
                DialogState.INITIAL);

        System.out.println("Qr data " + update.getMessage().getText());

        GeoInfo geoInfo = new GeoInfo();
        geoInfo.setPoints(Arrays.asList("59.12847", "38.00914"));

        File file = QRCode.from(geoInfo).to(ImageType.PNG)
                .withSize(200, 200)
                .file();

//        File file = QRCode.from("www.google.com").to(ImageType.PNG)
//                .withSize(200, 200)
//                .file();

        String fileName = "qrgen-qrcode.png";

        try {
            Path path = Paths.get(fileName);
            if ( Files.exists(path)){
                    Files.delete(path);
            }
            Files.copy(file.toPath(), path);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        MeCard johnDoe = new MeCard("John Doe");
//        johnDoe.setEmail("john.doe@example.org");
//        johnDoe.setAddress("John Doe Street 1, 5678 Doestown");
//        johnDoe.setTelephone("1234");
//        QRCode.from(johnDoe).file();
        File photo = new File("qrgen-qrcode.png");
        System.out.println(file.exists());

        SendPhotoParams sendPhotoParams = new SendPhotoParams()
                .setChatId(update.getChatId())
                .setPhoto(photo);

        SendMessageParams sendMessageParams = new SendMessageParams()
                .setText("Ловите QR #")
                .setChatId(update.getChatId());
        api.sendMessage(sendMessageParams);
        api.sendPhoto(sendPhotoParams);
    }
}
