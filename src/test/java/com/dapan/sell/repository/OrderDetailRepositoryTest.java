package com.dapan.sell.repository;

import com.dapan.sell.dataobject.OrderDetail;
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
public class OrderDetailRepositoryTest {

    @Autowired
    private OrderDetailRepository repository;

    @Test
    public void saveTest() {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("999999");
        orderDetail.setOrderId("888888");
        orderDetail.setProductId("123123");
        orderDetail.setProductName("Java");
        orderDetail.setProductPrice(new BigDecimal(3.30));
        orderDetail.setProductQuantity(10);
        orderDetail.setProductIcon("http://www.xxx.jpg");

        OrderDetail save = repository.save(orderDetail);
        Assert.assertNotNull(save);
    }

    @Test
    public void findByOrderId() {
        List<OrderDetail> orderDetailList = repository.findByOrderId("888888");
        Assert.assertNotEquals(0, orderDetailList.size());
    }
}