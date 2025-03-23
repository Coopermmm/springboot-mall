package com.cooper.mall.service;

import com.cooper.mall.constants.ProductCategory;
import com.cooper.mall.dto.ProductRequest;
import com.cooper.mall.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> getProducts(ProductCategory category, String search);
    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId, ProductRequest productRequest);

    void deleteProduct(Integer productId);
}
