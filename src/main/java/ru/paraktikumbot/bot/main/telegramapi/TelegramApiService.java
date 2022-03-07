package ru.paraktikumbot.bot.main.telegramapi;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import ru.paraktikumbot.bot.main.api.Api;
import ru.paraktikumbot.bot.main.telegramapi.incomedata.Message;
import ru.paraktikumbot.bot.main.telegramapi.outcomedata.SendMessageParams;

public class TelegramApiService implements Api {

    private final RestTemplate restTemplate;
    private final String telegramApi;

    public TelegramApiService(RestTemplate restTemplate, String telegramApi) {
        this.restTemplate = restTemplate;
        this.telegramApi = telegramApi;
    }

    @Override
    public void sendMessage(SendMessageParams sendMessageParams) {
        ResponseEntity<Message> responseSendMessage = restTemplate
                .postForEntity(telegramApi+"/sendMessage", sendMessageParams, Message.class);
        System.out.println(responseSendMessage.getBody());
    }
}
