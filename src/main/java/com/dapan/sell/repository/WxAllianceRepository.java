package com.dapan.sell.repository;

import com.dapan.sell.dataobject.WxProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WxAllianceRepository extends JpaRepository<WxProduct, String> {
}
