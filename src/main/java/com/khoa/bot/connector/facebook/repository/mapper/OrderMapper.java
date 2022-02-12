package com.khoa.bot.connector.facebook.repository.mapper;

import com.khoa.bot.connector.facebook.entity.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderMapper {
    void insertOrder(Order order);

    void addOrderItem(OrderItem orderItem);

    Order selectLatestInProgressOrder(@Param("user") User user);

    List<Order> selectInProgressOrders(@Param("user") User user);

    List<OrderItem> selectOrderItems(@Param("orderId") long orderId);

    void updateCatalogueData(@Param("user") User user, @Param("catalogueData") CatalogueData catalogueData);

    CatalogueData selectCatalogueData(@Param("userId") long userId);

    void addOrderItemOptionMapping(OrderItemOptionMapping orderItemOptionMapping);

    void addOrderAddressMapping(OrderAddressMapping orderAddressMapping);

    Address selectOrderAddressMapping(@Param("orderId") long orderId);

    void completeOrder(@Param("orderId")long orderId);
}
