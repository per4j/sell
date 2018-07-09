#### 用测试模拟并发

- 使用apache ab工具

- ab -n 100 -c 100 http://www.baidu.com -n表示发出一百个请求，-c表示一百个并发，相当于100个人同时发出请求

- ab -t 60 -c 100 http://www.baidu.com -t表示60秒，-c表示100个并发，表示在连续的60秒内不停的发出请求


一般机器，支持1000个请求，100个并发是没问题的。


#### 测试

1. 在浏览器查看秒杀活动：

    > http://127.0.0.1:8080/sell/skill/query/123456

2. 在浏览器参与秒杀（下单）：

    > http://127.0.0.1:8080/sell/skill/order/123456

3. ab测试

    > ab -n 100 -c 100 http://127.0.0.1:8080/sell/skill/order/123456

4. 查看浏览器测试结果

    > 国庆活动，皮蛋粥特价，限量份100000 还剩：99992 份 该商品成功下单用户数目：105 人

#### 使用synchronized解决数据不一致问题

```
@Service
public class SecKillServiceImpl implements SecKillService {

    @Override
    public synchronized void orderProductMockDiffUser(String productId) {

        // 1.查询该商品库存，为0则活动结束
        int stockNum = stock.get(productId);
        if (stockNum == 0) {
            throw new SellException(100, "活动结束");
        } else {
            // 2.下单（模拟不同用户openid不同）
            orders.put(KeyUtil.genUniqueKey(), productId);
            // 3. 减库存
            stockNum = stockNum - 1;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            stock.put(productId, stockNum);
        }
    }
}
```

再次执行`ab -n 100 -c 100 http://127.0.0.1:8080/sell/skill/order/123456`

刷新浏览器：
    
    国庆活动，皮蛋粥特价，限量份100000 还剩：99900 份 该商品成功下单用户数目：100 人

终端提示：
    
    Benchmarking 127.0.0.1 (be patient)...

没错，太慢了!

下一篇使用`redis`解决并发问题