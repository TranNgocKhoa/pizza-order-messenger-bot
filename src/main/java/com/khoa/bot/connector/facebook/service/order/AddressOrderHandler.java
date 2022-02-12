package com.khoa.bot.connector.facebook.service.order;

import com.khoa.bot.connector.facebook.adapter.model.Message;
import com.khoa.bot.connector.facebook.context.BotMessageContent;
import com.khoa.bot.connector.facebook.context.DialogContext;
import com.khoa.bot.connector.facebook.context.OrderContext;
import com.khoa.bot.connector.facebook.entity.enums.UserOrderContextType;
import com.khoa.bot.connector.facebook.service.AddressService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AddressOrderHandler implements PizzaOrderStepHandler {
    private final AddressService addressService;

    public AddressOrderHandler(AddressService addressService) {
        this.addressService = addressService;
    }

    @Override
    public List<Message> getMessages(DialogContext dialogContext, BotMessageContent botMessageContent) {
        OrderContext orderContext = dialogContext.getOrderContext();

        orderContext.updateAddress(dialogContext.getUserContext().getContext(), botMessageContent.getText());
        setNextStep(dialogContext, UserOrderContextType.PHONE_NUMBER_DECLARE);
        ArrayList<Message> messages = new ArrayList<>();
        messages.add(addressService.getSuccessAddressMessage());
        messages.addAll(addressService.getInputPhoneNumberMessage());

        return messages;
    }
}
