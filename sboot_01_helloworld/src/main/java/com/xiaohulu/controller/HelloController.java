package com.xiaohulu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: sunxianpeng
 * \* Date: 2019/2/13
 * \* Time: 20:50
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
//标注controller
@Controller
public class HelloController {

    //将helloworld 返给浏览器
    //响应浏览器的 hello请求
    @ResponseBody
    @RequestMapping("/hello")
    public String hello() {
        return "Hello World!";
    }

}