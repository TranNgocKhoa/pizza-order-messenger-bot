package com.khoa.bot.connector.facebook.service;

import com.khoa.bot.connector.facebook.adapter.enums.AttachmentType;
import com.khoa.bot.connector.facebook.adapter.model.Attachment;
import com.khoa.bot.connector.facebook.adapter.model.Message;
import com.khoa.bot.connector.facebook.adapter.model.TemplatePayload;
import com.khoa.bot.connector.facebook.adapter.model.TemplatePayloadElement;
import com.khoa.bot.connector.facebook.context.BotMessageContent;
import com.khoa.bot.connector.facebook.context.UserContext;
import com.khoa.bot.connector.facebook.entity.Order;
import com.khoa.bot.connector.facebook.entity.Product;
import com.khoa.bot.connector.facebook.entity.User;
import com.khoa.bot.connector.facebook.repository.mapper.OrderMapper;
import com.khoa.bot.connector.facebook.repository.mapper.ProductMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LookupMessageProducer implements ContextHandler {
    private final UserContext userContext;
    private final ProductMapper productMapper;
    private final OrderMapper orderMapper;

    public LookupMessageProducer(UserContext userContext, ProductMapper productMapper, OrderMapper orderMapper) {
        this.userContext = userContext;
        this.productMapper = productMapper;
        this.orderMapper = orderMapper;
    }

    @Override
    public List<Message> getMessages(BotMessageContent messageContent) {
        User user = userContext.getContext();

        List<Order> orders = orderMapper.selectInProgressOrders(user);

        if (CollectionUtils.isEmpty(orders)) {
            return List.of(Message.builder()
                    .text("Hiện tại không có đơn hàng nào!")
                    .build());
        }

        orders = orders.stream()
                .map(order -> {
                    order.setOrderItemList(orderMapper.selectOrderItems(order.getId()));
                    return order;
                })
                .map(order -> {
                    long productId = order.getOrderItemList().get(0).getProductId();
                    Product product = productMapper.selectProduct(productId);

                    order.setFirstProduct(product);
                    return order;
                }).collect(Collectors.toList());

        Message message = new Message();
        message.setAttachment(getOrderAttachment(orders));

        return List.of(message);
    }

    private Attachment getOrderAttachment(List<Order> orderList) {
        List<TemplatePayloadElement> templatePayloadElements = orderList.stream()
                .map(order -> {
                    TemplatePayloadElement templatePayloadElement = new TemplatePayloadElement();
                    templatePayloadElement.setTitle("Đơn hàng: " + order.getOrderTitle());
                    templatePayloadElement.setSubtitle(order.getPlainStatus());
                    templatePayloadElement.setImageUrl(order.getFirstProduct().getImageUrl());

                    return templatePayloadElement;
                }).collect(Collectors.toList());

        TemplatePayload templatePayload = new TemplatePayload();
        templatePayload.setElements(templatePayloadElements);
        templatePayload.setTemplateType("generic");

        Attachment attachment = new Attachment();
        attachment.setType(AttachmentType.TEMPLATE);
        attachment.setPayload(templatePayload);

        return attachment;
    }
}
