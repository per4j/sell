package com.dapan.sell.controller;

import com.dapan.sell.config.UrlConfig;
import com.dapan.sell.dataobject.SellerInfo;
import com.dapan.sell.form.SellerInfoForm;
import com.dapan.sell.service.SellerService;
import com.dapan.sell.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping("/seller")
@Slf4j
public class LoginController {

    @Autowired
    private SellerService sellerService;

    @Autowired
    private UrlConfig urlConfig;

    @PostMapping("/login")
    public ModelAndView login(@RequestParam("email") String email,
                              @RequestParam("password") String password,
                              Map<String, Object> map) {

        log.error("[登录] email={}, password={}", email, password);

        SellerInfo sellerInfo = sellerService.findSellerInfoByEmailAndPassword(email, password);
        if (sellerInfo == null) {
            map.put("msg", "邮箱或密码不正确");
            map.put("url", "/sell/seller/seller_login");
            return new ModelAndView("common/error", map);
        }

        map.put("msg", "登录成功~");
        map.put("url", "/sell/seller/seller_login");
        return new ModelAndView("redirect:" + urlConfig.getUrl() + "seller/login?openid=" + sellerInfo.getOpenid());
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
    public ModelAndView doRegister(@Valid SellerInfoForm form,
                                   BindingResult bindingResult,
                                   Map<String, Object> map) {
        log.error("[注册] email={}, username={}, password={}", form.getEmail(), form.getUsername(), form.getPassword());

        if (bindingResult.hasErrors()) {
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            map.put("url", "/sell/seller/seller_register");
            return new ModelAndView("common/error", map);
        }

        SellerInfo result = sellerService.findSellerInfoByEmail(form.getEmail());

        if (result != null) {
            map.put("msg", "邮箱已注册，请重新输入");
            map.put("url", "/sell/seller/seller_register");
            return new ModelAndView("common/error", map);
        }

        SellerInfo sellerInfo = new SellerInfo();
        sellerInfo.setSellerName(form.getUsername());
        sellerInfo.setEmail(form.getEmail());
        sellerInfo.setPassword(form.getPassword());

        sellerInfo.setSellerId(KeyUtil.genUniqueKey());
        sellerInfo.setOpenid(KeyUtil.genUniqueKey());

        SellerInfo info = sellerService.save(sellerInfo);

        if (info == null) {
            map.put("msg", "注册失败");
            map.put("url", "/sell/seller/seller_register");
            return new ModelAndView("common/error", map);
        }

        map.put("msg", "注册成功，请登录~");
        map.put("url", "/sell/seller/seller_login");
        return new ModelAndView("common/success", map);
    }
}
