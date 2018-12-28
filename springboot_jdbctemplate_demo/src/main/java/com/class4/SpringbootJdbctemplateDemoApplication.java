package com.class4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootJdbctemplateDemoApplication {
	/**
	 * Spring Framework对数据库的操作在JDBC上面做了深层次的封装，通过依赖注入功能，可以将DataSource注册到JdbcTemplate之中，
	 * 使我们可以轻易的完成对象关系映射，并有助于规避常见的错误，在SpringBoot中我们可以很轻松的使用它。
	 *1\速度快，对比其它的ORM框架而言，JDBC的方式无异于是最快的.
	 * 2\配置简单，Spring自家出品，几乎没有额外配置
	 * 3\学习成本低，毕竟JDBC是基础知识，JdbcTemplate更像是一个DBUtils
	 * @param args
	 */

	public static void main(String[] args) {
		SpringApplication.run(SpringbootJdbctemplateDemoApplication.class, args);
	}

}

