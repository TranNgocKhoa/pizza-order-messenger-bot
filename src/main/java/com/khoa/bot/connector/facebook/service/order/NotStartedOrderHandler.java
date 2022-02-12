package com.khoa.bot.connector.facebook.service.order;

import com.khoa.bot.connector.facebook.adapter.model.Message;
import com.khoa.bot.connector.facebook.context.BotMessageContent;
import com.khoa.bot.connector.facebook.context.DialogContext;
import com.khoa.bot.connector.facebook.entity.CatalogueData;
import com.khoa.bot.connector.facebook.entity.Order;
import com.khoa.bot.connector.facebook.entity.enums.UserOrderContextType;
import com.khoa.bot.connector.facebook.service.ProductListService;
import org.springframework.stereotype.Component;

import javax.annotation.Nullable;
import java.util.List;

@Component
public class NotStartedOrderHandler implements PizzaOrderStepHandler {
    private final ProductListService productListService;

    public NotStartedOrderHandler(ProductListService productListService) {
        this.productListService = productListService;
    }

    @Override
    public List<Message> getMessages(DialogContext dialogContext, @Nullable BotMessageContent botMessageContent) {
        Order order = dialogContext.getOrderContext().getContext();
        CatalogueData catalogueData = order.getCatalogueData();

        setNextStep(dialogContext, UserOrderContextType.CATALOGUE);

        return productListService.getProductListMessage(catalogueData.getOffset(), catalogueData.getLimit());
    }
}
