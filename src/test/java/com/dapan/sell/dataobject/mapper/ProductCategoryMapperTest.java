package com.dapan.sell.dataobject.mapper;

import com.dapan.sell.dataobject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryMapperTest {

    @Autowired
    private ProductCategoryMapper mapper;

    @Test
    public void insertByMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("categoryName", "欢乐");
        map.put("category_type", 111);
        int result = mapper.insertByMap(map);
        Assert.assertEquals(1, result);
    }

    @Test
    public void insertByObject() {
        ProductCategory category = new ProductCategory();
        category.setCategoryName("努力");
        category.setCategoryType(100);
        int result = mapper.insertByObject(category);
        Assert.assertEquals(1, result);
    }

    @Test
    public void findByCategoryType() {
        ProductCategory category = mapper.findByCategoryType(100);
        Assert.assertEquals("一定要努力", category.getCategoryName());
    }

    @Test
    public void findByCategoryName() {
        List<ProductCategory> category = mapper.findByCategoryName("欢乐");
        Assert.assertEquals(2, category.size());
    }

    @Test
    public void updateByCategoryType() {
        int update = mapper.updateByCategoryType("快乐", 101);
        Assert.assertEquals(1L, update);
    }

    @Test
    public void updateByObject() {
        ProductCategory category = new ProductCategory();
        category.setCategoryName("一定要努力");
        category.setCategoryType(100);
        int update = mapper.updateByObject(category);
        Assert.assertEquals(1, update);
    }

    @Test
    public void deleteByCategoryType() {
        int delete = mapper.deleteByCategoryType(101);
        Assert.assertEquals(1, delete);
    }

    @Test
    public void selectByCategoryType() {
        ProductCategory category = mapper.selectByCategoryType(100);
        Assert.assertNotNull(category);
    }
}