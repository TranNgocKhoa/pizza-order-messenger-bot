package com.khoa.bot.connector.facebook.repository.mapper;

import com.khoa.bot.connector.facebook.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    User selectUser(@Param("facebookId") String facebookId);

    void insertUser(User user);

    void insertUserConversationContext(User user);

    void insertOrderContext(User user);
}
