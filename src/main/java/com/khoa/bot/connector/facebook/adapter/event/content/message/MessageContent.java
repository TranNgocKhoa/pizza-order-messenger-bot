package com.khoa.bot.connector.facebook.adapter.event.content.message;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.khoa.bot.connector.facebook.adapter.event.nlp.NlpData;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

public class MessageContent extends AbstractContent {
    private String text;
    @JsonProperty("quick_reply")
    private QuickReplyContent quickReply;
    @JsonProperty("reply_to")
    private ReplyToContent replyTo;
    private List<AttachmentContent> attachments;
    private RefererContent referer;
    private NlpData nlp;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public QuickReplyContent getQuickReply() {
        return quickReply;
    }

    public void setQuickReply(QuickReplyContent quickReply) {
        this.quickReply = quickReply;
    }

    public ReplyToContent getReplyTo() {
        return replyTo;
    }

    public void setReplyTo(ReplyToContent replyTo) {
        this.replyTo = replyTo;
    }

    public List<AttachmentContent> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<AttachmentContent> attachments) {
        this.attachments = attachments;
    }

    public RefererContent getReferer() {
        return referer;
    }

    public void setReferer(RefererContent referer) {
        this.referer = referer;
    }

    public NlpData getNlp() {
        return nlp;
    }

    public void setNlp(NlpData nlp) {
        this.nlp = nlp;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
