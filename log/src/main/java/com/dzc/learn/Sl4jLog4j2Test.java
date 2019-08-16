package com.dzc.learn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * sl4j -> 对接 log4j2
 * <p>
 * 接口slf4j-api
 * <p>
 * 调用log4j-sl4j-impl 的实现
 * <p>
 * impl的实现 调用log4j-api.jar 的接口
 * <p>
 * log4j-core的jar实现
 *
 * <dependency>
 * <groupId>org.apache.logging.log4j</groupId>
 * <artifactId>log4j-core</artifactId>
 * </dependency>
 *
 * <dependency>
 * <groupId>org.apache.logging.log4j</groupId>
 * <artifactId>log4j-slf4j-impl</artifactId>
 * </dependency>
 */
public class Sl4jLog4j2Test {


    private static final Logger logger = LoggerFactory.getLogger(Sl4jLog4j2Test.class);


    public static void main(String[] args) {
        if (logger.isDebugEnabled()) {
            logger.debug("slf4j-log4j2 debug message");
        }
        if (logger.isInfoEnabled()) {
            logger.info("slf4j-log4j2 info message");
        }
        if (logger.isTraceEnabled()) {
            logger.trace("slf4j-log4j2 trace message");
        }
    }
}
