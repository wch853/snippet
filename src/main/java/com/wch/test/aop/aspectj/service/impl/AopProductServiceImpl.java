package com.wch.test.aop.aspectj.service.impl;

import com.wch.test.aop.aspectj.AdminOnly;
import com.wch.test.aop.aspectj.service.ProductService;
import com.wch.test.domain.Product;
import org.springframework.stereotype.Service;

@Service
public class AopProductServiceImpl implements ProductService {

    // @Resource
    // private AuthUtil authUtil;

    @AdminOnly
    public void insertProduct(Product product) {
        // 不使用aop：在每次需要验证时都手动调用checkAccess()方法
        // authUtil.checkAccess();
        System.out.println("insert product.");
    }

    @AdminOnly
    public void deleteProduct(Long id) throws RuntimeException {
        // authUtil.checkAccess();
        System.out.println("delete product.");
    }

    public String queryProduct(Long id) {
        System.out.println("query product.");
        return "product";
    }

    public boolean updateProduct(Long id) {
        System.out.println("edit product.");
        return true;
    }
}
