package com.khoa.bot.connector.facebook.entity;

import com.khoa.bot.connector.facebook.entity.enums.OptionType;

public class ProductOption {
    private long productId;
    private long optionId;
    private OptionType type;
    private String name;
    private long extraFee;

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public long getOptionId() {
        return optionId;
    }

    public void setOptionId(long optionId) {
        this.optionId = optionId;
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

    public long getExtraFee() {
        return extraFee;
    }

    public void setExtraFee(long extraFee) {
        this.extraFee = extraFee;
    }

    public String getFormattedExtraPrice() {
        return String.format("+ %,dđ", extraFee);
    }
}
