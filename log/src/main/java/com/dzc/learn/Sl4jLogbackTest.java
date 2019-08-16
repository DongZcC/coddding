package com.dzc.learn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 接口层调用slf4j-api
 *
 */
public class Sl4jLogbackTest {

    private static final Logger logger = LoggerFactory.getLogger(Sl4jLog4j2Test.class);

    public static void main(String[] args) {
        if (logger.isDebugEnabled()) {
            logger.debug("slf4j-logback debug message");
        }
        if (logger.isInfoEnabled()) {
            logger.info("slf4j-logback info message");
        }
        if (logger.isTraceEnabled()) {
            logger.trace("slf4j-logback trace message");
        }
    }
}
