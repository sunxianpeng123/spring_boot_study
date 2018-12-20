package com.class2;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.xiaohulu.class2")
//注意：@MapperScan("com.winter.mapper")这个注解非常的关键，这个对应了项目中mapper（dao）所对应的包路径，很多同学就是这里忘了加导致异常的
/**
 * 配置：
 * 可以根据个人使用习惯选择使用properties或者yml文件，本项目使用的是yml配置文件，所以把原本application.properties删除，创建一个application.yml文件
 */
public class SpringBootMybatisMutilDatabaseApplication {
//    springboot2.0正式版发布之后，很多的组件集成需要变更了，这次将多数据源的使用踩的坑给大家填一填。当前多数据源的主要为主从库，
// 读写分离，动态切换数据源。使用的技术就是AOP进行dao方法的切面，所以大家的方法名开头都需要按照规范进行编写，如：get***、add*** 等等，

	public static void main(String[] args) {
		SpringApplication.run(SpringBootMybatisMutilDatabaseApplication.class, args);
	}

}

