package com.wch.test.aop.aspect;

import com.wch.test.aop.aspectj.CurrentUserHolder;
import com.wch.test.aop.aspectj.service.ProductService;
import com.wch.test.domain.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AopProductTest {

    @Resource(name = "aopProductServiceImpl")
    private ProductService productService;

    @Test(expected = RuntimeException.class)
    public void productTest() {
        CurrentUserHolder.set("wch");
        productService.insertProduct(new Product());
    }

    @Test
    public void productTest2() {
        CurrentUserHolder.set("admin");
        productService.deleteProduct(1L);
    }

    @Test
    public void productTest3() {
        productService.queryProduct(1L);
    }

    @Test
    public void productTest4() {
        productService.updateProduct(1L);
    }
}
