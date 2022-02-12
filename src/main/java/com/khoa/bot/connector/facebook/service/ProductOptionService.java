package com.khoa.bot.connector.facebook.service;

import com.khoa.bot.connector.facebook.adapter.enums.AttachmentType;
import com.khoa.bot.connector.facebook.adapter.model.*;
import com.khoa.bot.connector.facebook.entity.Option;
import com.khoa.bot.connector.facebook.entity.ProductOption;
import com.khoa.bot.connector.facebook.entity.enums.OptionType;
import com.khoa.bot.connector.facebook.repository.mapper.ProductMapper;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductOptionService {
    private final ProductMapper productMapper;

    public ProductOptionService(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    public List<Message> getProductOptionList(long productId, OptionType optionType) {
        List<ProductOption> productOptions = productMapper.selectProductOptionList(productId, optionType);

        Message message = new Message();
        message.setAttachment(getProductOptionAttachment(productOptions, optionType));

        return List.of(getDescriptionMessage(optionType), message);
    }

    private Attachment getProductOptionAttachment(List<ProductOption> productOptionList, OptionType optionType) {
        List<TemplatePayloadElement> templatePayloadElements = productOptionList.stream()
                .map(productOption -> {
                    TemplatePayloadElement templatePayloadElement = new TemplatePayloadElement();
                    templatePayloadElement.setTitle(productOption.getName());
                    templatePayloadElement.setSubtitle(productOption.getFormattedExtraPrice());
                    templatePayloadElement.setButtons(
                            Arrays.asList(
                                    Button.postbackButtonBuilder()
                                            .payload(String.valueOf(productOption.getOptionId()))
                                            .title("Chọn")
                                            .build())
                    );

                    return templatePayloadElement;
                }).collect(Collectors.toList());

        if (optionType != OptionType.SIZE && optionType != OptionType.CRUST) {
            templatePayloadElements.add(getNoChoiceOption());
        }

        TemplatePayload templatePayload = new TemplatePayload();
        templatePayload.setElements(templatePayloadElements);
        templatePayload.setTemplateType("generic");

        Attachment attachment = new Attachment();
        attachment.setType(AttachmentType.TEMPLATE);
        attachment.setPayload(templatePayload);

        return attachment;
    }

    public List<Message> getSelectOptionSuccess(long optionId) {
        if (optionId <= 0) {
            return List.of(Message.fromText(String.format("Bạn đã chọn để mặc định.")));
        }

        Option option = productMapper.selectOption(optionId);

        return List.of(Message.fromText(String.format("Bạn đã chọn: %s", option.getName())));
    }

    private Message getDescriptionMessage(OptionType optionType) {
        switch (optionType) {
            case SIZE:
                return Message.fromText("Vui lòng chọn size:");
            case CRUST:
                return Message.fromText("Vui lòng chọn đế:");
            case EXTRAS:
                return Message.fromText("Vui lòng chọn:");
            case OUTER_CRUST:
                return Message.fromText("Vui lòng chọn viền:");
        }

        throw new RuntimeException(String.format("Invalid Option Type ==> %s", optionType));
    }

    private TemplatePayloadElement getNoChoiceOption() {
        TemplatePayloadElement templatePayloadElement = new TemplatePayloadElement();
        templatePayloadElement.setTitle("Không chọn");
        templatePayloadElement.setButtons(
                Arrays.asList(
                        Button.postbackButtonBuilder()
                                .payload(String.valueOf(0))
                                .title("Chọn")
                                .build())
        );

        return templatePayloadElement;
    }
}
