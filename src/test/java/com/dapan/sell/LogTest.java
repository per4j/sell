package com.dapan.sell;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class LogTest {

    private final Logger logger = LoggerFactory.getLogger(LogTest.class);

    @Test
    public void test1() {

        String name = "dapan";
        // 添加lombok插件，使用@Slf4j注解，就可以直接使用log来打印日志，不用担心getLogger()类别错误
        log.debug("debug = {}", name); // 默认不显示debug
        log.info("info = {}", name); // 使用占位符{}，拼接字符串
        log.error("error");

        logger.debug("debug");
        logger.info("info");
        logger.error("error = {}", name);

    }
}
