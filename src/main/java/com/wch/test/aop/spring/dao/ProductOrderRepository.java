package com.wch.test.aop.spring.dao;

import com.wch.test.aop.spring.domain.ProductOrder;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 订单操作dao
 */
public interface ProductOrderRepository extends JpaRepository<ProductOrder, Long> {

    @Override
    ProductOrder findOne(Long id);
}
