package com.example.controller;

import com.example.bean.AnchorBaseInfoBean;
import com.example.dao.AnchorBaseInfoDao;
import com.example.service.AnchorBaseInfoService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: sunxianpeng
 * \* Date: 2019/8/21
 * \* Time: 13:54
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
@RestController
@RequestMapping(value="/anchor", produces = "application/json; charset=UTF-8")
@Cacheable(cacheNames = {"test"}, cacheManager = "anchorBaseInfoCacheManager")
@Service
public class RedisCacheController {
    @Autowired
    private AnchorBaseInfoService anchorBaseInfoService;
    @Autowired
    private AnchorBaseInfoDao anchorBaseInfoDao;
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    RedisTemplate empRedisTemplate; // 自定义RedisTemplate



    //  http://localhost:8080/anchor/getAnchorBaseInfoByPlatRoomCacheable?platID=2&roomID=1023513
    @RequestMapping(value = "/getAnchorBaseInfoByPlatRoomCacheableRedis", method = {RequestMethod.GET})
//    @Cacheable(value = "anchorinfo" ,key = "#p0 + #p1")
    public AnchorBaseInfoBean getAnchorBaseInfoByPlatRoomCacheableRedis(
            @ApiParam(value = "平台ID", required = true)  @RequestParam(value = "platID", required = true) int platID,
            @ApiParam(value = "房间ID", required = true)  @RequestParam(value = "roomID", required = true) String roomID
    ) {
//        数据
        AnchorBaseInfoBean resultBean=anchorBaseInfoDao.getAnchorBaseInfoByPlatRoom(platID,roomID);
        System.out.println(resultBean.toString());
//        存储redis
//        stringRedisTemplate.opsForList().leftPush("", "v1");
//        stringRedisTemplate.opsForList().leftPush("list", "v2");
//        stringRedisTemplate.opsForList().leftPush("list", "v3");
//        stringRedisTemplate.opsForList().leftPush("list", "v4");
        stringRedisTemplate.opsForValue().append("room_id",resultBean.getRoom_id() );
        // 给redis存对象数据
        // 这里使用默认的JDK序列化器：JdkSerializationRedisSerializer 将序列化后的数据保存到redis中
        // 我们也可以自定义序列化器完成序列化存储
        // 1. 自动手动将对象序列化为JSON字符串
        // 2. 指定redisTemplate默认的序列化器
        redisTemplate.opsForValue().set("plat_room", resultBean);

        empRedisTemplate.opsForValue().set("plat_room_json", resultBean);

        return  resultBean;
    }



}