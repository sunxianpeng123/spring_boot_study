package com.xiaohulu.chapter1;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@SpringBootApplication
public class Chapter1Application {

	public static void main(String[] args) {
		SpringApplication.run(Chapter1Application.class, args);
	}

	/**
	 * 自定义Banner
	 * SpringBoot启动的时候我们可以看到如下内容，这一块其实是可以自定义的哦，而且在 2.X 版本中，
	 * 它支持的格式从文本扩展到banner.txt、banner.jpg、banner.gif、banner.jpeg等等，只需要在resouces目录下添加指定命名的文件即可
	 * @return
	 */
	@GetMapping("/demo1")
	public String demo1() {
//		http://localhost:9090/chapter1/demo1
		return "Hello Luis";
	}

//	@Bean
//	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
//		// 目的是
//		return args -> {
//			System.out.println("来看看 SpringBoot 默认为我们提供的 Bean：");
//			String[] beanNames = ctx.getBeanDefinitionNames();
//			Arrays.sort(beanNames);
//			Arrays.stream(beanNames).forEach(System.out::println);
//		};
//	}
}

