package com.class1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
/**
 * https://blog.csdn.net/Winter_chen001/article/details/81204116
 * springboot整合mybatis 使用HikariCP连接池
 * CREATE TABLE t_user( userId INT NOT NULL PRIMARY KEY AUTO_INCREMENT, userName VARCHAR(255) NOT NULL , password VARCHAR(255) NOT NULL , phone VARCHAR(255) NOT NULL ) ENGINE=INNODB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8;
**/
 public class SpringBoot2xApplication {

	public static void main(String[] args) {

		SpringApplication.run(SpringBoot2xApplication.class, args);
	}

}

