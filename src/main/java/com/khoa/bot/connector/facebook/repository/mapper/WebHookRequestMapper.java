package com.khoa.bot.connector.facebook.repository.mapper;

import com.khoa.bot.connector.facebook.entity.WebHookRequest;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WebHookRequestMapper {
    void insertRequest(WebHookRequest webHookRequest);
}
