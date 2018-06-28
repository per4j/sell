package com.dapan.sell.service.impl;

import com.dapan.sell.dataobject.ProductInfo;
import com.dapan.sell.enums.ProductStatusEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceImplTest {

    @Autowired
    private ProductServiceImpl service;

    @Test
    public void findOne() {
        ProductInfo one = service.findOne("123456");
        Assert.assertEquals("123456", one.getProductId());
    }

    @Test
    public void findUpAll() {
        List<ProductInfo> upAll = service.findUpAll();
        Assert.assertNotEquals(0, upAll.size());
    }

    @Test
    public void findAll() {
        PageRequest pageRequest = PageRequest.of(0, 2);
        Page<ProductInfo> all = service.findAll(pageRequest);
        System.out.println(all.getTotalElements());
    }

    @Test
    public void save() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("123456");
        productInfo.setProductName("芒果");
        productInfo.setProductPrice(new BigDecimal(3.4));
        productInfo.setProductStock(10);
        productInfo.setProductDescription("小芒果");
        productInfo.setProductIcon("http://www.test.com");
        productInfo.setProductStatus(ProductStatusEnum.UP.getCode());
        productInfo.setCategoryType(2);
        ProductInfo save = service.save(productInfo);
        Assert.assertNotNull(save);
    }

    @Test
    public void onSale() {
        ProductInfo productInfo = service.onSale("123");
        Assert.assertEquals(ProductStatusEnum.UP, productInfo.getProductStatusEnum());
    }

    @Test
    public void offSale() {
        ProductInfo productInfo = service.offSale("123");
        Assert.assertEquals(ProductStatusEnum.DOWN, productInfo.getProductStatusEnum());
    }
}