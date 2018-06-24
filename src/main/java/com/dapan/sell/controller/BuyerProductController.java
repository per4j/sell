package com.dapan.sell.controller;

import com.dapan.sell.dataobject.ProductCategory;
import com.dapan.sell.dataobject.ProductInfo;
import com.dapan.sell.service.CategoryService;
import com.dapan.sell.service.ProductService;
import com.dapan.sell.utils.ResultVOUtil;
import com.dapan.sell.vo.ProductInfoVO;
import com.dapan.sell.vo.ProductVO;
import com.dapan.sell.vo.ResultVO;
import org.apache.logging.log4j.util.PropertiesUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

//    @Autowired
    private ProductService productService;

//    @Autowired
    private CategoryService categoryService;

    @Autowired
    public BuyerProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping("/list")
    public ResultVO list() {

        // 1. 查询所有上架的商品
        List<ProductInfo> productInfoList = productService.findUpAll();

        // 2. 查询类目（一次性查询）
//        List<Integer> categoryTypeList = new ArrayList<>();

        // 传递方法
//        for (ProductInfo productInfo : productInfoList) {
//            categoryTypeList.add(productInfo.getCategoryType());
//        }

        // 精简方法(java8, lambda)
        List<Integer> categoryTypeList = productInfoList.stream()
                .map( p->p.getCategoryType() )
                .collect(Collectors.toList());

        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(categoryTypeList);
        // 3. 数据拼装
        List<ProductVO> productVOList = new ArrayList<>();
        for (ProductCategory category : productCategoryList) {
            ProductVO productVO = new ProductVO();
            productVO.setCategoryType(category.getCategoryType());
            productVO.setCategoryName(category.getCategoryName());

            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for (ProductInfo productInfo : productInfoList) {
                if (productInfo.getCategoryType().equals(category.getCategoryType())) {
                    ProductInfoVO productInfoVO = new ProductInfoVO();

//                    productInfoVO.setProductId(productInfo.getProductId());
//                    productInfoVO.setProductName(productInfo.getProductName());
//                    productInfoVO.setProductDescription(productInfo.getProductDescription());
//                    productInfoVO.setProductIcon(productInfo.getProductIcon());
//                    productInfoVO.setProductPrice(productInfo.getProductPrice());

                    BeanUtils.copyProperties(productInfo, productInfoVO);

                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        }

        return ResultVOUtil.success(productVOList);
    }
}
