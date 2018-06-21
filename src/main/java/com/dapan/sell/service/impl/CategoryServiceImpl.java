package com.dapan.sell.service.impl;

import com.dapan.sell.dataobject.ProductCategory;
import com.dapan.sell.repository.ProductCategoryRepository;
import com.dapan.sell.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

//    @Autowired // 不推荐对成员变量添加@Autowired注解
    private ProductCategoryRepository repository;

    @Autowired
    public CategoryServiceImpl(ProductCategoryRepository repository) {
        this.repository = repository;
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
