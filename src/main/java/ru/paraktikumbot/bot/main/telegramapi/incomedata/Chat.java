package ru.paraktikumbot.bot.main.telegramapi.incomedata;

public class Chat {
    private Integer id;
    private String type;
    private String username;

    public Integer getId() {
        return id;
    }

    public Chat setId(Integer id) {
        this.id = id;
        return this;
    }

    public Chat setType(String type) {
        this.type = type;
        return this;
    }

    public Chat setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getType() {
        return type;
    }

    public String getUsername() {
        return username;
    }

    public Chat setMessageId(Integer id) {
        this.id = id;
        return this;
    }
}
