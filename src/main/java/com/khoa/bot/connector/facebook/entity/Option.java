package com.khoa.bot.connector.facebook.entity;

import com.khoa.bot.connector.facebook.adapter.model.AbstractBaseModel;
import com.khoa.bot.connector.facebook.entity.enums.OptionType;

public class Option extends AbstractBaseModel {
    private long id;
    private OptionType type;
    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public OptionType getType() {
        return type;
    }

    public void setType(OptionType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
