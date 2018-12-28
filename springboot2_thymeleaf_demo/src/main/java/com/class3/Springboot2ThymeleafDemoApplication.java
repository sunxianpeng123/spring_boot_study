package com.class3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Springboot2ThymeleafDemoApplication {
	/**
	 * 在前面几章中已经介绍了如何创建一个SpringBoot项目，同时简单的描述了SpringBoot REST Web服务。
	 * 除此之外它也是支持如JSP、Thymeleaf、FreeMarker、Mustache、Velocity等各种模板引擎，同时还为开发者提供了自定义模板扩展的支持。
	 *使用嵌入式Servlet容器时，请避免使用JSP，因为使用JSP打包后会存在一些限制。在SpringBoot使用上述模板，默认从src/main/resources/templates下加载。
	 * @param args
	 */
	/**
	 * 				thymeleaf介绍
	 Thymeleaf是现代化服务器端的Java模板引擎，不同与其它几种模板的是Thymeleaf的语法更加接近HTML，并且具有很高的扩展性。详细资料可以浏览官网。
	 特点
	 支持无网络环境下运行，由于它支持 html 原型，然后在 html 标签里增加额外的属性来达到模板+数据的展示方式。浏览器解释 html 时会忽略未定义的标签属性，
	 所以 thymeleaf 的模板可以静态地运行；当有数据返回到页面时，Thymeleaf 标签会动态地替换掉静态内容，使页面动态显示。所以它可以让前端小姐姐在浏览器中查看页面的静态效果，
	 又可以让程序员小哥哥在服务端查看带数据的动态页面效果。
	 开箱即用，为Spring提供方言，可直接套用模板实现JSTL、 OGNL表达式效果，避免每天因套用模板而修改JSTL、 OGNL标签的困扰。同时开发人员可以扩展自定义的方言。
	 SpringBoot官方推荐模板，提供了可选集成模块(spring-boot-starter-thymeleaf)，可以快速的实现表单绑定、属性编辑器、国际化等功能。
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(Springboot2ThymeleafDemoApplication.class, args);
	}

}

