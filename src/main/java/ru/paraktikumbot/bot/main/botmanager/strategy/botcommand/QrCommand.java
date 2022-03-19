package ru.paraktikumbot.bot.main.botmanager.strategy.botcommand;

import org.springframework.stereotype.Component;
import ru.paraktikumbot.bot.main.api.Api;
import ru.paraktikumbot.bot.main.botmanager.model.BotCommand;
import ru.paraktikumbot.bot.main.botmanager.model.DialogState;
import ru.paraktikumbot.bot.main.botmanager.service.DialogStateService;
import ru.paraktikumbot.bot.main.common.model.Update;
import ru.paraktikumbot.bot.main.telegramapi.helpers.BotCommandHelper;
import ru.paraktikumbot.bot.main.telegramapi.outcomedata.SendMessageParams;

@Component
public class QrCommand implements BotCommandStrategy {

    private final Api api;
    private final BotCommandHelper helper;
    private final DialogStateService dialogStateService;

    public QrCommand(Api api, BotCommandHelper botCommandHelper, DialogStateService dialogStateService) {
        this.api = api;
        this.helper = botCommandHelper;
        this.dialogStateService = dialogStateService;
    }

    @Override
    public boolean apply(Update update) {
        return helper.checkCommand(BotCommand.QR, update);
    }

    @Override
    public void process(Update update) {
        System.out.println("Qr command strategy " + update.getMessage().getText());
        SendMessageParams sendMessageParams = new SendMessageParams()
                .setText("Отправьте следующим соообщением инфоормацию6 которую хотите завернуть в QR :)")
                .setChatId(update.getMessage().getChat().getId());
        api.sendMessage(sendMessageParams);
        dialogStateService.putDialogState(update.getMessage().getChat().getId(), DialogState.ASKING_QR_DATA);
    }
}
