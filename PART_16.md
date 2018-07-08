
####1. pom.xml添加mybatis

```
<dependency>
    <groupId>org.mybatis.spring.boot</groupId>
    <artifactId>mybatis-spring-boot-starter</artifactId>
    <version>1.3.1</version>
</dependency>
```

####2. mapper编写

##### 1.插入

```
public interface ProductCategoryMapper {

    @Insert("insert into product_category(category_name, category_type) values (#{categoryName, jdbcType=VARCHAR}, #{category_type, jdbcType=INTEGER})")
    int insertByMap(Map<String, Object> map);

    @Insert("insert into product_category(category_name, category_type) values (#{categoryName, jdbcType=VARCHAR}, #{categoryType, jdbcType=INTEGER})")
    int insertByObject(ProductCategory category);
}
```

##### 2. 查询

```
public interface ProductCategoryMapper {

    @Select("select * from product_category where category_type=#{categoryType}")
    @Results({
            @Result(column = "category_id", property = "categoryId"),
            @Result(column = "category_type", property = "categoryType"),
            @Result(column = "category_name", property = "categoryName")
    })
    ProductCategory findByCategoryType(Integer categoryType);
}
```

如果报`Expected one result (or null) to be returned by selectOne(), but found: 2`，表示返回的结果为多个，返回结果必须为集合:

```
public interface ProductCategoryMapper {

    //Expected one result (or null) to be returned by selectOne(), but found: 2
    @Select("select * from product_category where category_name=#{categoryName}")
    @Results({
            @Result(column = "category_id", property = "categoryId"),
            @Result(column = "category_type", property = "categoryType"),
            @Result(column = "category_name", property = "categoryName")
    })
    List<ProductCategory> findByCategoryName(String categoryName);
}
```

##### 3. 更新

```
public interface ProductCategoryMapper {

    @Update("update product_category set category_name=#{categoryName} where category_type=#{category_type}")
    int updateByCategoryType(@Param("categoryName") String categoryName, @Param("category_type") Integer categoryType);

    @Update("update product_category set category_name=#{categoryName} where category_type=#{categoryType}")
    int updateByObject(ProductCategory category);
}
```

##### 4. 删除

```
public interface ProductCategoryMapper {

    @Delete("delete from product_category where category_type=#{categoryType}")
    int deleteByCategoryType(Integer categoryType);
}
```

####3. upload_excel.ftl

```
<!DOCTYPE html>
<html>
<head>
    <title>文件上传示例</title>
</head>
<body>
<h2>文件上传示例</h2>
<hr/>
<form method="POST" enctype="multipart/form-data" action="/sell/wx/import">
    <p>
        文件：<input type="file" name="file" />
    </p>
    <p>
        <input type="submit" value="上传" />
    </p>
</form>
</body>
</html>
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

#### 4. dao

```

public class ProductCategoryDao {

    @Autowired
    private ProductCategoryMapper mapper;

    public int addByMap(Map<String, Object> map) {
        return mapper.insertByMap(map);
    }
}
```

#### 5. 使用dao

```
@Service
public class CategoryServiceImpl implements CategoryService {

//    @Autowired // 不推荐对成员变量添加@Autowired注解
    private ProductCategoryRepository repository;

    private ProductCategoryDao dao;

    @Autowired
    public CategoryServiceImpl(ProductCategoryDao dao, ProductCategoryRepository repository) {
        this.repository = repository;
        this.dao = dao;
    }
}
```

#### 6. ComponentScan
如果直接这样运行的话，不出意外，会报：` // No qualifying bean of type 'com.dapan.sell.dataobject.dao.ProductCategoryDao' available: expected at least 1 bean which qualifies as autowire candidate. Dependency annotations: {}`，解决办法：

```
@SpringBootApplication
@ComponentScan(basePackages = "com.dapan.sell.dataobject.dao")
public class SellApplication {

	public static void main(String[] args) {
		SpringApplication.run(SellApplication.class, args);
	}
}
```

#### 7. log打印sql(未测)

application.yml

```
logging:
  level:
    com.dapan.sell.dataobjec.mapper: trace
```