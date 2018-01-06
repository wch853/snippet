package com.wch.test.aop;

import com.wch.test.aop.aspect.CurrentUserHolder;
import com.wch.test.aop.aspect.ProductService;
import com.wch.test.domain.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AopTest {

    @Resource
    private ProductService productService;

    @Test(expected = RuntimeException.class)
    public void productTest() {
        CurrentUserHolder.set("wch");
        productService.insertProduct(new Product());
    }

    public void productTest2() {
        CurrentUserHolder.set("admin");
        productService.deleteProduct(1L);
    }
}
