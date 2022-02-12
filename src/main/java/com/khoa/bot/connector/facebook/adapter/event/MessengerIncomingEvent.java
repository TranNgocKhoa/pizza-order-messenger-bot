package com.khoa.bot.connector.facebook.adapter.event;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

public class MessengerIncomingEvent {
    private String object;
    private List<IncomingEventEntry> entry;

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public List<IncomingEventEntry> getEntry() {
        return entry;
    }

    public void setEntry(List<IncomingEventEntry> entry) {
        this.entry = entry;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }


}
