package com.cooper.mall.dao;

import com.cooper.mall.model.Product;

public interface ProductDao {
    Product getProductById(Integer productId);
}
