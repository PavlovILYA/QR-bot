package ru.paraktikumbot.bot.main.telegramapi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.paraktikumbot.bot.main.api.Api;
import ru.paraktikumbot.bot.main.telegramapi.incomedata.Message;
import ru.paraktikumbot.bot.main.telegramapi.outcomedata.SendMessageParams;

@Service
public class TelegramApiService implements Api {

    private final RestTemplate restTemplate;
    @Value("${telegram.api}")
    private String telegramApi;

    public TelegramApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void sendMessage(SendMessageParams sendMessageParams) {
        ResponseEntity<Message> responseSendMessage = restTemplate
                .postForEntity(telegramApi+"/sendMessage", sendMessageParams, Message.class);
        System.out.println(responseSendMessage.getBody());
    }
}
