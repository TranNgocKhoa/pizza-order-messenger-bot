package com.khoa.bot.connector.facebook.context;

import com.khoa.bot.connector.facebook.entity.User;
import com.khoa.bot.connector.facebook.entity.enums.ConversationContext;
import com.khoa.bot.connector.facebook.entity.enums.UserOrderContextType;
import com.khoa.bot.connector.facebook.repository.mapper.UserMapper;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class UserContext implements Context<User> {
    private static final ThreadLocal<User> USER_THREAD_LOCAL = ThreadLocal.withInitial(User::new);

    private final UserMapper userMapper;

    public UserContext(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @NonNull
    @Override
    public User getContext() {
        return USER_THREAD_LOCAL.get();
    }

    public User initUser(String facebookId) {
        User user = userMapper.selectUser(facebookId);
        if (Objects.isNull(user)) {
            User newUser = this.insertNewUser(facebookId);
            this.updateConversationContext(newUser, ConversationContext.GENERAL);
            this.updateOrderContext(newUser, UserOrderContextType.NOT_STARTED);
        } else {
            USER_THREAD_LOCAL.set(user);

        }
        return USER_THREAD_LOCAL.get();
    }

    public void updateConversationContext(User user, ConversationContext conversationContext) {
        user.setConversationContext(conversationContext);
        userMapper.insertUserConversationContext(user);
        USER_THREAD_LOCAL.set(user);
    }

    public void updateOrderContext(User user, UserOrderContextType userOrderContextType) {
        user.setOrderContext(userOrderContextType);
        userMapper.insertOrderContext(user);
        USER_THREAD_LOCAL.set(user);
    }

    private User insertNewUser(String facebookId) {
        User newUser = new User();
        newUser.setFacebookId(facebookId);
        userMapper.insertUser(newUser);

        return newUser;
    }
}
