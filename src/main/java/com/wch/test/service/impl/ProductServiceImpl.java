package com.wch.test.service.impl;

import com.wch.test.domain.Product;
import com.wch.test.exception.BusinessException;
import com.wch.test.mapper.ProductMapper;
import com.wch.test.service.ProductService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@CacheConfig(cacheNames = "product")
public class ProductServiceImpl implements ProductService {

    @Resource
    private ProductMapper productMapper;

    /**
     * 根据id查询商品
     *
     * @param id id
     * @return Product
     */
    @Override
    @Cacheable(key = "'product:' + #p0") // key会覆盖generated key
    public Product getProductById(long id) {
        return productMapper.selectProductById(id);
    }

    /**
     * 新增产品
     *
     * @param product product
     * @throws BusinessException BusinessException
     */
    @Override
    public void addProduct(Product product) throws BusinessException {
        int count = productMapper.insertProduct(product);
        if (count <= 0) {
            throw new BusinessException("add product failed.");
        }
    }
}
