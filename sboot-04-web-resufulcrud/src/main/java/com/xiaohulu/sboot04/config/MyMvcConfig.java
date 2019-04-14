package com.xiaohulu.sboot04.config;


import com.xiaohulu.sboot04.component.MyLocaleResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
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
//    自定义视图解析器
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
//        super.addViewControllers(registry);
//        浏览器发送/xiaohulu请求，也会来到success页面
        registry.addViewController("/xiaohulu").setViewName("success");
    }


//        所有的WebMvcConfigurerAdapter组件都会一起起作用,前提是Springboot 知道此组件，
//        要用 @Bean注册此组件
//        http://localhost:8080
    @Bean
    public  WebMvcConfigurerAdapter webMvcConfigurerAdapter(){
        WebMvcConfigurerAdapter adapter=new WebMvcConfigurerAdapter() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
//                super.addViewControllers(registry);
                registry.addViewController("/").setViewName("login");
                registry.addViewController("/login.html").setViewName("login");
            }
        };
        return adapter;
    }

    @Bean
    public LocaleResolver localeResolver(){
        return  new MyLocaleResolver();
    }

}