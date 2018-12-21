package com.class2.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.lang.Nullable;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: sunxianpeng
 * \* Date: 2018/12/20
 * \* Time: 20:40
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    /**
     * 创建动态数据源实现数据源切换的功能就是自定义一个类扩展AbstractRoutingDataSource抽象类，
     * 其实该相当于数据源DataSource的路由中介，可以实现在项目运行时根据相应key值切换到对应的数据源
     * DataSource上，有兴趣的同学可以看看它的源码。
     */
    //3
    static final Map<DatabaseType,List<String>> METHOD_TYPE_MAP=new HashMap<>();

    @Nullable
    @Override
    protected  Object determineCurrentLookupKey(){
        DatabaseType type=DatabaseContextHolder.getDatabaseType();
        logger.info("====================dataSource ==========" + type);
        return type;
    }
    void setMethodType(DatabaseType type, String content) {
        List<String> list = Arrays.asList(content.split(","));
        METHOD_TYPE_MAP.put(type, list);
    }




}