package com.xiaohulu.dao;

import com.xiaohulu.log.Log;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: sunxianpeng
 * \* Date: 2019/4/16
 * \* Time: 15:11
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class PrestoConnectionSource {
    public static void  close(Connection conn ){

        try {
            if(conn!=null)
                conn.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }



    }


    public  static Connection  getConnectForReal()  {
        Connection conn=null;
        try{
            Properties properties= new Properties();
            String path=System.getProperty("user.dir")+"/db.properties";
            InputStream is= new FileInputStream(path);
            properties.load(is);
            String prestoIp= properties.getProperty("prestoIp");
            String prestoPort=properties.getProperty("prestoPort");
            String prestoUser=properties.getProperty("prestoUser");
            String prestoPassword=properties.getProperty("prestoPassword");
            String prestoDB=properties.getProperty("prestoDB");
            System.out.println("###########prestoIp:"+prestoIp);
            Class.forName("com.facebook.presto.jdbc.PrestoDriver");
            //conn = DriverManager.getConnection("jdbc:presto://113.107.166.14:28080/hive","root","");

            conn= DriverManager.getConnection("jdbc:presto://"+prestoIp+":"+prestoPort+"/"+prestoDB,prestoUser,prestoPassword);  ;
            is.close();
        }

        catch(Exception e) {
            Log.error(e);
            //e.printStackTrace();
        }
        return   conn;
    }
}