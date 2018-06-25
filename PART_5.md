###一、买家订单-dao

   买家端
   
    - DAO层设计与开发
    - Service层设计与开发
    - Controller层设计与开发
   

####1. 买家订单dao

使用枚举来存储状态

```
public enum OrderStatusEnum {

    NEW_ORDER(0, "新订单"),
    FINISHED(1, "完结"),
    CANCEL(2, "已取消")
    ;

    private Integer code;
    private String msg;

    OrderStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
```

###二、买家订单-service

####1. 创建订单
 
创建订单流程：

1. 查询商品
    从前端传来的订单详情中，获取产品id,如果不存在，抛异常；

2. 计算订单总价
    使用BigDecimal#multiply()，BigDecimal#add()
    
    订单详情入库，注意订单详情id的生成规则
    
3. 写入数据库
    注意设置订单的状态，支付的状态
    
4. 扣库存
    从前端dto中获取产品id和数量，调用productService.decreaseStock()减库存
    
#### 2. 查询订单

#### 3. 取消订单

#### 4. 完成订单和支付订单