package com.dapan.sell.dataobject.dao;

import com.dapan.sell.dataobject.mapper.ProductCategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

public class ProductCategoryDao {


    private ProductCategoryMapper mapper;

    @Autowired
    public ProductCategoryDao(ProductCategoryMapper mapper) {
        this.mapper = mapper;
    }

    public int addByMap(Map<String, Object> map) {
        return mapper.insertByMap(map);
    }
}
