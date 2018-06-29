
###1. 登出

####1. 从cookie里查询
        
```
Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
```

####2. 清除redis
 
 ```
stringRedisTemplate.opsForValue().getOperations().delete(String.format(RedisConstant.TOKEN_PREFIX, cookie.getValue()));
 ```
 
####3. 清除cookie

```
CookieUtil.set(response, CookieConstant.TOKEN, null, 0);
```

