###一、买家类目-dao(上)

   买家端
   
    - DAO层设计与开发
    - Service层设计与开发
    - Controller层设计与开发
   

####1. 使用application.yml配置数据库

```
spring:
  datasource:
    driver-class-name: com.mysql.jdbc.Driver
    username: root
    password: 123456
    url: jdbc:mysql://127.0.0.1:3306/sell?characterEncoding=utf-8&useSSL=false
  jpa:
    show-sql: true
```

####2. 在pom.xml中添加jpa配置

```

<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
</dependency>

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
```

####3. 增加类目实体类

```
@Entity
@DynamicUpdate// 更新时间
public class ProductCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;
}
```

> 注意，主键要添加GeneratedValue注解，并且配置生成策略

####4. dao接口

```
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {

    List<ProductCategory> findAllByCategoryTypeIn(List<Integer> categoryTypeList);
}
```

注意，1。Jpa的两个参数，实体类，主键类型
     2。生成的方法规则，如：findxxx()
     
####5. 测试

```
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {

    @Autowired
    private ProductCategoryRepository repository;

    @Test
    public void findOne() {
        ProductCategory category = repository.findById(1).get();
        System.out.println(category.toString());
    }

    @Test
    public void saveTest() {
        ProductCategory category = new ProductCategory();
        category.setCategoryId(1);
        category.setCategoryName("热榜");
        category.setCategoryType(2);
        repository.save(category);
    }

    @Test
    @Transactional // 测试完，回滚测试数据
    public void updateTest() { // 更新操作时，如果不想设置自增主键，需要添加strategy = GenerationType.IDENTITY
        ProductCategory category = new ProductCategory();
        category.setCategoryName("都市");
        category.setCategoryType(7);
        repository.save(category);
    }

    @Test
    public void findAllByCategoryTypeIn() {
        List<ProductCategory> categories = repository.findAllByCategoryTypeIn(Arrays.asList(2, 3, 4));
        Assert.assertNotEquals(0, categories.size());
    }
}
```

###二、买家类目-service

#### 1. 新增CategoryService接口

#### 2. 实现CategoryService接口

```
@Service
public class CategoryServiceImpl implements CategoryService {

//    @Autowired // 不推荐对成员变量添加@Autowired注解
    private ProductCategoryRepository repository;

    @Autowired
    public CategoryServiceImpl(ProductCategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public ProductCategory findOne(Integer categoryId) {
        return repository.findById(categoryId).get();
    }

    @Override
    public List<ProductCategory> findAll() {
        return repository.findAll();
    }

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {
        return repository.findAllByCategoryTypeIn(categoryTypeList);
    }

    @Override
    public ProductCategory save(ProductCategory productCategory) {
        return repository.save(productCategory);
    }
}
```

> 注意：Service的实现类，必须添加@Service注解，不然找不到类

PS: Java变量的初始化顺序为：静态变量或静态语句块–>实例变量或初始化语句块–>构造方法–>@Autowired

#### 3. 测试Service

```
@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceImplTest {

    @Autowired
    private CategoryServiceImpl service;

    @Test
    public void findOne() {
        ProductCategory one = service.findOne(1);
        Assert.assertNotNull(one);
    }

    @Test
    public void findAll() {
        List<ProductCategory> all = service.findAll();
        Assert.assertNotEquals(0, all.size());
    }
}
```