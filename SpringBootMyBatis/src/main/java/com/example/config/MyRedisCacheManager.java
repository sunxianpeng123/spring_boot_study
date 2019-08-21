package com.example.config;

import com.example.bean.AnchorBaseInfoBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import java.net.UnknownHostException;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: sunxianpeng
 * \* Date: 2019/8/21
 * \* Time: 15:04
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class MyRedisCacheManager {
    @Configuration
    public class MyRedisConfig {
//        AnchorBaseInfoBean
        @Bean
        public RedisTemplate<Object, AnchorBaseInfoBean> anchorBaseInfoRedisTemplate(RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
            RedisTemplate<Object, AnchorBaseInfoBean> template = new RedisTemplate<>();
            template.setConnectionFactory(redisConnectionFactory);
            Jackson2JsonRedisSerializer<AnchorBaseInfoBean> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(AnchorBaseInfoBean.class);
            template.setDefaultSerializer(jackson2JsonRedisSerializer);
            return template;
        }

        @Bean
        public RedisCacheManager anchorBaseInfoCacheManager(RedisTemplate<Object, AnchorBaseInfoBean> empRedisTemplate) {
            System.out.println(empRedisTemplate);
            RedisCacheManager cacheManager = new RedisCacheManager(empRedisTemplate);
            // 使用前綴，默认使用cacheNames作为前缀
            cacheManager.setUsePrefix(true);
            return cacheManager;
        }
//
//        @Bean
//        public RedisTemplate<Object, Department> deptRedisTemplate(RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
//            RedisTemplate<Object, Department> template = new RedisTemplate<>();
//            template.setConnectionFactory(redisConnectionFactory);
//            Jackson2JsonRedisSerializer<Department> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Department.class);
//            template.setDefaultSerializer(jackson2JsonRedisSerializer);
//            return template;
//        }
//        @Bean
//        public RedisCacheManager deptCacheManager(RedisTemplate<Object, Department> deptRedisTemplate) {
//            RedisCacheManager cacheManager = new RedisCacheManager(deptRedisTemplate);
//            // 使用前綴，默认使用cacheNames作为前缀
//            cacheManager.setUsePrefix(true);
//            return cacheManager;
//        }
        @Primary
        @Bean
        public RedisCacheManager cacheManager(RedisTemplate<Object, Object> redisTemplate) {
            RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
            cacheManager.setUsePrefix(true);
            return cacheManager;
        }
    }

}