
####1. pom.xml添加mybatis

```
<dependency>
    <groupId>org.mybatis.spring.boot</groupId>
    <artifactId>mybatis-spring-boot-starter</artifactId>
    <version>1.3.1</version>
</dependency>
```

####2. mapper编写

##### 1. 查询

```
public interface ProductCategoryMapper {

    ProductCategory selectByCategoryType(Integer categoryType);
}
```

####3. resources/mapper/*.xml

```
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.dapan.sell.dataobject.mapper.ProductCategoryMapper">

    <resultMap id="BaseResultMap" type="com.dapan.sell.dataobject.ProductCategory">
        <id column="category_id" property="categoryId" jdbcType="INTEGER" />
        <id column="category_name" property="categoryName" jdbcType="VARCHAR" />
        <id column="category_type" property="categoryType" jdbcType="INTEGER" />
    </resultMap>
    <select id="selectByCategoryType" resultMap="BaseResultMap" parameterType="java.lang.Integer">
      select category_id, category_name, category_type
      from product_category
      where category_type = #{category_type, jdbcType=INTEGER}
  </select>
</mapper>
```

#### 3. 添加MapperScan

```
@SpringBootApplication
@MapperScan(basePackages = "com.dapan.sell.dataobject.mapper")
public class SellApplication {

	public static void main(String[] args) {
		SpringApplication.run(SellApplication.class, args);
	}
}
```

只是简单的查询，官网已不推荐xml形式，个有觉得，使用注解更直观一些。