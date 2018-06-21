package com.dapan.sell.repository;

import com.dapan.sell.dataobject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {

    @Autowired
    private ProductCategoryRepository repository;

    @Test
    public void findOne() {
        ProductCategory category = repository.findById(1).orElse(new ProductCategory());
        System.out.println(category.toString());
    }

    @Test
    public void saveTest() {
        ProductCategory category = new ProductCategory();
        category.setCategoryId(1);
        category.setCategoryName("热榜");
        category.setCategoryType(2);
        repository.save(category);
    }

    @Test
    @Transactional // 测试完，回滚测试数据
    public void updateTest() { // 更新操作时，如果不想设置自增主键，需要添加strategy = GenerationType.IDENTITY
        ProductCategory category = new ProductCategory();
        category.setCategoryId(6);
        category.setCategoryName("都市");
        category.setCategoryType(8);
        repository.save(category);
    }

    @Test
    public void findAllByCategoryTypeIn() {
        List<ProductCategory> categories = repository.findAllByCategoryTypeIn(Arrays.asList(2, 3, 4));
        Assert.assertNotEquals(0, categories.size());
    }
}