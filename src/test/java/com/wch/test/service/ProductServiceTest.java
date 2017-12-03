package com.wch.test.service;

import com.wch.test.domain.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceTest {

    @Resource
    private ProductService productService;

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceTest.class);

    @Test
    public void getProductById() throws Exception {
        // TODO 缓存无法命中的情况
        Product product = productService.getProductById(1);
        LOGGER.info("product: {}", product);
    }

    @Test
    public void addProduct() throws Exception {
        Product product = new Product();
        product.setName("Mac air");
        product.setPrice(new BigDecimal(9899));
        product.setStock(100);
        productService.addProduct(product);
    }

}