package com.xiaohulu.chapter1.Controller;

import com.xiaohulu.chapter1.UserDomain.MyProperties1;
import com.xiaohulu.chapter1.UserDomain.MyProperties2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: sunxianpeng
 * \* Date: 2018/12/21
 * \* Time: 20:12
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
@RequestMapping("/properties")
@RestController
public class PropertiesController {
    private static Logger log= LoggerFactory.getLogger(PropertiesController.class);

    private final MyProperties1 myProperties1;
    private final MyProperties2 myProperties2;
    @Autowired
    public PropertiesController(MyProperties1 myProperties1,MyProperties2 myProperties2) {
        this.myProperties1 = myProperties1;
        this.myProperties2 = myProperties2;
    }
    @GetMapping("/1")
    public MyProperties1 myProperties1(){
        log.info("========================================================================");
        log.info(myProperties1.toString());
        log.info("========================================================================");
        return myProperties1;
    }
    @GetMapping("/2")
    public MyProperties2 myProperties2(){
//        接下来在PropertiesController用来注入MyProperties2测试我们编写的代码
        log.info("========================================================================");
        log.info(myProperties2.toString());
        log.info("========================================================================");
        return myProperties2;
    }


}