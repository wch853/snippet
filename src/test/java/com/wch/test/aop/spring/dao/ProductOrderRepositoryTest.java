package com.wch.test.aop.spring.dao;

import com.wch.test.aop.spring.domain.ProductOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductOrderRepositoryTest {

    @Resource
    private ProductOrderRepository productOrderRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductOrderRepositoryTest.class);

    @Test
    public void insertTest() {
        ProductOrder productOrder = new ProductOrder();
        productOrder.setProductName("Mac Pro");
        productOrder.setOrderPrice(new BigDecimal(13999));
        Date date = new Date();
        productOrder.setCreateTime(date);
        productOrder.setUpdateTime(date);

        ProductOrder order = productOrderRepository.save(productOrder);
        LOGGER.info("order: {}", order);
    }

    @Test
    public void updateTest() {
        ProductOrder productOrder = productOrderRepository.findOne(2L);
        productOrder.setOrderPrice(new BigDecimal(14999));
        productOrder.setUpdateTime(new Date());

        ProductOrder order = productOrderRepository.save(productOrder);
        LOGGER.info("order: {}", order);
    }

    @Test
    public void deleteTest() {
        productOrderRepository.delete(1L);
    }
}