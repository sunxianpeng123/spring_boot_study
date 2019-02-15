package com.xiaohulu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: sunxianpeng
 * \* Date: 2019/2/13
 * \* Time: 20:46
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
@RestController
@SpringBootApplication//标注主程序类，说明这是一个spring boot应用
public class HelloWorldApp {

    @RequestMapping("/")
    public String hello(){
        return"Hello world!";
    }
//    http://localhost:8080/hello
    public static void main(String[] args) {
//        spring boot 应用启动
        SpringApplication.run(HelloWorldApp.class,args);

    }

}