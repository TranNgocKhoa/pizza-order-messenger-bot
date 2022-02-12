package com.khoa.bot.connector.facebook.service;

import com.khoa.bot.connector.facebook.adapter.enums.AttachmentType;
import com.khoa.bot.connector.facebook.adapter.model.*;
import com.khoa.bot.connector.facebook.context.BotMessageContent;
import com.khoa.bot.connector.facebook.context.DialogContext;
import com.khoa.bot.connector.facebook.entity.CatalogueData;
import com.khoa.bot.connector.facebook.entity.Order;
import com.khoa.bot.connector.facebook.entity.Product;
import com.khoa.bot.connector.facebook.repository.mapper.ProductMapper;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductListService extends AbstractUserContextHandler {
    private final ProductMapper productMapper;

    protected ProductListService(DialogContext dialogContext, ProductMapper productMapper) {
        super(dialogContext);
        this.productMapper = productMapper;
    }

    @Override
    public List<Message> getMessages(BotMessageContent messageContent) {
        Order order = dialogContext.getOrderContext().getContext();

        CatalogueData catalogueData = order.getCatalogueData();

        List<Product> products = productMapper.selectProductList(catalogueData.getOffset(), catalogueData.getLimit());

        Message message = new Message();
        message.setAttachment(getProductAttachment(products));

        return List.of(message);
    }

    public List<Message> getProductListMessage(long offset, long limit) {
        List<Product> products = productMapper.selectProductList(offset, limit);

        Message message = new Message();
        message.setAttachment(getProductAttachment(products));

        return List.of(message);
    }

    public List<Message> getSelectProductSuccess(long productId) {
        Product product = productMapper.selectProduct(productId);

        return List.of(Message.fromText(String.format("Bạn đã chọn: %s", product.getName())));
    }

    private Attachment getProductAttachment(List<Product> productList) {
        List<TemplatePayloadElement> templatePayloadElements = productList.stream()
                .map(product -> {
                    TemplatePayloadElement templatePayloadElement = new TemplatePayloadElement();
                    templatePayloadElement.setTitle(product.getName());
                    templatePayloadElement.setImageUrl(product.getImageUrl());
                    templatePayloadElement.setSubtitle(product.getFormattedPrice());
                    templatePayloadElement.setButtons(
                            Arrays.asList(
                                    Button.webUrlButtonBuilder()
                                            .url("https://www.facebook.com/tranngoc.khoa.1/")
                                            .title("Chi tiết")
                                            .build(),
                                    Button.postbackButtonBuilder()
                                            .payload(String.valueOf(product.getId()))
                                            .title("Chọn")
                                            .build())
                    );

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
