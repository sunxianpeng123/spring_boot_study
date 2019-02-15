package com.xiaohulu.sboot02.bean;

import org.hibernate.validator.constraints.Email;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: sunxianpeng
 * \* Date: 2019/2/14
 * \* Time: 18:57
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */

/*
从application.properties或application.yml获取配置信息
 */
/**
 * 将配置文件中配置的每一个属性值，映射到这个组件中
 * @ConfigurationProperties 告诉Springboot将本类中的所有属性和配置文件中相关的配置进行绑定
 *       prefix = "person" 配置文件中person下面的所有属性进行一一映射
 * 只有这个组件是容器中的组件，才能有容器提供的 @ConfigurationProperties功能；
 */
@Component
@ConfigurationProperties(prefix = "person")
@Validated
public class Person {
    @Email//lastname必须是邮箱格式的
    private  String lastName;
    private  Integer age;
    private  Boolean boss;
    private Date  birth;
    private Map<String,Object> maps;
    private List<Object> lists;
    private Dog dog;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Boolean getBoss() {
        return boss;
    }

    public void setBoss(Boolean boss) {
        this.boss = boss;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Map<String, Object> getMaps() {
        return maps;
    }

    public void setMaps(Map<String, Object> maps) {
        this.maps = maps;
    }

    public List<Object> getLists() {
        return lists;
    }

    public void setLists(List<Object> lists) {
        this.lists = lists;
    }

    public Dog getDog() {
        return dog;
    }

    public void setDog(Dog dog) {
        this.dog = dog;
    }

    @Override
    public String toString() {
        return "Person{" +
                "lastName='" + lastName + '\'' +
                ", age=" + age +
                ", boss=" + boss +
                ", birth=" + birth +
                ", maps=" + maps +
                ", lists=" + lists +
                ", dog=" + dog +
                '}';
    }
}