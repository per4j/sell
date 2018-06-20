###Logback配置

   在application.properties文件中添加：
    
    控制台log格式
    logging.pattern.console= %d - %msg%n 
    
    log输出路径
    #logging.path=/Users/per4j/log/tomcat/
    log输出文件名
    logging.file=/Users/per4j/log/tomcat/sell.log

####1.区分info和error日志

#####新增logback-spring.xml

1.1 配置info文件

```
<!--配置info文件-->
<appender name="fileInfoLog" class="ch.qos.logback.core.rolling.RollingFileAppender">
    <filter class="ch.qos.logback.classic.filter.LevelFilter">
        <level>ERROR</level>
        <onMatch>DENY</onMatch>
        <onMismatch>ACCEPT</onMismatch>
    </filter>
    <encoder>
        <pattern>
            %msg%n
        </pattern>
    </encoder>
    <!--滚动策略-->
    <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
        <!--路径-->
        <fileNamePattern>/Users/per4j/log/tomcat/info.%d.log</fileNamePattern>
    </rollingPolicy>
</appender>
```

1.2 配置error文件

```
<!--配置error文件-->
<appender name="fileErrorLog" class="ch.qos.logback.core.rolling.RollingFileAppender">
    <filter class="ch.qos.logback.classic.filter.ThresholdFilter">
        <level>ERROR</level>
    </filter>
    <encoder>
        <pattern>
            %msg%n
        </pattern>
    </encoder>
    <!--滚动策略-->
    <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
        <!--路径-->
        <fileNamePattern>/Users/per4j/log/tomcat/error.%d.log</fileNamePattern>
    </rollingPolicy>
</appender>
```

1.3 引用配置项

```
<root level="info">
    <appender-ref ref="consoleLog" />
    <appender-ref ref="fileInfoLog" />
    <appender-ref ref="fileErrorLog" />
</root>
```
    


####2.每天生成一个日志文件

```
<!--滚动策略-->
<rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
    <!--路径-->
    <fileNamePattern>/Users/per4j/log/tomcat/error.%d.log</fileNamePattern>
</rollingPolicy>
```