package com.dapan.sell.repository;

import com.dapan.sell.dataobject.SellerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepository extends JpaRepository<SellerInfo, String> {

    SellerInfo findByOpenid(String openid);

    SellerInfo findByEmail(String email);

    SellerInfo findByEmailAndPassword(String email, String password);
}
