package com.cooper.mall.service.ProductServiceImpl;

import com.cooper.mall.dao.OrderDao;
import com.cooper.mall.dao.ProductDao;
import com.cooper.mall.dto.BuyItem;
import com.cooper.mall.dto.CreateOrderRequest;
import com.cooper.mall.model.Order;
import com.cooper.mall.model.OrderItem;
import com.cooper.mall.model.Product;
import com.cooper.mall.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private ProductDao productDao;

    @Override
    public Order getOrderById(Integer orderId) {
        Order order = orderDao.getOrderById(orderId);
        List<OrderItem> orderItemList = orderDao.getOrderItemById(orderId);
        order.setOrderItemList(orderItemList);
        return order;
    }

    @Transactional
    @Override
    public Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest) {
        int totalAmount = 0;
        List<OrderItem> orderItemList = new ArrayList<OrderItem>();
        for (BuyItem buyItem : createOrderRequest.getBuyItemList()) {
            Product product = productDao.getProductById(buyItem.getProductId());
            // 計算每個品項的總金額
            Integer amount = product.getPrice() * buyItem.getQuantity();
            totalAmount = totalAmount + amount;
            // 將BuyItem轉換成OrderOtem
            // 方便後續當作參數傳遞，主要是需要上一段的amount
            OrderItem orderItem = new OrderItem();
            orderItem.setProductId(buyItem.getProductId());
            orderItem.setQuantity(buyItem.getQuantity());
            orderItem.setAmount(amount);
            orderItemList.add(orderItem);
        }
        Integer orderId = orderDao.createOrder(userId, totalAmount);

        orderDao.createOrderItems(orderId, orderItemList);
        return orderId;
    }
}
