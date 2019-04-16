package com.xiaohulu.dao;

import com.xiaohulu.log.Log;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author xj
 */

public class LiveShowXuehuConnectionSource {
	 private static BasicDataSource dataSource = null;
	    
	    public static void init() {
	        if (dataSource != null) {
	                 try {
	                     dataSource.close();
	                 } catch (Exception e) {
	                		Log.error(e);
	                 }
	                 dataSource = null;
	             }
	         try {
	        	 
	        	 Properties properties= new Properties();
		   	     String path=System.getProperty("user.dir")+"/db.properties";
		   	     InputStream is= new FileInputStream(path);
	             properties.load(is);
	             System.out.println("###########DBIP:"+properties.getProperty("dbIpXuehu"));
	             Properties p = new Properties();
	             p.setProperty("driverClassName", "com.mysql.jdbc.Driver");
	             p.setProperty("url", "jdbc:mysql://"+properties.getProperty("dbIpXuehu")+":"+properties.getProperty("dbPortXuehu")+"/"+properties.getProperty("dbNameXuehu")+"?autoReconnect=true&useUnicode=true&characterEncoding=utf8&useSSL=false");
	             p.setProperty("password", properties.getProperty("dbPasswordXuehu"));
	             p.setProperty("username",  properties.getProperty("dbUserXuehu"));
	             p.setProperty("maxActive", "150");
	             p.setProperty("maxIdle", "20");
	             p.setProperty("maxWait", "1000");
	             p.setProperty("testOnBorrow", "true");
	             p.setProperty("testWhileIdle", "true");
	             p.setProperty("testOnReturn", "true");
	             p.setProperty("timeBetweenEvictionRunsMillis","1800000");
	             p.setProperty("minEvictableIdleTimeMillis","1800000");
	             p.setProperty("numTestsPerEvictionRun","3");
	             p.setProperty("validationQuery", "select 1");
	             p.setProperty("removeAbandoned", "true");
	             p.setProperty("removeAbandonedTimeout", "120");
	             p.setProperty("logAbandoned", "true");
	             dataSource = (BasicDataSource) BasicDataSourceFactory.createDataSource(p);
	         } catch (Exception e) {
	         	Log.error(e);
	       }

	     }
	    
	    
    public static void  close(Connection conn ){
	    	
	    	try {
	    		if(conn!=null)
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
	    	
	    	
	    }
    public static synchronized Connection getConnection()  {
        if (dataSource == null) {
            init();
        }
        Connection conn = null;
        if (dataSource != null) {
            try {
				conn = dataSource.getConnection();
				if(conn.isClosed()|| conn==null){
					
					dataSource=null;
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					getConnection();
					
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				Log.error(e);
				dataSource=null;
				 return null;
			}
        }
        return conn;


    }
	    
	    public  static Connection  getConnectForReal()  {
	 	   Connection conn=null;
	       try{
	     	      Properties properties= new Properties();
	     	      String path=System.getProperty("user.dir")+"/db.properties";
	     	      InputStream is= new FileInputStream(path);
	               properties.load(is);
	               System.out.println("###########DBIP:"+properties.getProperty("dbIpXuehu"));
	               conn = DriverManager.getConnection("jdbc:mysql://"+properties.getProperty("dbIpXuehu")+":"+properties.getProperty("dbPortXuehu")+"/"+properties.getProperty("dbNameXuehu")+"?autoReconnect=true&rewriteBatchedStatements=true&useUnicode=true&characterEncoding=utf8&useSSL=false", properties.getProperty("dbUserXuehu"),  properties.getProperty("dbPasswordXuehu"));
	               is.close();
	           }

	       catch(Exception e) {
	     	  Log.error(e);
	           //e.printStackTrace();
	       }
	       return   conn;
	     }
	
 

}
