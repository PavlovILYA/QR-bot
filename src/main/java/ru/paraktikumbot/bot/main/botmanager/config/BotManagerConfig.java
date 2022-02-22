package ru.paraktikumbot.bot.main.botmanager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.paraktikumbot.bot.main.botmanager.BotManager;

@Configuration
public class BotManagerConfig {

    @Bean
    public BotManager botManager() {
        return new BotManager();
    }
}
