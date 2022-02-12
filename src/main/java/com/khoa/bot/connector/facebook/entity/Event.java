package com.khoa.bot.connector.facebook.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public class Event {
    private String id;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss:fff")
    private LocalDateTime dateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
