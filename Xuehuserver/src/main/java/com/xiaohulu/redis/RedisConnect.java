package com.xiaohulu.redis;

import com.xiaohulu.log.Log;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Tuple;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.*;

public class RedisConnect {
	private static JedisPool JedisPool = null;
	
	
	   private static JedisPool    getConnect()  {
		   if(JedisPool==null){
		   
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
			              JedisPool = new JedisPool(config, properties.getProperty("redisIp"), Integer.parseInt(properties.getProperty("redisPort")));
			              is.close();	    
			          }
		
			      catch(Exception e) {
			    	  JedisPool.close();
			    	  JedisPool=null;
			         Log.error(e);
			      }
	      }
		   
	      return   JedisPool;
	    }
	   
	   public  static  List<String>  lrange(Jedis jd, String key , int startIndex, int endIndex){
		   
		     List<String> list = new ArrayList<String>();
		     try {
		    	 list= jd.lrange(key, startIndex, endIndex);
		     }
		     catch(Exception e ){
		    	   Log.error(e);
				   JedisPool.close();
			       JedisPool=null;
				   getConnect();
				   jd = JedisPool.getResource();
				   list= jd.lrange(key, startIndex, endIndex);
		     }
		     
		     return list;
		   
	   }
	   
	   
	   public static String lpop(Jedis jd,final String key) {
		   String result="";
		     try {
		    	 result= jd.lpop(key);
		     }
		     catch(Exception e ){
		    	   Log.error(e);
				   JedisPool.close();
			       JedisPool=null;
				   getConnect();
				   jd = JedisPool.getResource();
				   result= jd.lpop(key);
		     }
		     
		     return result;
		   
		   
	   }
	   
	   public static void lpush(Jedis jd,final String key,String value) {
		     try {
		    	 jd.lpush(key,value);
		     }
		     catch(Exception e ){
		    	   Log.error(e);
				   JedisPool.close();
			       JedisPool=null;
				   getConnect();
				   jd = JedisPool.getResource();
				   jd.lpush(key,value);
		     }
		     
		   
		   
	   }
	   
	   public static void rpush(Jedis jd,final String key,String value) {
		     try {
		    	 jd.rpush(key,value);
		     }
		     catch(Exception e ){
		    	   Log.error(e);
				   JedisPool.close();
			       JedisPool=null;
				   getConnect();
				   jd = JedisPool.getResource();
				   jd.rpush(key,value);
		     }
		     
		   
		   
	   }
	   
	   
	   
	   public static Long del(Jedis jd,String key) {
		   long result=0;
		     try {
		    	 result= jd.del(key);
		     }
		     catch(Exception e ){
		    	   Log.error(e);
				   JedisPool.close();
			       JedisPool=null;
				   getConnect();
				   jd = JedisPool.getResource();
				   result= jd.del(key);
		     }
		     
		     return result;
		  }

	   
	   public static String set(Jedis jd, String key, String value) {
		   
		   String result="";
		     try {
		    	 result= jd.set(key,value);
		     }
		     catch(Exception e ){
		    	   Log.error(e);
				   JedisPool.close();
			       JedisPool=null;
				   getConnect();
				   jd = JedisPool.getResource();
				   result= jd.set(key,value);
		     }
		     
		     return result;
		   
		   
	   }
	   
	   public  static  String  get(Jedis jd, String key ){
		   
		   String result="";
		     try {
		    	 result= jd.get(key);
		     }
		     catch(Exception e ){
		    	   Log.error(e);
				   JedisPool.close();
			       JedisPool=null;
				   getConnect();
				   jd = JedisPool.getResource();
				   result= jd.get(key);
		     }
		     
		     return result;
		   
	   }
	   
	   
	   public  static Jedis getJedis (){
		   getConnect();
		   
		   Jedis jedis=null;
		   try {
			 jedis = JedisPool.getResource();
		   }
		   catch(Exception e ){
			   Log.error(e);
			   JedisPool.close();
		       JedisPool=null;
			   getConnect();
			   jedis = JedisPool.getResource();
			 
		   }
			
			return  jedis;
	   }
	   
	   public static void close(Jedis redis) {
		  // pool.close();
		   if(redis!=null){
		   redis.close();
		   }
		   
		   
		   
	   }
	   
   public static String hmset(Jedis jd, String key, Map<String,String> value) {
		   
		   String result="";
		     try {
		    	 result= jd.hmset(key,value);
		     }
		     catch(Exception e ){
		    	   Log.error(e);
				   JedisPool.close();
			       JedisPool=null;
				   getConnect();
				   jd = JedisPool.getResource();
				   result= jd.hmset(key,value);
		     }
		     
		     return result;
		   
		   
	   }
   
   
  public static Set<String> keys(Jedis jd, String key){
	  
	  Set<String> set =new HashSet<String>();
	  
	   try {
		   set= jd.keys(key);
	     }
	     catch(Exception e ){
	    	   Log.error(e);
			   JedisPool.close();
		       JedisPool=null;
			   getConnect();
			   jd = JedisPool.getResource();
			   set= jd.keys(key);
	     }
	     
	     return set;
	  
	  
  }



	public static Map<String,String> getHashAllByKey(Jedis jedis, String key){
		Map<String,String> maponlineinfo = new HashMap<String, String>();

		try {
			maponlineinfo = jedis.hgetAll(key);
		}
		catch(Exception e ){
			Log.error(e);
			JedisPool.close();
			JedisPool=null;
			getConnect();
			jedis = JedisPool.getResource();
			maponlineinfo = jedis.hgetAll(key);
		}

		return maponlineinfo;
	}






	public static Map<String,Double> KeysWithScore(Jedis jedis, String key){

		Set<String> set =new HashSet<String>();
		Set<Tuple> alldata = new HashSet<Tuple>();
		Map<String,Double> maptime = new HashMap<String, Double>();
		try {
			alldata = jedis.zrangeWithScores(key,0,-1);
		}
		catch(Exception e ){
			Log.error(e);
			JedisPool.close();
			JedisPool=null;
			getConnect();
			jedis = JedisPool.getResource();

			alldata = jedis.zrangeWithScores(key,0,-1);
		}
		for (Tuple item : alldata) {

			maptime.put(item.getElement(),item.getScore());
			//System.out.println(str);
		}
		return maptime;
	}

	   
	   
}
