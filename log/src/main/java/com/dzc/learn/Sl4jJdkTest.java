package com.dzc.learn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 对接JDK默认的日志实现
 * <p>
 * SL4j 提供接口门面
 * <p>
 * <p>
 * sl4j-jdk14 jar 提供适配， 实现Sl4j接口 内部adapter调用 util.logging
 *
 * <dependency>
 * <groupId>org.slf4j</groupId>
 * <artifactId>slf4j-api</artifactId>
 * </dependency>
 *
 * <dependency>
 * <groupId>org.slf4j</groupId>
 * <artifactId>slf4j-jdk14</artifactId>
 * </dependency>
 */
public class Sl4jJdkTest {


    private static final Logger logger = LoggerFactory.getLogger(Sl4jJdkTest.class);


    public static void main(String[] args) {

        if (logger.isDebugEnabled()) {
            logger.debug("slf4j-jdk debug message");
        }
        if (logger.isInfoEnabled()) {
            logger.info("slf4j-jdk info message");
        }
        if (logger.isTraceEnabled()) {
            logger.trace("slf4j-jdk trace message");
        }
    }
}
