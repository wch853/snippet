package com.wch.test.service;


import com.wch.test.domain.Product;
import com.wch.test.exception.BusinessException;

public interface ProductService {

    /**
     * 根据id查询商品
     *
     * @param id id
     * @return Product
     */
    Product getProductById(long id);

    /**
     * 新增产品
     *
     * @param product product
     * @throws BusinessException BusinessException
     */
    void addProduct(Product product) throws BusinessException;
}
