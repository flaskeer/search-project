package com.cheche.controller;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by user on 2016/3/10.
 */
@Controller
@RequestMapping("/index")
public class IndexController {

    public static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @RequestMapping(method = RequestMethod.GET)
    public String index(){
        return "index";
    }

    @RequestMapping(value = "/search",method = RequestMethod.GET)
    public String search(@RequestParam(required = true) String search){
        if(StringUtils.isNotBlank(search)){

        }
        return null;
    }
}
