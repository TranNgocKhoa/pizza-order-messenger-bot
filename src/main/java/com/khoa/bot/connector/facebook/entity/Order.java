package com.khoa.bot.connector.facebook.entity;

import java.util.List;

public class Order {
    private long id;
    private long userId;
    private CatalogueData catalogueData;
    private List<OrderItem> orderItemList;
    private Address address;
    private String status;

    private Product firstProduct;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public CatalogueData getCatalogueData() {
        return catalogueData;
    }

    public void setCatalogueData(CatalogueData catalogueData) {
        this.catalogueData = catalogueData;
    }

    public List<OrderItem> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(List<OrderItem> orderItemList) {
        this.orderItemList = orderItemList;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setFirstProduct(Product firstProduct) {
        this.firstProduct = firstProduct;
    }

    public Product getFirstProduct() {
        return firstProduct;
    }

    public String getOrderTitle() {
        if (getOrderItemList().size() > 1) {
            return firstProduct.getName() + " và " + orderItemList.size() + " sản phẩm khác.";
        }

        return firstProduct.getName();
    }

    public String getStatus() {
        return status;
    }

    public String getPlainStatus() {
        return "Trạng thái: " + status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
