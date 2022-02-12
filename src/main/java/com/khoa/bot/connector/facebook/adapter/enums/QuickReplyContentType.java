package com.khoa.bot.connector.facebook.adapter.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum QuickReplyContentType {
    TEXT("text"),
    USER_PHONE_NUMBER("user_phone_number"),
    USER_EMAIL("user_email");

    QuickReplyContentType(String value) {
        this.value = value;
    }

    private final String value;

    @JsonValue
    public String getValue() {
        return value;
    }
}
