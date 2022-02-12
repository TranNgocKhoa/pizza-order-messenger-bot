package com.khoa.bot.connector.facebook.entity;

import com.khoa.bot.connector.facebook.adapter.model.AbstractBaseModel;

public class CatalogueData extends AbstractBaseModel {
    private long offset;
    private long limit;

    public CatalogueData() {
        offset = 0;
        limit = 10;
    }

    public long getOffset() {
        return offset;
    }

    public void setOffset(long offset) {
        this.offset = offset;
    }

    public long getLimit() {
        return limit;
    }

    public void setLimit(long limit) {
        this.limit = limit;
    }
}
