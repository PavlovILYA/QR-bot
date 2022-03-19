package ru.paraktikumbot.bot.main.botmanager.strategy;

import net.glxn.qrgen.core.image.ImageType;
import net.glxn.qrgen.core.scheme.*;
import net.glxn.qrgen.javase.QRCode;
import org.springframework.stereotype.Component;
import ru.paraktikumbot.bot.main.api.Api;
import ru.paraktikumbot.bot.main.botmanager.model.BotCommand;
import ru.paraktikumbot.bot.main.common.model.Update;
import ru.paraktikumbot.bot.main.telegramapi.outcomedata.SendMessageParams;
import ru.paraktikumbot.bot.main.telegramapi.outcomedata.SendPhotoParams;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

@Component
public class BotCommandContext {

    private final List<BotCommandStrategy> strategies;
    private final Api api;
    private boolean isWaitingForQrData = false;

    public BotCommandContext(List<BotCommandStrategy> strategies, Api api) {
        this.strategies = strategies;
        this.api = api;
    }

    public void process(Update update) {

        if (isWaitingForQrData) {
            checkQrData(update);
            return;
        }

        for (BotCommandStrategy strategy : strategies) {
            if (strategy.apply(update)) {
                isWaitingForQrData = strategy.process(update);
                return;
            }
        }
    }

    public void checkQrData(Update update) {
        System.out.println("Qr data " + update.getMessage().getText());

        GeoInfo geoInfo = new GeoInfo();
        geoInfo.setPoints(Arrays.asList("59.12847", "38.00914"));
//        SMS sms = new SMS();
//        sms.setSubject("ты жопа");
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
                .setChatId(update.getMessage().getChat().getId())
                .setPhoto(photo);

        SendMessageParams sendMessageParams = new SendMessageParams()
                .setText("Ловите QR #")
                .setChatId(update.getMessage().getChat().getId());
        api.sendMessage(sendMessageParams);
        api.sendPhoto(sendPhotoParams);
        isWaitingForQrData = false;
    }
}
