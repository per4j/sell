
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
