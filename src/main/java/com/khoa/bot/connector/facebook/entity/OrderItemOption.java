package com.khoa.bot.connector.facebook.entity;

public class OrderItemOption {
    private long orderId;
    private long productId;
    private long optionId;
    private long extraFee;

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

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

    public long getExtraFee() {
        return extraFee;
    }

    public void setExtraFee(long extraFee) {
        this.extraFee = extraFee;
    }
}
