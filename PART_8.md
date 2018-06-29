
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