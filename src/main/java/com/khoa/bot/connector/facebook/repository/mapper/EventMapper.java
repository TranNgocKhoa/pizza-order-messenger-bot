package com.khoa.bot.connector.facebook.repository.mapper;

import com.khoa.bot.connector.facebook.entity.Event;
import com.khoa.bot.connector.facebook.entity.Message;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EventMapper {
    void insertEvent(Event event);

    void insertMessage(Message message);
}
