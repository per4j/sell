package com.dapan.sell.service;

import com.dapan.sell.dataobject.SellerInfo;

public interface SellerService {

    SellerInfo findSellerInfoByOpenid(String openid);

    SellerInfo findSellerInfoByEmail(String email);

    SellerInfo save(SellerInfo sellerInfo);

    SellerInfo findSellerInfoByEmailAndPassword(String email, String password);
}
