package com.xiaohulu;

import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: sunxianpeng
 * \* Date: 2019/4/15
 * \* Time: 20:58
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
//swagger2的配置文件，在项目的启动类的同级文件建立
@Configuration
@EnableSwagger2
public class Swagger2 {
    //swagger2的配置文件，这里可以配置swagger2的一些基本的内容，比如扫描的包等等
    @Bean
    public Docket createRestApi(){

        return  new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.springboot.example.Controller"))
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build();

    }

    //构建 api文档的详细信息函数,注意这里的注解引用的是哪个
    private ApiInfo apiInfo(){
        return  new ApiInfoBuilder()
                //页面标题
//                .title("Spring Boot 测试使用 Swagger2 构建RESTful API")
                .title("大数据计算统计任务API")
                //创建人
                .contact(new Contact("MarryFeng", "http://www.baidu.com", ""))
                //版本号
                .version("1.0")
                //描述
                .description("计算统计数据")
                .termsOfServiceUrl("http://www.xiaohulu.com/")
                .build();
//                .title("大数据计算统计任务API")
//                .description("计算统计数据")
//                .termsOfServiceUrl("http://www.xiaohulu.com/")
//                .version("1.0")
//                .build();
    }





}