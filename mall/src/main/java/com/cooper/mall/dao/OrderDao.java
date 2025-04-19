package com.cooper.mall.dao;

import com.cooper.mall.dto.CreateOrderRequest;
import com.cooper.mall.dto.OrderQueryParams;
import com.cooper.mall.model.Order;
import com.cooper.mall.model.OrderItem;

import java.util.List;

public interface OrderDao {
    Order getOrderById(Integer orderId);
    List<Order> getOrders(OrderQueryParams orderQueryParams);
    Integer countOrder(OrderQueryParams orderQueryParams);
    List<OrderItem> getOrderItemById(Integer orderId);
    Integer createOrder(Integer userId, Integer totalAmount);
    void createOrderItems(Integer orderId, List<OrderItem> orderItemList);
}
