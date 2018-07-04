package com.dapan.sell.service.impl;

import com.dapan.sell.dataobject.SellerInfo;
import com.dapan.sell.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SellerServiceImplTest {

    private static final String openid="abc";

    @Autowired
    private SellerServiceImpl sellerService;

    @Test
    public void findSellerInfoByOpenid() {

        SellerInfo result = sellerService.findSellerInfoByOpenid(openid);
        Assert.assertEquals(openid, result.getOpenid());
    }

    @Test
    public void findSellerInfoByEmail() {
        SellerInfo info = sellerService.findSellerInfoByEmail("dapan@163.com");
        Assert.assertNull(info);
    }

    @Test
    public void save() {
        SellerInfo sellerInfo = new SellerInfo();
        sellerInfo.setSellerId(KeyUtil.genUniqueKey());
        sellerInfo.setOpenid(KeyUtil.genUniqueKey());
        sellerInfo.setSellerName("dapan");
        sellerInfo.setEmail("dapan@163.com");
        sellerInfo.setPassword("123456");
        SellerInfo result = sellerService.save(sellerInfo);
        Assert.assertNotNull(result);
    }

    @Test
    public void findSellerInfoByEmailAndPassword() {
        SellerInfo sellerInfo = sellerService.findSellerInfoByEmailAndPassword("pan728@163.com", "123456");
        Assert.assertNotNull(sellerInfo);
    }
}