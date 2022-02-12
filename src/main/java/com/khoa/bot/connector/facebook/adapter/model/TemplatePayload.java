package com.khoa.bot.connector.facebook.adapter.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class TemplatePayload extends AttachmentPayload {
    @JsonProperty("template_type")
    protected String templateType;
    private List<TemplatePayloadElement> elements;

    public String getTemplateType() {
        return templateType;
    }

    public void setTemplateType(String templateType) {
        this.templateType = templateType;
    }

    public List<TemplatePayloadElement> getElements() {
        return elements;
    }

    public void setElements(List<TemplatePayloadElement> elements) {
        this.elements = elements;
    }
}
