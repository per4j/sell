package com.dapan.sell.service.impl;

import com.dapan.sell.dataobject.SellerInfo;
import com.dapan.sell.repository.SellerRepository;
import com.dapan.sell.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    private SellerRepository repository;

    @Override
    public SellerInfo findSellerInfoByOpenid(String openid) {
        return repository.findByOpenid(openid);
    }
}
