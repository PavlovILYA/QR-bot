package ru.paraktikumbot.bot.main.botmanager.strategy.dialogstate;

import org.springframework.stereotype.Component;
import ru.paraktikumbot.bot.main.api.Api;
import ru.paraktikumbot.bot.main.botmanager.model.DialogState;
import ru.paraktikumbot.bot.main.botmanager.service.DialogStateService;
import ru.paraktikumbot.bot.main.common.model.Update;
import ru.paraktikumbot.bot.main.telegramapi.outcomedata.SendMessageParams;


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
        System.out.println("Qr data type " + update.getMessageText());
        String output;

        try {
            int qrDataType = Integer.parseInt(update.getMessageText());

            switch (qrDataType) {
                case 1:
                    dialogStateService.putDialogState(update.getChatId(),
                            DialogState.ASKING_COORDS);
                    output = "Введите координаты гео-точки (пример: 59.12847 38.00914)";
                    break;
                case 2:
                    dialogStateService.putDialogState(update.getChatId(),
                            DialogState.ASKING_EMAIL);
                    output = "Введите Email";
                    break;
                case 3:
                    dialogStateService.putDialogState(update.getChatId(),
                            DialogState.ASKING_NAME);
                    output = "Введите ФИО";
                    break;
                case 4:
                    dialogStateService.putDialogState(update.getChatId(),
                            DialogState.ASKING_YOUTUBE);
                    output = "Введите video id\n" +
                            "(Как определить video id: http://soc-service.com/html/yb_id_video.html)";
                    break;
                default:
                    throw new NumberFormatException();
            }

        } catch (NumberFormatException e) {
            output = "НЕВЕРНЫЙ ВВОД! Попробуйте еще раз\n" +
                    "Выберите тип данных, которые будут конвертированы в QR-код:" +
                    "\n 1 - Гео-точка\n 2 - Email\n 3 - Визитная карточка\n 4 - YouTube";
            System.out.println(dialogStateService.getDialogState(update.getChatId()));
        }

        SendMessageParams sendMessageParams = new SendMessageParams()
                .setText(output)
                .setChatId(update.getChatId());
        api.sendMessage(sendMessageParams);
    }
}
