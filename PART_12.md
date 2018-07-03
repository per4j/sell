
###1. 异常处理

```
@ControllerAdvice
@Slf4j
public class SellExceptionHandler {

    @Autowired
    private UrlConfig urlConfig;

    @ExceptionHandler(value = SellerAuthorizeException.class)
    public ModelAndView handlerAuthorizeException() {
        String url = "redirect:" //http://127.0.0.1:8080/sell/seller/seller_login
                .concat(urlConfig.getUrl())
                .concat("seller/seller_login");
        log.warn("[authorize] " + url);
        return new ModelAndView(url);
    }
}
```

###2. Aspect控制器拦截

```
@Aspect
@Component
@Slf4j
public class SellerAuthorizeAspect {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Pointcut("execution(public * com.dapan.sell.controller.Seller*.*(..))" +
        "&& !execution(public * com.dapan.sell.controller.SellerUserController.*(..))")
    public void verify() {

    }

    @Before("verify()")
    public void doVerify() {

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // 查询cookie
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if (cookie == null) {
            log.warn("[登录校验] Cookie中查不到token");
            throw new SellerAuthorizeException();
        }

        // 去redis里查
        String tokenValue = stringRedisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_PREFIX, cookie.getValue()));

        if (StringUtils.isEmpty(tokenValue)) {
            log.warn("[登录校验] redis中查不到token");
            throw new SellerAuthorizeException();
        }

    }
}
```
