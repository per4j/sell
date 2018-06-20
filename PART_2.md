###Logback配置

####1.区分info和error日志
    
   在application.properties文件中添加：
    
    控制台log格式
    logging.pattern.console= %d - %msg%n 
    
    log输出路径
    #logging.path=/Users/per4j/log/tomcat/
    log输出文件名
    logging.file=/Users/per4j/log/tomcat/sell.log

####2.每天生成一个日志文件
