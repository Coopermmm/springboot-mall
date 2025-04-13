package com.cooper.mall.dao;

import com.cooper.mall.dto.CreateOrderRequest;
import com.cooper.mall.model.OrderItem;

import java.util.List;

public interface OrderDao {
    Integer createOrder(Integer userId, Integer totalAmount);

    void createOrderItems(Integer orderId, List<OrderItem> orderItemList);
}
