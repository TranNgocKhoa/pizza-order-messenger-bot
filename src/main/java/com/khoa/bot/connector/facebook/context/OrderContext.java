package com.khoa.bot.connector.facebook.context;

import com.khoa.bot.connector.facebook.entity.*;
import com.khoa.bot.connector.facebook.repository.mapper.AddressMapper;
import com.khoa.bot.connector.facebook.repository.mapper.OrderMapper;
import com.khoa.bot.connector.facebook.repository.mapper.ProductMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class OrderContext implements Context<Order> {
    private static final ThreadLocal<Order> ORDER_THREAD_LOCAL = ThreadLocal.withInitial(Order::new);

    private final OrderMapper orderMapper;
    private final ProductMapper productMapper;
    private final AddressMapper addressMapper;

    public OrderContext(OrderMapper orderMapper, ProductMapper productMapper, AddressMapper addressMapper) {
        this.orderMapper = orderMapper;
        this.productMapper = productMapper;
        this.addressMapper = addressMapper;
    }

    @Override
    public Order getContext() {
        return ORDER_THREAD_LOCAL.get();
    }

    public Order initOrder(User user) {
        Order order = ORDER_THREAD_LOCAL.get();
        if (order.getId() > 0) {
            return order;
        }
        order.setUserId(user.getId());
        if (order.getId() <= 0) {
            Order savedOrder = orderMapper.selectLatestInProgressOrder(user);

            if (Objects.isNull(savedOrder)) {
                orderMapper.insertOrder(order);
                order.setCatalogueData(new CatalogueData());
                orderMapper.updateCatalogueData(user, order.getCatalogueData());
            } else {
                order.setId(savedOrder.getId());
                order.setCatalogueData(orderMapper.selectCatalogueData(user.getId()));
            }

            List<OrderItem> orderItemList = orderMapper.selectOrderItems(order.getId());
            order.setOrderItemList(orderItemList);

            Address address = orderMapper.selectOrderAddressMapping(order.getId());
            order.setAddress(address);
        }
        ORDER_THREAD_LOCAL.set(order);

        return order;
    }

    public void updateProduct(User user, long productId) {
        Product product = productMapper.selectProduct(productId);

        Order order = ORDER_THREAD_LOCAL.get();

        if (order.getId() <= 0) {
            initOrder(user);

            order = ORDER_THREAD_LOCAL.get();
        }

        OrderItem updatedOrderItem = order.getOrderItemList()
                .stream().findFirst()
                .map(orderItem -> {
                    orderItem.setProductId(productId);
                    orderItem.setPrice(product.getPrice());
                    orderItem.setQuantity(1);
                    return orderItem;
                }).orElse(OrderItem.builder().orderId(getContext().getId()).productId(productId).quantity(1).build());

        orderMapper.addOrderItem(updatedOrderItem);
    }

    public void updateOption(User user, long optionId) {
        if (optionId <= 0) {
            return;
        }

        Order order = ORDER_THREAD_LOCAL.get();

        if (order.getId() <= 0) {
            initOrder(user);

            order = ORDER_THREAD_LOCAL.get();
        }

        OrderItemOptionMapping orderItemOptionMapping = order.getOrderItemList()
                .stream().findFirst()
                .map(orderItem -> {
                    ProductOption productOption = productMapper.selectProductOption(orderItem.getProductId(), optionId);
                    return OrderItemOptionMapping.builder()
                            .orderId(ORDER_THREAD_LOCAL.get().getId())
                            .optionId(optionId)
                            .productId(orderItem.getProductId())
                            .extraFee(productOption.getExtraFee())
                            .build();
                }).get();

        orderMapper.addOrderItemOptionMapping(orderItemOptionMapping);
    }

    public void updateCatalogueData(User user, long offset, long limit) {
        CatalogueData catalogueData = new CatalogueData();
        catalogueData.setLimit(limit);
        catalogueData.setOffset(offset);

        orderMapper.updateCatalogueData(user, catalogueData);
    }

    public void updateAddress(User context, String addressString) {
        Order order = ORDER_THREAD_LOCAL.get();

        if (order.getId() <= 0) {
            initOrder(context);

            order = ORDER_THREAD_LOCAL.get();
        }

        Address address = new Address();
        address.setAddressValue(addressString);
        address.setUserId(order.getUserId());

        addressMapper.insertAddress(address);

        order.setAddress(address);

        OrderAddressMapping orderAddressMapping = new OrderAddressMapping();
        orderAddressMapping.setAddressId(address.getId());
        orderAddressMapping.setOrderId(order.getId());

        orderMapper.addOrderAddressMapping(orderAddressMapping);

        ORDER_THREAD_LOCAL.set(order);
    }

    public void updatePhoneNumber(User context, String phoneNumber) {
        Order order = ORDER_THREAD_LOCAL.get();

        if (order.getId() <= 0) {
            initOrder(context);

            order = ORDER_THREAD_LOCAL.get();
        }

        Address address = addressMapper.selectAddressByOrderId(order.getId());
        address.setPhoneNumber(phoneNumber);

        order.setAddress(address);

        addressMapper.updatePhoneNumber(address);

        ORDER_THREAD_LOCAL.set(order);
    }

    public void completeOrder() {
        orderMapper.completeOrder(ORDER_THREAD_LOCAL.get().getId());
    }
}
