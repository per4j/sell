package com.dapan.sell.service;

import com.dapan.sell.dataobject.SellerInfo;

public interface SellerService {

    SellerInfo findSellerInfoByOpenid(String openid);
}
