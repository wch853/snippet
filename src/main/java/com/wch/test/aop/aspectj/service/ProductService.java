package com.wch.test.aop.aspectj.service;

import com.wch.test.domain.Product;

public interface ProductService {

    void insertProduct(Product product);

    void deleteProduct(Long id);

    String queryProduct(Long id);

    boolean updateProduct(Long id);
}
