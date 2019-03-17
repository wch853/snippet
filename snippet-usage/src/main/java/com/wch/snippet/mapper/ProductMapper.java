package com.wch.snippet.mapper;


import com.wch.snippet.domain.Product;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductMapper {

    /**
     * 根据产品id查询产品
     */
    Product selectProductById(long id);

    /**
     * 插入产品信息
     */
    int insertProduct(Product product);
}
