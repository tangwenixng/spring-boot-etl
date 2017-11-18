package com.twx.controller;

import com.twx.db.LogRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by twx on 2017/11/18.
 */
@RestController
@RequestMapping("/api")
public class APIController {
    private static final Logger logger = LoggerFactory.getLogger(APIController.class);

    @Value("${sevs.pushExUrl}")
    private String pushExUrl;

    @Autowired
    private LogRepository logRepository;

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public String getTest(){
        logger.debug("GetTest "+pushExUrl);
        long count = logRepository.count();
        logger.info("log size is "+count);
        return "api test";
    }
}
