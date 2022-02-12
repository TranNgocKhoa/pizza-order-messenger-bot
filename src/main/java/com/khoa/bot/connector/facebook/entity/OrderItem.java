package com.khoa.bot.connector.facebook.entity;

import java.util.List;

public class OrderItem {
    private long orderId;
    private long productId;
    private long price;
    private List<OrderItemOption> orderItemOptionList;
    private long quantity;

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

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public List<OrderItemOption> getOrderItemOptionList() {
        return orderItemOptionList;
    }

    public void setOrderItemOptionList(List<OrderItemOption> orderItemOptionList) {
        this.orderItemOptionList = orderItemOptionList;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public static OrderItemBuilder builder() {
        return new OrderItemBuilder();
    }

    public static final class OrderItemBuilder {
        private long orderId;
        private long productId;
        private long price;
        private List<OrderItemOption> orderItemOptionList;
        private long quantity;

        private OrderItemBuilder() {
        }

        public OrderItemBuilder orderId(long orderId) {
            this.orderId = orderId;
            return this;
        }

        public OrderItemBuilder productId(long productId) {
            this.productId = productId;
            return this;
        }

        public OrderItemBuilder price(long price) {
            this.price = price;
            return this;
        }

        public OrderItemBuilder orderItemOptionList(List<OrderItemOption> orderItemOptionList) {
            this.orderItemOptionList = orderItemOptionList;
            return this;
        }

        public OrderItemBuilder quantity(long quantity) {
            this.quantity = quantity;
            return this;
        }

        public OrderItem build() {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrderId(orderId);
            orderItem.setProductId(productId);
            orderItem.setPrice(price);
            orderItem.setOrderItemOptionList(orderItemOptionList);
            orderItem.setQuantity(quantity);
            return orderItem;
        }
    }
}
