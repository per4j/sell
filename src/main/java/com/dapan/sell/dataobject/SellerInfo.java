package com.dapan.sell.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class SellerInfo {

    @Id
    private String sellerId;

    private String sellerName;

    private String password;

    private String openid;
}
