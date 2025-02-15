package com.cooper.mall.service.ProductServiceImpl;

import com.cooper.mall.dao.ProductDao;
import com.cooper.mall.model.Product;
import com.cooper.mall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao productDao;
    @Override
    public Product getProductById(Integer productId) {
        return productDao.getProductById(productId);
    }
}
