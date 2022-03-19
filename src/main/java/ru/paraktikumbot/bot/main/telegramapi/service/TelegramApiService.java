package ru.paraktikumbot.bot.main.telegramapi.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import ru.paraktikumbot.bot.main.api.Api;
import ru.paraktikumbot.bot.main.telegramapi.incomedata.Message;
import ru.paraktikumbot.bot.main.telegramapi.outcomedata.SendMessageParams;
import ru.paraktikumbot.bot.main.telegramapi.outcomedata.SendPhotoParams;

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
    }

    @Override
    public void sendPhoto(SendPhotoParams sendPhotoParams) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("photo", new FileSystemResource(sendPhotoParams.getPhoto()));
        body.add("chat_id", sendPhotoParams.getChatId());

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        ResponseEntity<Message> responseSendPhoto = restTemplate
                .postForEntity(telegramApi+"/sendPhoto", requestEntity, Message.class);
    }
}
