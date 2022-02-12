package com.khoa.bot.connector.facebook.service.order;

import com.khoa.bot.connector.facebook.adapter.model.Message;
import com.khoa.bot.connector.facebook.context.BotMessageContent;
import com.khoa.bot.connector.facebook.context.DialogContext;
import com.khoa.bot.connector.facebook.entity.OrderItem;
import com.khoa.bot.connector.facebook.entity.User;
import com.khoa.bot.connector.facebook.entity.enums.OptionType;
import com.khoa.bot.connector.facebook.entity.enums.UserOrderContextType;
import com.khoa.bot.connector.facebook.service.AddressService;
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
public class ListOuterCrustOrderHandler implements PizzaOrderStepHandler {
    private final NotStartedOrderHandler notStartedOrderHandler;
    private final ProductOptionService productOptionService;
    private final AddressService addressService;

    public ListOuterCrustOrderHandler(NotStartedOrderHandler notStartedOrderHandler, ProductOptionService productOptionService, AddressService addressService) {
        this.notStartedOrderHandler = notStartedOrderHandler;
        this.productOptionService = productOptionService;
        this.addressService = addressService;
    }

    @Override
    public List<Message> getMessages(DialogContext dialogContext, BotMessageContent messageContent) {
        Optional<String> payloadOptional = getPayload(messageContent);
        long productId = dialogContext.getOrderContext().getContext().getOrderItemList().stream().findFirst().map(OrderItem::getProductId)
                .orElse(NumberUtils.LONG_ZERO);
        if (productId <= NumberUtils.LONG_ZERO) {
            return handleProductNotChosen(dialogContext, messageContent);
        }
        if (payloadOptional.isPresent()) {
            if (NumberUtils.isParsable(payloadOptional.get())) {
                return handleUserSelectValidOuterCrust(dialogContext, Long.parseLong(payloadOptional.get()));
            }
        }

        return handleUserSelectInvalidOuterCrust(productId);
    }

    private List<Message> handleUserSelectValidOuterCrust(DialogContext dialogContext, long optionId) {
        User contextUser = dialogContext.getUserContext().getContext();

        dialogContext.getOrderContext().updateOption(contextUser, optionId);
        dialogContext.getUserContext().updateOrderContext(contextUser, UserOrderContextType.ADDRESS_DECLARE);
        return Stream.of(
                        productOptionService.getSelectOptionSuccess(optionId),
                        addressService.getInputAddressMessage())
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    private ArrayList<Message> handleProductNotChosen(DialogContext dialogContext, BotMessageContent messageContent) {
        setNextStep(dialogContext, UserOrderContextType.CATALOGUE);
        ArrayList<Message> messages = new ArrayList<>();
        messages.add(Message.fromText("Bạn chưa chọn sản phẩm."));
        messages.addAll(notStartedOrderHandler.getMessages(dialogContext, messageContent));

        return messages;
    }

    private List<Message> handleUserSelectInvalidOuterCrust(long productId) {
        ArrayList<Message> messages = new ArrayList<>();
        messages.add(Message.fromText("Lựa chọn không có trong danh sách, vui lòng chọn lại"));
        messages.addAll(productOptionService.getProductOptionList(productId, OptionType.EXTRAS));

        return messages;
    }
}
