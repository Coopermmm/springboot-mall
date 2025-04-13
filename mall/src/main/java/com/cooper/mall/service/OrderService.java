package com.cooper.mall.service;

import com.cooper.mall.dto.CreateOrderRequest;
import com.cooper.mall.model.Order;

public interface OrderService {
    Order getOrderById(Integer orderId);
    Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);
}
