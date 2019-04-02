package com.xiaohulu.sboot03;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Sboot03LoggingApplicationTests {
	Logger logger=LoggerFactory.getLogger(getClass());//记录器
	@Test
	public void contextLoads() {
		//日志的级别，下面顺序为由低到高
//		可以调整输出的日志级别,
		logger.trace("这是trance日志。。。");//
		logger.debug("这是debug日志。。。");
//		Springboot默认的日志级别是 info 级别,可以在配置文件中修改日志级别 ,logging.level.com.xiaohulu=trace
//		没有指定级别的则使用默认级别：root级别
		logger.info("这是infor信息");
		logger.warn("这是warn信息");
		logger.error("这是error信息");
	}

}
