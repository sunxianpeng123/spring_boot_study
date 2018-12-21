
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;

@SpringBootApplication
@MapperScan("com.xiaohulu.class2")
//注意：@MapperScan("com.winter.mapper")这个注解非常的关键，这个对应了项目中mapper（dao）所对应的包路径，很多同学就是这里忘了加导致异常的
/**
 * 配置：
 * 可以根据个人使用习惯选择使用properties或者yml文件，本项目使用的是yml配置文件，所以把原本application.properties删除，创建一个application.yml文件
 *
 * Group：组织ID，一般分为多个段，这里我只说两段，第一段为域，第二段为公司名称。域又分为org、com、cn等等，其中org为非营利组织，
 * com为商业组织。如阿里、淘宝（com.alibaba/com.taobao）
 * Artifact:唯一标识符，一般是项目名称
 * SpringBoot启动的时候我们可以看到如下内容，这一块其实是可以自定义的哦，而且在 2.X 版本中，
 * 它支持的格式从文本扩展到banner.txt、banner.jpg、banner.gif、banner.jpeg等等，只需要在resouces目录下添加指定命名的文件即可
 */
public class SpringBootMybatisMutilDatabaseApplication {
//    springboot2.0正式版发布之后，很多的组件集成需要变更了，这次将多数据源的使用踩的坑给大家填一填。当前多数据源的主要为主从库，
// 读写分离，动态切换数据源。使用的技术就是AOP进行dao方法的切面，所以大家的方法名开头都需要按照规范进行编写，如：get***、add*** 等等，

	public static void main(String[] args) {
		SpringApplication.run(SpringBootMybatisMutilDatabaseApplication.class, args);
	}


}

