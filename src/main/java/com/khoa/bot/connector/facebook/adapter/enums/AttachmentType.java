package com.khoa.bot.connector.facebook.adapter.enums;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Type of attachment, may be image, audio, video, file or template. For assets, max file size is 25MB.
 */
public enum AttachmentType {
    IMAGE("image"),
    AUDIO("audio"),
    VIDEO("video"),
    FILE("file"),
    FALLBACK("fallback"),
    LOCATION("location"),
    TEMPLATE("template");

    AttachmentType(String value) {
        this.value = value;
    }

    @JsonValue
    private final String value;

    public String getValue() {
        return value;
    }
}
