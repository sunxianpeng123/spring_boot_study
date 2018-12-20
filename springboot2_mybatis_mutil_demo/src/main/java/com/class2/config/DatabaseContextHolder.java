package com.class2.config;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: sunxianpeng
 * \* Date: 2018/12/20
 * \* Time: 20:36
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */


public class DatabaseContextHolder {
    /**
     *     创建线程安全的DatabaseType容器
     *     多数据源必须要保证数据源的线程安全的
     */

    private static final ThreadLocal<DatabaseType> contextHolder=new ThreadLocal<>();//用于存放多线程环境下的成员变量

    public static void setDatabaseType(DatabaseType type) {
        contextHolder.set(type);
    }
    public static DatabaseType getDatabaseType() {
        return contextHolder.get();
    }
}