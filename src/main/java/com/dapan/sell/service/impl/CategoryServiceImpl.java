package com.dapan.sell.service.impl;

import com.dapan.sell.dataobject.ProductCategory;
import com.dapan.sell.dataobject.dao.ProductCategoryDao;
import com.dapan.sell.repository.ProductCategoryRepository;
import com.dapan.sell.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

//    @Autowired // 不推荐对成员变量添加@Autowired注解
    private ProductCategoryRepository repository;

//    @Autowired
//    private ProductCategoryDao dao; // No qualifying bean of type 'com.dapan.sell.dataobject.dao.ProductCategoryDao' available: expected at least 1 bean which qualifies as autowire candidate. Dependency annotations: {}

    @Autowired
    public CategoryServiceImpl(ProductCategoryRepository repository) {
        this.repository = repository;
//        this.dao = dao;
    }

    @Override
    public ProductCategory findOne(Integer categoryId) {
        return repository.findById(categoryId).get();
    }

    @Override
    public List<ProductCategory> findAll() {
        return repository.findAll();
    }

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {
        return repository.findAllByCategoryTypeIn(categoryTypeList);
    }

    @Override
    public ProductCategory save(ProductCategory productCategory) {
        return repository.save(productCategory);
    }
}
