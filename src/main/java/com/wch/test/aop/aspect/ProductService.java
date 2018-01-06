package com.wch.test.aop.aspect;

import com.wch.test.domain.Product;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    // @Resource
    // private AuthService authService;

    @AdminOnly
    public void insertProduct(Product product) {
        // 不使用aop：在每次需要验证时都手动调用checkAccess()方法
        // authService.checkAccess();
        System.out.println("insert product.");
    }

    @AdminOnly
    public void deleteProduct(Long id) {
        // authService.checkAccess();
        System.out.println("delete product.");
    }
}
