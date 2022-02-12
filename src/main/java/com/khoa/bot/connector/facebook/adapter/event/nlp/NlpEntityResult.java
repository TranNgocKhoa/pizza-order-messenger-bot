package com.khoa.bot.connector.facebook.adapter.event.nlp;

public class NlpEntityResult {
    private float confidence;
    private String value;

    public float getConfidence() {
        return confidence;
    }

    public void setConfidence(float confidence) {
        this.confidence = confidence;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
