package com.xiaohulu.sboot04;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

import java.util.Locale;

@SpringBootApplication
public class Sboot04WebResufulcrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(Sboot04WebResufulcrudApplication.class, args);
	}

////	如何定制：可以自己给容器中添加一个视图解析器，自动的将其组合起来,如下
//	@Bean
//	public ViewResolver myViewResolver(){
//		return null;
//	}
//	private static  class  MyViewResolver implements ViewResolver{
//
//		@Override
//		public View resolveViewName(String s, Locale locale) throws Exception {
//			return null;
//		}
//	}

}
