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

    @Override
    public SellerInfo findSellerInfoByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public SellerInfo findSellerInfoByEmailAndPassword(String email, String password) {
        return repository.findByEmailAndPassword(email, password);
    }

    @Override
    public SellerInfo save(SellerInfo sellerInfo) {
        return repository.save(sellerInfo);
    }
}
