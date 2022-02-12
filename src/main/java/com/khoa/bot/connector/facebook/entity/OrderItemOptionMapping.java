package com.khoa.bot.connector.facebook.entity;

import com.khoa.bot.connector.facebook.adapter.model.AbstractBaseModel;

public class OrderItemOptionMapping extends AbstractBaseModel {
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

    public static OrderItemOptionMappingBuilder builder() {
        return new OrderItemOptionMappingBuilder();
    }

    public static final class OrderItemOptionMappingBuilder {
        private long orderId;
        private long productId;
        private long optionId;
        private long extraFee;

        private OrderItemOptionMappingBuilder() {
        }

        public OrderItemOptionMappingBuilder orderId(long orderId) {
            this.orderId = orderId;
            return this;
        }

        public OrderItemOptionMappingBuilder productId(long productId) {
            this.productId = productId;
            return this;
        }

        public OrderItemOptionMappingBuilder optionId(long optionId) {
            this.optionId = optionId;
            return this;
        }

        public OrderItemOptionMappingBuilder extraFee(long extraFee) {
            this.extraFee = extraFee;
            return this;
        }

        public OrderItemOptionMapping build() {
            OrderItemOptionMapping orderItemOptionMapping = new OrderItemOptionMapping();
            orderItemOptionMapping.setOrderId(orderId);
            orderItemOptionMapping.setProductId(productId);
            orderItemOptionMapping.setOptionId(optionId);
            orderItemOptionMapping.setExtraFee(extraFee);
            return orderItemOptionMapping;
        }
    }
}
