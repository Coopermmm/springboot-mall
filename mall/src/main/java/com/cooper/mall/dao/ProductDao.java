package com.cooper.mall.dao;

import com.cooper.mall.dto.ProductRequest;
import com.cooper.mall.model.Product;

public interface ProductDao {
    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId, ProductRequest productRequest);

    void deleteProduct(Integer productId);
}
