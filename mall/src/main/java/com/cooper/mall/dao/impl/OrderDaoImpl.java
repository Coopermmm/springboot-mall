package com.cooper.mall.dao.impl;

import com.cooper.mall.dao.OrderDao;
import com.cooper.mall.dto.CreateOrderRequest;
import com.cooper.mall.dto.OrderQueryParams;
import com.cooper.mall.model.Order;
import com.cooper.mall.model.OrderItem;
import com.cooper.mall.rowmapper.OrderItemRowMapper;
import com.cooper.mall.rowmapper.OrderRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class OrderDaoImpl implements OrderDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Order getOrderById(Integer orderId) {
        String sql = " SELECT order_id, user_id, total_amount, created_date, last_modified_date " +
                " FROM orders WHERE order_id = :orderId ";
        Map<String, Object> map = new HashMap<>();
        map.put("orderId", orderId);
        List<Order> orderList = namedParameterJdbcTemplate.query(sql, map, new OrderRowMapper());
        if (orderList.size()>0){
            return orderList.get(0);
        }else{
            return null;
        }
    }

    @Override
    public List<Order> getOrders(OrderQueryParams orderQueryParams) {
        String sql = " SELECT order_id, user_id, total_amount, created_date, last_modified_date FROM orders WHERE 1=1 ";
        Map<String, Object> map = new HashMap<>();
        // 查詢條件
        sql = addFilteringSql(sql, map, orderQueryParams);
        // 排序
        sql = sql + " ORDER BY created_date DESC ";
        // 分頁
        sql = sql + " LIMIT :limit OFFSET :offset ";
        map.put("limit", orderQueryParams.getLimit());
        map.put("offset", orderQueryParams.getOffset());

        List<Order> orderList = namedParameterJdbcTemplate.query(sql, map, new OrderRowMapper());
        return orderList;
    }

    @Override
    public Integer countOrder(OrderQueryParams orderQueryParams) {
        String sql = " SELECT count(*) FROM orders WHERE 1=1 ";
        Map<String, Object> map = new HashMap<>();
        addFilteringSql(sql, map, orderQueryParams);
        Integer total = namedParameterJdbcTemplate.queryForObject(sql, map, Integer.class);
        return total;
    }

    @Override
    public List<OrderItem> getOrderItemById(Integer orderId) {
        String sql = " SELECT oi.order_item_id, oi.order_id, oi.product_id, oi.quantity, oi.amount, p.product_name, p.image_url " +
                " FROM order_item oi " +
                " LEFT JOIN product p ON oi.product_id = p.product_id " +
                " WHERE oi.order_id = :orderId ";
        Map<String, Object> map = new HashMap<>();
        map.put("orderId", orderId);
        List<OrderItem> orderItemList = namedParameterJdbcTemplate.query(sql, map, new OrderItemRowMapper());
        return orderItemList;
    }

    @Override
    public Integer createOrder(Integer userId, Integer totalAmount) {
        String sql = " INSERT INTO orders(user_id, total_amount, created_date, last_modified_date) " +
                " VALUES (:userId, :totalAmount, :createdDate, :lastModifiedDate) ";
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("totalAmount", totalAmount);

        Date now = new Date();
        map.put("createdDate", now);
        map.put("lastModifiedDate", now);

        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);
        int orderId = keyHolder.getKey().intValue();

        return orderId;
    }

    @Override
    public void createOrderItems(Integer orderId, List<OrderItem> orderItemList) {
        // 使用batchUpdate一次性加入數據，效率更高
        String sql = " INSERT INTO order_item(order_id, product_id, quantity, amount) " +
                " VALUES(:orderId, :productId, :quantity, :amount) ";
        MapSqlParameterSource[] mapSqlParameterSources = new MapSqlParameterSource[orderItemList.size()];
        for (int i = 0; i < orderItemList.size(); i++){
            OrderItem orderItem = orderItemList.get(i);
            mapSqlParameterSources[i] = new MapSqlParameterSource();
            mapSqlParameterSources[i].addValue("orderId", orderId);
            mapSqlParameterSources[i].addValue("productId", orderItem.getProductId());
            mapSqlParameterSources[i].addValue("quantity", orderItem.getQuantity());
            mapSqlParameterSources[i].addValue("amount", orderItem.getAmount());
        }

        namedParameterJdbcTemplate.batchUpdate(sql, mapSqlParameterSources);

    }

    public String addFilteringSql(String sql, Map<String, Object> map, OrderQueryParams orderQueryParams){
        if (orderQueryParams != null){
            sql = sql + " AND user_id = :userId ";
            map.put("userId", orderQueryParams.getUserId());
        }
        return sql;
    }
}
