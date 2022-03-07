package ru.paraktikumbot.bot.main.telegramapi.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.paraktikumbot.bot.main.telegramapi.outcomedata.SendMessageParams;

@Service
public class TelegramRequestService {
    private final RestTemplate restTemplate;

    @Value("${telegram.api}")
    private String telegramApi;

    public TelegramRequestService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void sendMessage(Integer chatId, String text) {
        SendMessageParams sendMessageParams = new SendMessageParams(chatId, text);
        ResponseEntity<Object> responseSendMessage = restTemplate
                .postForEntity(telegramApi+"/sendMessage", sendMessageParams, Object.class);
        System.out.println(responseSendMessage.getBody());
    }
}
