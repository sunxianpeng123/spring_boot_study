package com.xiaohulu.sboot02;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

//@ImportResource(locations = {"classpath:beans.xml"})
@SpringBootApplication
public class Sboot02ConfigApplication {

	public static void main(String[] args) {
		SpringApplication.run(Sboot02ConfigApplication.class, args);
	}

}

