package com.xiaohulu.sboot02;

import com.xiaohulu.sboot02.bean.Person3SelfProperties;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Springboot的单元测试
 * 可以在测试期间很方便的类似编码一样进行自动注入等容器的功能
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class Sboot02ConfigApplicationTests {

	@Autowired
	Person3SelfProperties person;
	@Autowired
	ApplicationContext ioc;
	@Test
	public  void  testHelloSerivce(){
//		Boolean b=ioc.containsBean("helloService02");
		Boolean b=ioc.containsBean("helloService");
		System.out.println(b);
	}
	@Test
	public void contextLoads() {
		System.out.println(person);
	}

}

