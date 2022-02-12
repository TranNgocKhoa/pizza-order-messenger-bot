package com.khoa.bot.connector.facebook.adapter.enums;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

public enum ActionType {
    WEB_URL("web_url"),
    POSTBACK("postback");

    private final String value;

    ActionType(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    public static ActionType fromValue(String value) {
        return Arrays.stream(values()).filter(actionType -> actionType.getValue().equalsIgnoreCase(value)).findFirst().orElse(null);
    }
}
