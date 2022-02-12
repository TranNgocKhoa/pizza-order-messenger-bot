package com.khoa.bot.connector.facebook.repository.mapper;

import com.khoa.bot.connector.facebook.entity.Address;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AddressMapper {
    void insertAddress(Address address);

    Address selectAddressByOrderId(@Param("orderId") long orderId);

    void updatePhoneNumber(Address address);
}
