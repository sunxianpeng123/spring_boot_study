package com.xiaohulu;

import com.xiaohulu.bean.TestBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class SearchxuehumsgserverStudyApplication {

	public static void main(String[] args) {
		SpringApplication.run(SearchxuehumsgserverStudyApplication.class, args);
	}
//	@RequestMapping("/test")
//	public  String  test(){
//		return  "XuehuStatisController RestController";
//	}

	@Bean
	@Scope("prototype")
	public TestBean testBean() {
		System.out.println("springboot已经启动。。。");
		return new TestBean();
	}

}
