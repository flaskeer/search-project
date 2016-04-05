package com.hao.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by user on 2016/3/10.
 */
@Component
public class LogService {

    public static final Logger logger = LoggerFactory.getLogger(LogService.class);

    public void generateLog(){
//        logger.info("hello:{}", UUID.randomUUID().toString());
    }
}
