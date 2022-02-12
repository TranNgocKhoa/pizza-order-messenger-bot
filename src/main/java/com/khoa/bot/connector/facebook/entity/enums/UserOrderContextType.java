package com.khoa.bot.connector.facebook.entity.enums;

import com.khoa.bot.connector.facebook.service.order.*;

public enum UserOrderContextType {
    NOT_STARTED(NotStartedOrderHandler.class),
    CATALOGUE(CatalogueOrderHandler.class),
    LIST_CRUST(ListCrustOrderHandler.class),
    LIST_SIZE(ListSizeOrderHandler.class),
    LIST_EXTRAS(ListExtrasOrderHandler.class),
    LIST_OUTER_CRUST(ListOuterCrustOrderHandler.class),
    ADDRESS_DECLARE(AddressOrderHandler.class),
    PHONE_NUMBER_DECLARE(PhoneNumberOrderHandler.class),
    COMPLETE(null);

    UserOrderContextType(Class<? extends PizzaOrderStepHandler> handler) {
        this.handler = handler;
    }

    private final Class<? extends PizzaOrderStepHandler> handler;

    public Class<? extends PizzaOrderStepHandler> getHandler() {
        return handler;
    }
}
