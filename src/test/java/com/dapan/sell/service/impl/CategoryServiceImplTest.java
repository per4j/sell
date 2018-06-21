package com.dapan.sell.service.impl;

import com.dapan.sell.dataobject.ProductCategory;
import com.dapan.sell.service.CategoryService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceImplTest {

    @Autowired
    private CategoryServiceImpl service;

    @Test
    public void findOne() {
        ProductCategory one = service.findOne(1);
        Assert.assertNotNull(one);
    }

    @Test
    public void findAll() {
        List<ProductCategory> all = service.findAll();
        Assert.assertNotEquals(0, all.size());
    }

    @Test
    public void findByCategoryTypeIn() {
        List<ProductCategory> categories = service.findByCategoryTypeIn(Arrays.asList(1, 3));
        Assert.assertNotEquals(0, categories);
    }

    @Test
    public void save() {

        ProductCategory category = new ProductCategory();
        category.setCategoryName("武术");
        category.setCategoryType(11);
        ProductCategory save = service.save(category);
        Assert.assertNotNull(save);
    }
}