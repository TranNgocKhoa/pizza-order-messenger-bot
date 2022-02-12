package com.khoa.bot.connector.facebook.adapter.event.content.message;

import java.util.List;

public class Product {
    private List<ProductElement> elements;

    public List<ProductElement> getElements() {
        return elements;
    }

    public void setElements(List<ProductElement> elements) {
        this.elements = elements;
    }
}
