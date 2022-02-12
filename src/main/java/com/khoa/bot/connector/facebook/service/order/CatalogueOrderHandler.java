package com.khoa.bot.connector.facebook.service.order;

import com.khoa.bot.connector.facebook.adapter.model.Message;
import com.khoa.bot.connector.facebook.context.BotMessageContent;
import com.khoa.bot.connector.facebook.context.DialogContext;
import com.khoa.bot.connector.facebook.entity.CatalogueData;
import com.khoa.bot.connector.facebook.entity.enums.OptionType;
import com.khoa.bot.connector.facebook.entity.enums.UserOrderContextType;
import com.khoa.bot.connector.facebook.service.ProductListService;
import com.khoa.bot.connector.facebook.service.ProductOptionService;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class CatalogueOrderHandler implements PizzaOrderStepHandler {
    private final ProductListService productListService;
    private final ProductOptionService productOptionService;

    public CatalogueOrderHandler(ProductListService productListService, ProductOptionService productOptionService) {
        this.productListService = productListService;
        this.productOptionService = productOptionService;
    }

    @Override
    public List<Message> getMessages(DialogContext dialogContext, BotMessageContent messageContent) {
        Optional<String> payloadOptional = getPayload(messageContent);
        if (payloadOptional.isPresent()) {
            if (NumberUtils.isParsable(payloadOptional.get())) {
                return handleUserSelectValidProduct(dialogContext, Long.parseLong(payloadOptional.get()));
            }
        }

        CatalogueData catalogueData = dialogContext.getOrderContext().getContext().getCatalogueData();
        return userSelectProductNotInList(catalogueData);
    }

    private List<Message> userSelectProductNotInList(CatalogueData catalogueData) {
        ArrayList<Message> messages = new ArrayList<>();
        messages.add(Message.fromText("Sản phẩm không có trong danh sách! Vui lòng chọn lại"));
        messages.addAll(productListService.getProductListMessage(catalogueData.getOffset(), catalogueData.getLimit()));

        return messages;
    }

    private List<Message> handleUserSelectValidProduct(DialogContext dialogContext, long productId) {
        setNextStep(dialogContext, UserOrderContextType.LIST_CRUST);
        dialogContext.getOrderContext().updateProduct(dialogContext.getUserContext().getContext(), productId);

        return Stream.of(
                        productListService.getSelectProductSuccess(productId),
                        productOptionService.getProductOptionList(productId, OptionType.CRUST))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }
}
