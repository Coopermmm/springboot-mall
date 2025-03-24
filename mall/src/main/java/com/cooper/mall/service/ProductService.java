package com.cooper.mall.service;

import com.cooper.mall.constants.ProductCategory;
import com.cooper.mall.dto.ProductQueryParams;
import com.cooper.mall.dto.ProductRequest;
import com.cooper.mall.model.Product;

import java.util.List;

public interface ProductService {
    Integer countProduct(ProductQueryParams productQueryParams);
    List<Product> getProducts(ProductQueryParams productQueryParams);
    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId, ProductRequest productRequest);

    void deleteProduct(Integer productId);
}
