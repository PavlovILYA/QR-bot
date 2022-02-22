package ru.paraktikumbot.bot.main.botmanager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.paraktikumbot.bot.main.botmanager.service.BotManagerService;

@Configuration
public class BotManagerConfig {

    @Bean
    public BotManagerService botManager() {
        return new BotManagerService();
    }
}
