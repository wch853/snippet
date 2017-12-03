package com.wch.test.mapper;


import com.wch.test.domain.Product;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductMapper {

    /**
     * 根据产品id查询产品
     *
     * @param id
     * @return
     */
    Product selectProductById(long id);

    /**
     * 插入产品信息
     *
     * @param product product
     * @return row count
     */
    int insertProduct(Product product);
}
