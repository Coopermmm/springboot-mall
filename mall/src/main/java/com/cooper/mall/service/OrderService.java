package com.cooper.mall.service;

import com.cooper.mall.dto.CreateOrderRequest;
import com.cooper.mall.dto.OrderQueryParams;
import com.cooper.mall.model.Order;

import java.util.List;

public interface OrderService {
    Order getOrderById(Integer orderId);

    List<Order> getOrders(OrderQueryParams queryParams);

    Integer countOrder(OrderQueryParams queryParams);
    Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);
}
