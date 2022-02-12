package com.khoa.bot.connector.facebook.service;

import com.khoa.bot.connector.facebook.adapter.model.Message;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {
    public List<Message> getInputAddressMessage() {
        return List.of(Message.fromText("Vui lòng nhập địa chỉ giao hàng bên dưới:"));
    }

    public List<Message> getInputPhoneNumberMessage() {
        return List.of(Message.fromText("Vui lòng nhập số điện thoại bên dưới:"));
    }

    public Message getSuccessAddressMessage() {
        return Message.fromText("Bạn đã nhập địa chỉ thành công!");
    }

    public Message getSuccessPhoneNumberMessage() {
        return Message.fromText("Bạn đã nhập số điện thoại thành công!");
    }

    public Message getInvalidPhoneNumberMessage() {
        return Message.fromText("Số điện thoại không hợp lệ, vui lòng nhập lại!");
    }
}
