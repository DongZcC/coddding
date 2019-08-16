package com.dzc.learn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * sl4j 对接log4j
 * <p>
 * sl4j-api 提供接口
 * <p>
 * slf4j-log4j12 提供适配
 * <p>
 * log4j 提供具体日志实现
 *
 * <dependency>
 * <groupId>org.slf4j</groupId>
 * <artifactId>slf4j-log4j12</artifactId>
 * </dependency>
 * <p>
 * <p>
 * log4j 需要提供log4j.properties 配置文件， 需要提供 Appender配置
 * <p>
 * <p>
 * 依赖
 * <dependency>
 * <groupId>org.slf4j</groupId>
 * <artifactId>slf4j-log4j12</artifactId>
 * </dependency>
 */
public class Sl4jLog4j1Test {


    private static final Logger logger = LoggerFactory.getLogger(Sl4jLog4j1Test.class);


    public static void main(String[] args) {
        if (logger.isDebugEnabled()) {
            logger.debug("slf4j-log4j1 debug message");
        }
        if (logger.isInfoEnabled()) {
            logger.info("slf4j-log4j1 info message");
        }
        if (logger.isTraceEnabled()) {
            logger.trace("slf4j-log4j1 trace message");
        }
    }

}
