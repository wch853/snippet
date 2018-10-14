package com.wch.snippet.aop.aspect;

import com.wch.snippet.SpringTestBase;
import com.wch.snippet.aop.aspectj.CurrentUserHolder;
import com.wch.snippet.aop.aspectj.service.AopProductService;
import com.wch.snippet.domain.Product;
import org.testng.annotations.Test;

import javax.annotation.Resource;

public class AopProductTest extends SpringTestBase {

    @Resource(name = "aopProductServiceImpl")
    private AopProductService aopProductService;

    @Test(expectedExceptions = RuntimeException.class)
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
