package com.xiaohulu.redis;

import com.xiaohulu.log.Log;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Tuple;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.*;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: sunxianpeng
 * \* Date: 2019/4/16
 * \* Time: 16:05
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class RedisConnect {

    private  static JedisPool jedisPool=null;
    private  static JedisPool getConnect(){
        if (jedisPool==null){
            try{
                Properties properties= new Properties();
                String path=System.getProperty("user.dir")+"/db.properties";
                InputStream is= new FileInputStream(path);
                properties.load(is);
                System.out.println("###########REDIS:"+properties.getProperty("redisIp"));
                JedisPoolConfig config =new JedisPoolConfig();
                config.setMaxIdle(5);
                config.setMaxWaitMillis((1000 * 100));
                config.setTestOnBorrow(true);
                config.setTestOnReturn(true);
                jedisPool = new JedisPool(config, properties.getProperty("redisIp"), Integer.parseInt(properties.getProperty("redisPort")));
                is.close();
            }

            catch(Exception e) {
                jedisPool.close();
                jedisPool=null;
                Log.error(e);
            }
        }
        return jedisPool;
    }

    /**
     *返回列表 key 中指定区间内的元素，区间以偏移量start 和 stop 指定
     * @param jd
     * @param key
     * @param startIndex
     * @param endIndex
     * @return
     */
    public static List<String> lrange(Jedis jd ,String key,int startIndex,int endIndex){
        List<String> list=new ArrayList<>();
        try {
            list= jd.lrange(key, startIndex, endIndex);
        } catch(Exception e ){
            Log.error(e);
            jedisPool.close();
            jedisPool=null;
            getConnect();
            jd = jedisPool.getResource();
            list= jd.lrange(key, startIndex, endIndex);
        }
        return list;
    }

    /**
     *移除并返回列表 key 的头元素。 当 key 不存在时，返回 nil 。
     * @param jd
     * @param key
     * @return
     */
    public static String lpop(Jedis jd,final String key) {
        String result="";
        try {
            result= jd.lpop(key);
        } catch(Exception e ){
            Log.error(e);
            jedisPool.close();
            jedisPool=null;
            getConnect();
            jd = jedisPool.getResource();
            result= jd.lpop(key);
        }
        return result;
    }

    /**
     *将一个或多个值插入到列表头部。 如果 key 不存在，一个空列表会被创建并执行 LPUSH 操作。 当 key 存在但不是列表类型时，返回一个错误。
     * @param jd
     * @param key
     * @param value
     */
    public static void lpush(Jedis jd,final String key,String value) {
        try {
            jd.lpush(key,value);
        } catch(Exception e ){
            Log.error(e);
            jedisPool.close();
            jedisPool=null;
            getConnect();
            jd = jedisPool.getResource();
            jd.lpush(key,value);
        }
    }

    /**
     * 将一个或多个值 value 插入到列表 key 的表尾(最右边)。
     * 如果有多个 value 值，那么各个 value 值按从左到右的顺序依次插入到表尾：
     * 比如对一个空列表 mylist 执行 RPUSH mylist a b c ，得出的结果列表为 a b c ，
     * 等同于执行命令 RPUSH mylist a 、 RPUSH mylist b 、 RPUSH mylist c 。
     * 如果 key 不存在，一个空列表会被创建并执行 RPUSH 操作。
     * 当 key 存在但不是列表类型时，返回一个错误。
     * @param jd
     * @param key
     * @param value
     */
    public static void rpush(Jedis jd,final String key,String value) {
        try {
            jd.rpush(key,value);
        } catch(Exception e ){
            Log.error(e);
            jedisPool.close();
            jedisPool=null;
            getConnect();
            jd = jedisPool.getResource();
            jd.rpush(key,value);
        }
    }


    /**
     * 命令用于删除已存在的键。不存在的 key 会被忽略。
     * @param jd
     * @param key
     * @return
     */
    public static Long del(Jedis jd,String key) {
        long result=0;
        try {
            result= jd.del(key);
        } catch(Exception e ){
            Log.error(e);
            jedisPool.close();
            jedisPool=null;
            getConnect();
            jd = jedisPool.getResource();
            result= jd.del(key);
        }

        return result;
    }

    /**
     *Set 是 String 类型的无序集合。集合成员是唯一的，这就意味着集合中不能出现重复的数据。
     * @param jd
     * @param key
     * @param value
     * @return
     */
    public static String set(Jedis jd, String key, String value) {

        String result="";
        try {
            result= jd.set(key,value);
        } catch(Exception e ){
            Log.error(e);
            jedisPool.close();
            jedisPool=null;
            getConnect();
            jd = jedisPool.getResource();
            result= jd.set(key,value);
        }
        return result;
    }

    /**
     *用于获取指定 key 的值。如果 key 不存在，返回 nil 。如果key 储存的值不是字符串类型，返回一个错误。
     * @param jd
     * @param key
     * @return
     */
    public  static  String  get(Jedis jd, String key ){

        String result="";
        try {
            result= jd.get(key);
        } catch(Exception e ){
            Log.error(e);
            jedisPool.close();
            jedisPool=null;
            getConnect();
            jd = jedisPool.getResource();
            result= jd.get(key);
        }
        return result;
    }

    /**
     *
     * @return
     */
    public  static Jedis getJedis (){
        getConnect();
        Jedis jedis=null;
        try {
            jedis = jedisPool.getResource();
        } catch(Exception e ){
            Log.error(e);
            jedisPool.close();
            jedisPool=null;
            getConnect();
            jedis = jedisPool.getResource();

        }

        return  jedis;
    }

    /**
     * @param redis
     */
    public static void close(Jedis redis) {
        // pool.close();
        if(redis!=null){
            redis.close();
        }
    }

    /**
     *用于同时将多个 field-value (字段-值)对设置到哈希表中。此命令会覆盖哈希表中已存在的字段。
     *如果哈希表不存在，会创建一个空哈希表，并执行 HMSET 操作。
     * @param jd
     * @param key
     * @param value
     * @return
     */
    public static String hmset(Jedis jd, String key, Map<String,String> value) {
        String result="";
        try {
            result= jd.hmset(key,value);
        } catch(Exception e ){
            Log.error(e);
            jedisPool.close();
            jedisPool=null;
            getConnect();
            jd = jedisPool.getResource();
            result= jd.hmset(key,value);
        }
        return result;
    }

    /**
     * 用于查找所有符合给定模式 pattern 的 key 。
     * @param jd
     * @param key
     * @return
     */
    public static Set<String> keys(Jedis jd, String key){
        Set<String> set =new HashSet<String>();
        try {
            set= jd.keys(key);
        } catch(Exception e ){
            Log.error(e);
            jedisPool.close();
            jedisPool=null;
            getConnect();
            jd = jedisPool.getResource();
            set= jd.keys(key);
        }
        return set;
    }

    /**
     *  用于返回哈希表(Redis 哈希(Hash) )中，所有的字段和值。在返回值里，
     * 紧跟每个字段名(field name)之后是字段的值(value)，所以返回值的长度是哈希表大小的两倍。
     * @param jedis
     * @param key
     * @return
     */
    public static Map<String,String> getHashAllByKey(Jedis jedis, String key){
        Map<String,String> maponlineinfo = new HashMap<String, String>();
        try {
            maponlineinfo = jedis.hgetAll(key);
        } catch(Exception e ){
            Log.error(e);
            jedisPool.close();
            jedisPool=null;
            getConnect();
            jedis = jedisPool.getResource();
            maponlineinfo = jedis.hgetAll(key);
        }
        return maponlineinfo;
    }


    /**
     *  Redis 有序集合(sorted set)
     *  Redis Zrangebyscore 返回有序集合中指定分数区间的成员列表。有序集成员按分数值递增(从小到大)次序排列。
     *  具有相同分数值的成员按字典序来排列(该属性是有序集提供的，不需要额外的计算)。
     *  默认情况下，区间的取值使用闭区间 (小于等于或大于等于)，你也可以通过给参数前增加 ( 符号来使用可选的开区间 (小于或大于)。
     * @param jedis
     * @param key
     * @return
     */
    public static Map<String,Double> KeysWithScore(Jedis jedis, String key){
        Set<String> set =new HashSet<String>();
        Set<Tuple> alldata = new HashSet<Tuple>();
        Map<String,Double> maptime = new HashMap<String, Double>();
        try {
            alldata = jedis.zrangeWithScores(key,0,-1);
        } catch(Exception e ){
            Log.error(e);
            jedisPool.close();
            jedisPool=null;
            getConnect();
            jedis = jedisPool.getResource();

            alldata = jedis.zrangeWithScores(key,0,-1);
        }
        for (Tuple item : alldata) {
            maptime.put(item.getElement(),item.getScore());
            //System.out.println(str);
        }
        return maptime;
    }
}