package com.khoa.bot.connector.facebook.service.order;

import com.khoa.bot.connector.facebook.adapter.model.Message;
import com.khoa.bot.connector.facebook.context.BotMessageContent;
import com.khoa.bot.connector.facebook.context.DialogContext;
import com.khoa.bot.connector.facebook.context.OrderContext;
import com.khoa.bot.connector.facebook.entity.OrderItem;
import com.khoa.bot.connector.facebook.entity.User;
import com.khoa.bot.connector.facebook.entity.enums.OptionType;
import com.khoa.bot.connector.facebook.entity.enums.UserOrderContextType;
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
public class ListCrustOrderHandler implements PizzaOrderStepHandler {
    private final ProductOptionService productOptionService;
    private final NotStartedOrderHandler notStartedOrderHandler;

    public ListCrustOrderHandler(ProductOptionService productOptionService, NotStartedOrderHandler notStartedOrderHandler) {
        this.productOptionService = productOptionService;
        this.notStartedOrderHandler = notStartedOrderHandler;
    }

    @Override
    public List<Message> getMessages(DialogContext dialogContext, BotMessageContent messageContent) {
        Optional<String> payloadOptional = getPayload(messageContent);
        if (payloadOptional.isPresent()) {
            if (NumberUtils.isParsable(payloadOptional.get())) {
                return this.handleUserSelectValidCrust(dialogContext, Long.parseLong(payloadOptional.get()));
            }
        }

        long productId = dialogContext.getOrderContext()
                .getContext()
                .getOrderItemList()
                .stream()
                .findFirst()
                .map(OrderItem::getProductId)
                .orElse(NumberUtils.LONG_ZERO);
        return handleUserSelectInvalidCrust(productId);
    }

    private List<Message> handleUserSelectInvalidCrust(long productId) {
        ArrayList<Message> messages = new ArrayList<>();
        messages.add(Message.fromText("L???a ch???n kh??ng c?? trong danh s??ch, vui l??ng ch???n l???i"));
        messages.addAll(productOptionService.getProductOptionList(productId, OptionType.CRUST));

        return messages;
    }

    private List<Message> handleUserSelectValidCrust(DialogContext dialogContext, long optionId) {
        OrderContext orderContext = dialogContext.getOrderContext();
        long productId = orderContext.getContext().getOrderItemList().stream().findFirst().map(OrderItem::getProductId)
                .orElse(NumberUtils.LONG_ZERO);

        User contextUser = dialogContext.getUserContext().getContext();
        if (productId <= NumberUtils.LONG_ZERO) {
            setNextStep(dialogContext, UserOrderContextType.CATALOGUE);
            ArrayList<Message> messages = new ArrayList<>();
            messages.add(Message.fromText("B???n ch??a ch???n s???n ph???m."));

            messages.addAll(notStartedOrderHandler.getMessages(dialogContext, null));
            return messages;
        } else {
            dialogContext.getOrderContext().updateOption(contextUser, optionId);
            setNextStep(dialogContext, UserOrderContextType.LIST_SIZE);
            return Stream.of(
                            productOptionService.getSelectOptionSuccess(optionId),
                            productOptionService.getProductOptionList(productId, OptionType.SIZE))
                    .flatMap(Collection::stream)
                    .collect(Collectors.toList());
        }
    }
}
