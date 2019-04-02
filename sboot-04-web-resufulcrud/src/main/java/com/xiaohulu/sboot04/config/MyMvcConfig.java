package com.xiaohulu.sboot04.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


/**
 * \* Created with IntelliJ IDEA.
 * \* User: sunxianpeng
 * \* Date: 2019/4/2
 * \* Time: 20:31
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
//使用WebMvcConfigurerAdapter可以来扩展SpringMVC的功能
@Configuration
public class MyMvcConfig  extends WebMvcConfigurerAdapter {
//    现在不能继承WebMvcConfigurerAdapter，会失败
//自定义视图解析器
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
//        super.addViewControllers(registry);
//        浏览器发送/xiaohulu请求，也会来到success页面
        registry.addViewController("/xiaohulu").setViewName("success");
    }
}