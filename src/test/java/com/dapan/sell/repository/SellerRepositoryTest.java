package com.dapan.sell.repository;

import com.dapan.sell.dataobject.SellerInfo;
import com.dapan.sell.utils.KeyUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SellerRepositoryTest {

    @Autowired
    private SellerRepository repository;

    @Test
    public void addTest() {
        SellerInfo sellerInfo = new SellerInfo();
        sellerInfo.setSellerId(KeyUtil.genUniqueKey());
        sellerInfo.setSellerName("admin");
        sellerInfo.setPassword("admin");
        sellerInfo.setOpenid("abc");

        SellerInfo info = repository.save(sellerInfo);
        Assert.assertNotNull(info);

    }

    @Test
    public void findByOpenid() {
        SellerInfo sellerInfo = repository.findByOpenid("abc");
        Assert.assertNotNull(sellerInfo);
    }
}