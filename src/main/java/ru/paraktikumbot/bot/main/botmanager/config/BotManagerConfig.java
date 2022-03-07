package ru.paraktikumbot.bot.main.botmanager.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import ru.paraktikumbot.bot.main.api.Api;
import ru.paraktikumbot.bot.main.botmanager.service.BotManagerService;
import ru.paraktikumbot.bot.main.botmanager.strategy.BotCommandContext;
import ru.paraktikumbot.bot.main.botmanager.strategy.BotCommandStrategy;
import ru.paraktikumbot.bot.main.botmanager.strategy.HelpCommand;
import ru.paraktikumbot.bot.main.botmanager.strategy.StartCommand;
import ru.paraktikumbot.bot.main.telegramapi.TelegramApiService;
import ru.paraktikumbot.bot.main.telegramapi.helpers.BotCommandHelper;

import java.util.Arrays;
import java.util.List;

@Configuration
public class BotManagerConfig {

    @Value("${telegram.api}")
    private String telegramApi;

    @Bean
    public BotManagerService botManager() {
        BotCommandHelper botCommandHelper = new BotCommandHelper();

        RestTemplate restTemplate = new RestTemplate();
        Api telegramApiService = new TelegramApiService(restTemplate, telegramApi);

        BotCommandStrategy helpCommand = new HelpCommand(telegramApiService, botCommandHelper);
        BotCommandStrategy startCommand = new StartCommand(telegramApiService, botCommandHelper);
        List<BotCommandStrategy> strategies = Arrays.asList(helpCommand, startCommand);

        BotCommandContext context = new BotCommandContext(strategies);
        return new BotManagerService(context);
    }
}
