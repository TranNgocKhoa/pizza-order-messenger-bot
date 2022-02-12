package com.khoa.bot.connector.facebook.service;

import com.khoa.bot.connector.facebook.entity.enums.FunctionType;
import com.khoa.bot.connector.facebook.adapter.enums.QuickReplyContentType;
import com.khoa.bot.connector.facebook.adapter.model.Message;
import com.khoa.bot.connector.facebook.adapter.model.QuickReply;
import com.khoa.bot.connector.facebook.adapter.model.TextQuickReply;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FunctionMessageProducer implements ContextLessMessageProducer {
    private static final String FUNCTION_CHOSEN_GUIDE_TEXT = "Vui lòng chọn chức năng:";

    @Override
    public Message getMessage() {
        return askChooseFunction();
    }

    private Message askChooseFunction() {
        return Message.builder()
                .text(FUNCTION_CHOSEN_GUIDE_TEXT)
                .quickReplies(functionList())
                .build();
    }

    private List<QuickReply> functionList() {
        return List.of(
                TextQuickReply.builder()
                        .contentType(QuickReplyContentType.TEXT)
                        .title(FunctionType.ORDER.getUiValue())
                        .payload(FunctionType.ORDER.getPayload())
                        .build(),
//                TextQuickReply.builder()
//                        .contentType(QuickReplyContentType.TEXT)
//                        .title(FunctionType.HELP.getUiValue())
//                        .payload(FunctionType.HELP.getPayload())
//                        .build(),
                TextQuickReply.builder()
                        .contentType(QuickReplyContentType.TEXT)
                        .title(FunctionType.LOOKUP.getUiValue())
                        .payload(FunctionType.LOOKUP.getPayload())
                        .build()
        );
    }
}
