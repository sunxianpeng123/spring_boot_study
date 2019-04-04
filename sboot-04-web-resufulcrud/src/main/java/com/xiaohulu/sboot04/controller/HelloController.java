package com.xiaohulu.sboot04.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.Map;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: sunxianpeng
 * \* Date: 2019/3/30
 * \* Time: 20:56
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
@Controller
public class HelloController {
//http://localhost:8080/hello
    @ResponseBody
    @RequestMapping("/hello")
    public  String hello(){
        return "Hello World!";
    }



//    http://localhost:8080/success
//    查出一些数据在页面展示
    @RequestMapping("/success")
    public  String success(Map<String,Object> map){
//        classpath:/templates/success.html
        map.put("hello","<h1>你好</h1>");
        map.put("users", Arrays.asList("zhangsan","lisi","wangwu"));
        return "success";//返回的success字符串需要和templates/success.html的前缀一样
    }


//    http://localhost:8080
//    访问项目路径或者项目下的index.html都是去templates下去寻找
//    或者添加视图映射 直接跳转到想要的页面
//    @RequestMapping({"/","/index.html"})
//    public String  index(){
//        return  "index";
//    }
}