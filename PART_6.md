

   卖家端
   
    - DAO层设计与开发
    - Service层设计与开发
    - Controller层设计与开发
   

####1. 卖家订单service

```
Assert.assertTrue("查询所有订单列表", orderDTOPage.getTotalElements() > 0);
```

前面是出错后的提示语，后面是条件

####2. 卖家订单-controller

注意，这时使用了freemarker模板技术，需要在pom.xml中引入依赖

```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-freemarker</artifactId>
</dependency>
```

1. 卖家订单列表的实现

```
@Controller
@RequestMapping("/seller/order")
public class SellerOrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "10") Integer size,
                             Map<String, Object> map) {
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        Page<OrderDTO> orderDTOPage = orderService.findList(pageRequest);
        map.put("orderDTOPage", orderDTOPage);

        return new ModelAndView("order/list", map);
    }
}
```

这里不是返回json数据了，所以使用的是`@Controller注解`，返回的结果是`ModelAndView`对象

`new ModelAndView("order/list", map);`， `order/list`表示`resources/templates/order`下的`list.ftl`文件

2. 卖家订单列表分页功能的实现

在SellOrderController中添加分页需要的页数与每页大小

```
map.put("currentPage", page);
map.put("size", size);
```

3. 卖家订单取消订单实现

```
    @GetMapping("/cancel")
    public ModelAndView cancel(@RequestParam("orderId") String orderId, Map<String, Object> map) {

        try {
            OrderDTO orderDTO = orderService.findOne(orderId);
            orderService.cancel(orderDTO);
        } catch (SellException e) {
            log.error("[卖家端取消订单] 发生异常 {}", e.getMessage());

            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/order/list");
            return new ModelAndView("common/error", map);
        }

        map.put("msg", ResultEnum.ORDER_CANCEL_SUCCESS.getMessage());
        map.put("url", "/sell/seller/order/list");

        return new ModelAndView("common/success", map);
    }
```

成功/失败后自动跳转

```
    <script>
        setTimeout('location.href="${url}"', 3000);
    </script>
```

4. 卖家端订单详情的controller实现

####3. 卖家端商品列表