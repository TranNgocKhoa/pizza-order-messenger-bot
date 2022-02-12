package com.khoa.bot.connector.facebook.context;

import com.khoa.bot.connector.facebook.adapter.model.Message;

import java.util.ArrayList;
import java.util.List;

public class MessageContext {
    private static final ThreadLocal<List<Message>> MESSAGE_THREAD_LOCAL = ThreadLocal.withInitial(ArrayList::new);

    public static void addMessage(Message message) {
        MESSAGE_THREAD_LOCAL.get().add(message);
    }

    public static List<Message> getMessages() {
        return MESSAGE_THREAD_LOCAL.get();
    }

    public static void clear() {
        MESSAGE_THREAD_LOCAL.remove();
    }
}
