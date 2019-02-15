package com.xiaohulu.sboot02.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: sunxianpeng
 * \* Date: 2019/2/14
 * \* Time: 20:06
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
@RestController
public class HelloController {
    @Value("${person.last-name}")
    private  String name;
    @RequestMapping("/hello")
    public String sayHello(){
        return "Hello " +name;
    }
}