package com.khoa.bot.connector.facebook.adapter.event;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

public class IncomingEventEntry {
    private String id;
    private long time;
    private List<MessengerEvent> messaging;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public List<MessengerEvent> getMessaging() {
        return messaging;
    }

    public void setMessaging(List<MessengerEvent> messaging) {
        this.messaging = messaging;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
