package com.dapan.sell.handler;

import com.dapan.sell.config.UrlConfig;
import com.dapan.sell.exception.SellerAuthorizeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

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
