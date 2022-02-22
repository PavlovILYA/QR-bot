package ru.paraktikumbot.bot.main.common.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Chat {
    @JsonProperty(value = "id")
    private Integer id;

    @JsonProperty(value = "type")
    private String type;

    @JsonProperty(value = "username")
    private String username;

    public Integer getId() {
        return id;
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
