
###1. redis

####1.1 引入redis依赖

```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-redis</artifactId>
</dependency>
```

####1.2 配置redis

application.yml

```
  redis:
    host: 127.0.0.1
    port: 6379
#    password:
```

####1.3 redis简单使用

```
private StringRedisTemplate stringRedisTemplate;
```

赋值

```
stringRedisTemplate.opsForValue().set(
                String.format(RedisConstant.TOKEN_PREFIX, token), // key
                openid, // value
                expire, // 过期时间
                TimeUnit.SECONDS // 时间的格式
        );
```


###2. cookie

```
@GetMapping("/login")
public ModelAndView login(@RequestParam("openid") String openid,
                          HttpServletResponse response,
                          Map<String, Object> map) {

    // 1. openid去和数据库里的数据匹配

    // 2.设置token至redis
    String token = UUID.randomUUID().toString();

    // 3.设置token至cookie
    CookieUtil.set(response, CookieConstant.TOKEN, token, CookieConstant.EXPIRE);

    return new ModelAndView("redirect:" + urlConfig.getUrl() + "seller/order/list");

}
```