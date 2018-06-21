package com.dapan.sell.repository;

import com.dapan.sell.dataobject.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoRepositoryTest {

    @Autowired
    private ProductInfoRepository repository;

    @Test
    public void saveTest() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("123456");
        productInfo.setProductName("西瓜");
        productInfo.setProductPrice(new BigDecimal(1.4));
        productInfo.setProductStock(100);
        productInfo.setProductDescription("大西瓜");
        productInfo.setProductIcon("http://www.test.com");
        productInfo.setProductStatus(1);
        productInfo.setCategoryType(1);

        ProductInfo save = repository.save(productInfo);
        Assert.assertNotNull(save);
    }

    @Test
    public void findByProductStatus() {

        List<ProductInfo> productInfos = repository.findByProductStatus(1);
        Assert.assertNotEquals(0, productInfos.size());
    }
}