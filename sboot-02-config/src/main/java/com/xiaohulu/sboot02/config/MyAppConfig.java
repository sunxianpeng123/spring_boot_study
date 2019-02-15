package com.xiaohulu.sboot02.config;

import com.xiaohulu.sboot02.service.HelloService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: sunxianpeng
 * \* Date: 2019/2/14
 * \* Time: 20:58
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
@Configuration//指定这是一个配置类
public class MyAppConfig {
    //将方法的返回值添加到容器中，容器中这个组件默认的id就是方法名
    @Bean
    public HelloService helloService02(){
        System.out.println("配置类@Bean给容器中添加组件了");
        return  new HelloService();
    }
}