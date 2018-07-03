package com.dapan.sell.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/seller")
@Slf4j
public class LoginController {

    @PostMapping("/login")
    public ModelAndView login(@RequestParam("email") String email,
                              @RequestParam("password") String password) {

        log.error("[登录] email={}, password={}", email, password);
        return new ModelAndView("seller/login");
    }

    @GetMapping("/seller_login")
    public ModelAndView login() {
        return new ModelAndView("seller/seller_login");
    }

    @GetMapping("/seller_register")
    public ModelAndView register() {

        return new ModelAndView("seller/seller_register");
    }

    @PostMapping("/register")
    public ModelAndView doRegister(@RequestParam("email") String email,
                                   @RequestParam("username") String username,
                                   @RequestParam("password") String password) {
        log.error("[注册] email={}, username={}, password={}", email, username, password);

        return null;
    }
}
