package com.khoa.bot.connector.facebook.service.order;

import com.khoa.bot.connector.facebook.adapter.model.Message;
import com.khoa.bot.connector.facebook.context.BotMessageContent;
import com.khoa.bot.connector.facebook.context.DialogContext;
import com.khoa.bot.connector.facebook.context.OrderContext;
import com.khoa.bot.connector.facebook.entity.Order;
import com.khoa.bot.connector.facebook.entity.OrderItem;
import com.khoa.bot.connector.facebook.entity.OrderItemOption;
import com.khoa.bot.connector.facebook.entity.enums.ConversationContext;
import com.khoa.bot.connector.facebook.entity.enums.OptionType;
import com.khoa.bot.connector.facebook.entity.enums.UserOrderContextType;
import com.khoa.bot.connector.facebook.repository.mapper.ProductMapper;
import com.khoa.bot.connector.facebook.service.AddressService;
import com.khoa.bot.connector.facebook.service.CongratsOrderService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Component
public class PhoneNumberOrderHandler implements PizzaOrderStepHandler {
    private final AddressService addressService;
    private final ProductMapper productMapper;
    private final CongratsOrderService congratsOrderService;

    public PhoneNumberOrderHandler(AddressService addressService, ProductMapper productMapper, CongratsOrderService congratsOrderService) {
        this.addressService = addressService;
        this.productMapper = productMapper;
        this.congratsOrderService = congratsOrderService;
    }

    @Override
    public List<Message> getMessages(DialogContext dialogContext, BotMessageContent botMessageContent) {
        OrderContext orderContext = dialogContext.getOrderContext();

        String text = botMessageContent.getText();

//        if (!isValidPhoneNumber(text)) {
//            return List.of(addressService.getInvalidPhoneNumberMessage());
//        }

        orderContext.updatePhoneNumber(dialogContext.getUserContext().getContext(), text);

        ArrayList<Message> messages = new ArrayList<>();
        messages.add(addressService.getSuccessPhoneNumberMessage());

        setNextStep(dialogContext, UserOrderContextType.NOT_STARTED);
        dialogContext.getUserContext().updateConversationContext(dialogContext.getUserContext().getContext(), ConversationContext.GENERAL);

        orderContext.completeOrder();
        messages.addAll(getOrderResult(orderContext.initOrder(dialogContext.getUserContext().getContext())));

        return messages;
    }

    boolean isValidPhoneNumber(String phoneNumber) {
        Pattern pattern = Pattern.compile("^\\d{10}$");
        Matcher matcher = pattern.matcher(phoneNumber);

        return matcher.matches();
    }

    private List<Message> getOrderResult(Order order) {
        ArrayList<Message> messages = new ArrayList<>();
        messages.add(congratsOrderService.getMessage());
        messages.add(Message.fromText("B???n ???? ?????t h??ng th??nh c??ng! Sau ????y l?? th??ng tin ????n h??ng:"));
        OrderItem orderItem = order.getOrderItemList().get(0);
        long productId = orderItem.getProductId();
        messages.add(Message.fromText(String.format("S???n ph???m: %s", productMapper.selectProduct(productId).getName())));

        messages.add(Message.fromText("T??y ch???n:"));
        List<Long> orderOptionIds = orderItem.getOrderItemOptionList().stream().map(OrderItemOption::getOptionId).collect(Collectors.toList());
        String crust = productMapper.selectProductOptionList(productId, OptionType.CRUST)
                .stream().filter(productOption -> orderOptionIds.contains(productOption.getOptionId()))
                .findFirst().map(productOption -> String.format("%s %s", productOption.getName(), productOption.getFormattedExtraPrice()))
                .orElse("M???c ?????nh");
        messages.add(Message.fromText(String.format("?????: %s\n", crust)));
        String size = productMapper.selectProductOptionList(productId, OptionType.SIZE)
                .stream().filter(productOption -> orderOptionIds.contains(productOption.getOptionId()))
                .findFirst().map(productOption -> String.format("%s %s", productOption.getName(), productOption.getFormattedExtraPrice()))
                .orElse("M???c ?????nh");
        messages.add(Message.fromText(String.format("Size: %s\n", size)));
        String extras = productMapper.selectProductOptionList(productId, OptionType.EXTRAS)
                .stream().filter(productOption -> orderOptionIds.contains(productOption.getOptionId()))
                .findFirst().map(productOption -> String.format("%s %s", productOption.getName(), productOption.getFormattedExtraPrice()))
                .orElse("M???c ?????nh");
        messages.add(Message.fromText(String.format("L???a ch???n th??m: %s\n", extras)));
        String outerCrust = productMapper.selectProductOptionList(productId, OptionType.OUTER_CRUST)
                .stream().filter(productOption -> orderOptionIds.contains(productOption.getOptionId()))
                .findFirst().map(productOption -> String.format("%s %s", productOption.getName(), productOption.getFormattedExtraPrice()))
                .orElse("M???c ?????nh");
        messages.add(Message.fromText(String.format("Vi???n: %s\n", outerCrust)));

        messages.add(Message.fromText("Th??ng tin giao h??ng:"));
        messages.add(Message.fromText("?????a ch???: " + order.getAddress().getAddressValue()));
        messages.add(Message.fromText("S??? ??i???n tho???i: " + order.getAddress().getPhoneNumber()));

        return messages;
    }
}
