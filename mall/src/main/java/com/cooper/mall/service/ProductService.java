package com.cooper.mall.service;

import com.cooper.mall.dto.ProductRequest;
import com.cooper.mall.model.Product;

public interface ProductService {
    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId, ProductRequest productRequest);

    void deleteProduct(Integer productId);
}
