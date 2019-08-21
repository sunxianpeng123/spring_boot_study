package com.example.config;

import com.example.bean.AnchorBaseInfoBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import java.net.UnknownHostException;

@Configuration
public class MyRedisConfig {
    @Bean
    public RedisTemplate<Object, AnchorBaseInfoBean> empRedisTemplate(RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
        RedisTemplate<Object, AnchorBaseInfoBean> template = new RedisTemplate<Object, AnchorBaseInfoBean>();
        template.setConnectionFactory(redisConnectionFactory);
        Jackson2JsonRedisSerializer<AnchorBaseInfoBean> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(AnchorBaseInfoBean.class);
        template.setDefaultSerializer(jackson2JsonRedisSerializer);
        return template;
    }
}