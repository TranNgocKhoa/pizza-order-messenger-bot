package com.khoa.bot.connector.facebook.adapter.model;

import com.khoa.bot.connector.facebook.adapter.enums.QuickReplyContentType;

public class TextQuickReply extends QuickReply {

    public static TextQuickReplyBuilder builder() {
        return new TextQuickReplyBuilder();
    }

    public static final class TextQuickReplyBuilder {
        protected QuickReplyContentType contentType;
        protected String title;
        protected Object payload;
        protected String imageUrl;

        private TextQuickReplyBuilder() {
        }



        public TextQuickReplyBuilder contentType(QuickReplyContentType contentType) {
            this.contentType = contentType;
            return this;
        }

        public TextQuickReplyBuilder title(String title) {
            this.title = title;
            return this;
        }

        public TextQuickReplyBuilder payload(Object payload) {
            this.payload = payload;
            return this;
        }

        public TextQuickReplyBuilder imageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }

        public TextQuickReply build() {
            TextQuickReply textQuickReply = new TextQuickReply();
            textQuickReply.setContentType(contentType);
            textQuickReply.setTitle(title);
            textQuickReply.setPayload(payload);
            textQuickReply.setImageUrl(imageUrl);
            return textQuickReply;
        }
    }
}
