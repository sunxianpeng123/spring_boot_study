package com.xiaohulu.log;

import org.apache.log4j.Logger;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: sunxianpeng
 * \* Date: 2018/12/27
 * \* Time: 20:41
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class Log {
    //    但是在非静态成员方法中是可以访问静态成员方法/变量的。静态变量被所有的对象所共享，在内存中只有一个副本，它当且仅
    // 当在类初次加载时会被初始化。而非静态变量是对象所拥有的，在创建对象的时候被初始化，存在多个副本，各个对象拥有的副本互不影响。
    private static Logger logger=Logger.getLogger(Log.class);


    //static方法一般称作静态方法,在静态方法中不能访问类的非静态成员变量和非静态成员方法,但是在非静态成员方法中是可以访问静态成员方法/变量的。
    public  static  void  error(String string){

        logger.error(string);
    }

    public static  void  error(Exception e){
        logger.error(e);
    }

    public static  void  info(String string){
        logger.info(string);
    }
}