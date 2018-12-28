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
 * \* Created with IntelliJ IDEA.
 * \* User: sunxianpeng
 * \* Date: 2018/12/27
 * \* Time: 21:26
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class LiveShowConnectionSource {

    public static BasicDataSource dataSource=null;

    public static void init(){
        if(dataSource!=null){
            try {
                dataSource.close();
            }catch (Exception e){
                Log.error(e);
            }
            dataSource=null;
        }
        try {
            Properties properties= new Properties();
            String path=System.getProperty("user.dir")+"/db.properties";
            InputStream is= new FileInputStream(path);
            properties.load(is);
            System.out.println("###########DBIP:"+properties.getProperty("dbIp"));
            Properties p = new Properties();
            p.setProperty("driverClassName", "com.mysql.jdbc.Driver");
            p.setProperty("url", "jdbc:mysql://"+properties.getProperty("dbIp")+":"+properties.getProperty("dbPort")+"/"+properties.getProperty("dbName")+"?autoReconnect=true&useUnicode=true&characterEncoding=utf8&useSSL=false");
            p.setProperty("password", properties.getProperty("dbPassword"));
            p.setProperty("username",  properties.getProperty("dbUser"));
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
            dataSource= (BasicDataSource)BasicDataSourceFactory.createDataSource(p);

        }catch (Exception e){
            Log.error(e);
        }
    }



    public static void close(Connection connection){
        try {
            if(connection!=null){
                connection.close();
            }
        }catch (SQLException e){
            Log.error(e);
        }
    }


    public static synchronized Connection getConnection(){
        if (dataSource==null){
            init();
        }
        Connection connection=null;
        if (dataSource!=null){
            try {
                connection=dataSource.getConnection();
                if (connection.isClosed()|| connection==null){
                    dataSource=null;
                    try {
                        Thread.sleep(5000);
                    }catch (InterruptedException e){
                        Log.error(e);
                    }
                    getConnection();
                }


            }catch (SQLException e){
                Log.error(e);
                dataSource=null;
                return null;
            }
        }
        return connection;
    }


    public  static Connection  getConnectForReal()  {
        Connection conn=null;
        try{
            Properties properties= new Properties();
            String path=System.getProperty("user.dir")+"/db.properties";
            InputStream is= new FileInputStream(path);
            properties.load(is);
            System.out.println("###########DBIP:"+properties.getProperty("dbIp"));
            conn = DriverManager.getConnection("jdbc:mysql://"+properties.getProperty("dbIp")+":"+properties.getProperty("dbPort")+"/"+properties.getProperty("dbName")+"?autoReconnect=true&rewriteBatchedStatements=true&useUnicode=true&characterEncoding=utf8&useSSL=false", properties.getProperty("dbUser"),  properties.getProperty("dbPassword"));
            is.close();
        }

        catch(Exception e) {
            Log.error(e);
            //e.printStackTrace();
        }
        return   conn;
    }









}