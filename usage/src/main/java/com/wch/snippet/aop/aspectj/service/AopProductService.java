package com.wch.snippet.aop.aspectj.service;

import com.wch.snippet.domain.Product;

public interface AopProductService {

    void insertProduct(Product product);

    void deleteProduct(Long id);

    String queryProduct(Long id);

    boolean updateProduct(Long id);
}
