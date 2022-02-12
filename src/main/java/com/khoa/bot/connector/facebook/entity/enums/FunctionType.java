package com.khoa.bot.connector.facebook.entity.enums;

import java.util.Arrays;

public enum FunctionType {
    ORDER("start_order", "Đặt Pizza \uD83C\uDF55"),
    HELP("help", "Hỗ trợ ❓"),
    LOOKUP("lookup", "Tra cứu đơn hàng \uD83D\uDD0D");

    private final String payload;
    private final String uiValue;

    FunctionType(String payload, String uiValue) {
        this.payload = payload;
        this.uiValue = uiValue;
    }

    public String getPayload() {
        return payload;
    }

    public String getUiValue() {
        return uiValue;
    }

    public static FunctionType fromPayload(String payload) {
        return Arrays.stream(values())
                .filter(functionType -> functionType.payload.equalsIgnoreCase(payload))
                .findFirst().orElse(null);
    }
}
