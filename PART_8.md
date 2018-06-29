
####1. 新增、修改页面

freemarker if语句

```
<#if (productInfo.categoryType)?? && productInfo.categoryType == category.categoryType>
selected
</#if>
```

对象为空判断

```
${(productInfo.productId)!''}
```

#####1.2 修改表单提交

```
    /**
     * 保存/更新
     */
    @PostMapping("/save")
    public ModelAndView save(@Valid ProductForm form,
                             BindingResult bindingResult,
                             Map<String, Object> map) {

        if (bindingResult.hasErrors()) {
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            map.put("url", "/sell/seller/product/index");
            return new ModelAndView("common/error", map);
        }

        try {
            ProductInfo productInfo = productService.findOne(form.getProductId());
            BeanUtils.copyProperties(form, productInfo);

            productService.save(productInfo);
        } catch (SellException e) {
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/product/index");
            return new ModelAndView("common/error", map);
        }

        map.put("url", "/sell/seller/product/list");
        return new ModelAndView("common/success", map);
    }
```

##### 1.3 新增商品

修改ProductInfo.java，默认为上架状态
```
private Integer productStatus = ProductStatusEnum.UP.getCode(); // 0正常，1下架
```

如果productId不为空，表示修改，为空，表示新增

```
    /**
     * 保存/更新
     */
    @PostMapping("/save")
    public ModelAndView save(@Valid ProductForm form,
                             BindingResult bindingResult,
                             Map<String, Object> map) {

        if (bindingResult.hasErrors()) {
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            map.put("url", "/sell/seller/product/index");
            return new ModelAndView("common/error", map);
        }

        ProductInfo productInfo = new ProductInfo();
        try {
            if (!StringUtils.isEmpty(form.getProductId())) {
                productInfo = productService.findOne(form.getProductId());
            } else {
                form.setProductId(KeyUtil.genUniqueKey());
            }
            BeanUtils.copyProperties(form, productInfo);
            productService.save(productInfo);
        } catch (SellException e) {
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/product/index");
            return new ModelAndView("common/error", map);
        }

        map.put("url", "/sell/seller/product/list");
        return new ModelAndView("common/success", map);
    }
```

#### 2卖家类目功能开发