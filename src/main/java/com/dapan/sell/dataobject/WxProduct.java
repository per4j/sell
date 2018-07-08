package com.dapan.sell.dataobject;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
public class WxProduct {


    @Id
    private String productId;

    private String productName;

    private String productPic;

    private String productDetailUrl;

    private String storeName;

    private BigDecimal productPrice;

    private Integer productSaleCount;

    private BigDecimal commonIncomeRatio;

    private BigDecimal commonCommission;

    private Integer activityStatus;

    private BigDecimal activityIncomeRatio;

    private BigDecimal activityCommission;

    private Date activityStartTime;

    private Date activityEndTime;

    @Column(name = "seller_ww")
    private String sellerWW;

    private String taobaoSpreaderShortLink;

    private String taobaoSpreaderLink;

    private String taoToken;

    private Integer couponAmount;

    private Integer couponBalance;

    private String couponDenomination;

    private Date couponStart;

    private Date couponEnd;

    private String couponLink;

    private String couponToken;

    private String couponShortLink;
}
