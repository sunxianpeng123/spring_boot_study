package com.xiaohulu.chapter1.UserDomain;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: sunxianpeng
 * \* Date: 2018/12/21
 * \* Time: 20:10
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
@Component
@ConfigurationProperties(prefix = "my1")//前缀为my1的配置
public class MyProperties1 {
    private int age ;
    private  String name ;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "MyProperties1{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}