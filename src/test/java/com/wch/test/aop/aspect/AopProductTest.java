package com.wch.test.aop.aspect;

import com.wch.test.aop.aspectj.CurrentUserHolder;
import com.wch.test.aop.aspectj.service.AopProductService;
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
    private AopProductService aopProductService;

    @Test(expected = RuntimeException.class)
    public void productTest() {
        CurrentUserHolder.set("wch");
        aopProductService.insertProduct(new Product());
    }

    @Test
    public void productTest2() {
        CurrentUserHolder.set("admin");
        aopProductService.deleteProduct(1L);
    }

    @Test
    public void productTest3() {
        aopProductService.queryProduct(1L);
    }

    @Test
    public void productTest4() {
        aopProductService.updateProduct(1L);
    }
}
