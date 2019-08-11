package com.xiaohulu.sboot01.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: sunxianpeng
 * \* Date: 2019/2/14
 * \* Time: 15:49
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
//@Controller
//@ResponseBody//这个类的所有方法返回的数据直接写给浏览器
@RestController//可以代替@Controller和@ResponseBody
public class HelloController {
    private Logger logger = LoggerFactory.getLogger(HelloController.class);

    //@ResponseBody
    @RequestMapping("/hello")
    public String hello(){
        return "hello world quick!";
    }
//    restApi

    @GetMapping("/docker")
    public String hellDocker(){
        logger.info("docker hello world !!!");
        return  "Hello World !!!";
    }

}