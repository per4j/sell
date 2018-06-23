package com.dapan.sell.controller;

import com.dapan.sell.vo.ProductInfoVO;
import com.dapan.sell.vo.ProductVO;
import com.dapan.sell.vo.ResultVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @GetMapping("/list")
    public ResultVO list() {

        ResultVO resultVO = new ResultVO();
        ProductVO productVO = new ProductVO();
        ProductInfoVO productInfoVO = new ProductInfoVO();

        productVO.setProductInfoVOList(Arrays.asList(productInfoVO));

        resultVO.setData(Arrays.asList(productVO));

        resultVO.setCode(0);
        resultVO.setMsg("成功");




        return resultVO;

    }
}
