package com.class2.config;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: sunxianpeng
 * \* Date: 2018/12/20
 * \* Time: 21:02
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
@Aspect
@Component
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class DataSourceAspect {
    /**
     * 配置AOP
     * 本章的开头已经说过，多数据源动态切换的原理是利用AOP切面进行动态的切换的，当调用dao接口方法时，根据接口方法的方法名开头进行区分读写。
     */

    private static Logger logger = LoggerFactory.getLogger(DataSourceAspect.class);
    @Pointcut("execution(* com.winterchen.dao.*.*(..))")//切点
    public void aspect() {

    }
    @Before("aspect()")
    public void before(JoinPoint point) {
        //在指定切点的方法之前执行
        String className = point.getTarget().getClass().getName();
        String method = point.getSignature().getName();
        String args = StringUtils.join(point.getArgs(), ",");
        logger.info("className:{}, method:{}, args:{} ", className, method, args);
        try {
            for (DatabaseType type : DatabaseType.values()) {
                List<String> values = DynamicDataSource.METHOD_TYPE_MAP.get(type);
                for (String key : values) {
                    if (method.startsWith(key)) {
                        logger.info(">>{} 方法使用的数据源为:{}<<", method, key);
                        DatabaseContextHolder.setDatabaseType(type);
                        DatabaseType types = DatabaseContextHolder.getDatabaseType();
                        logger.info(">>{}方法使用的数据源为:{}<<", method, types);
                    }
                }
            }
        }
        catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }
/**
 * 如上可以看到，切点切在dao的接口方法中，根据接口方法的方法名进行匹配数据源，然后将数据源set到用于存放数据源线程安全的容器中；
 */

}