package com.xiaohulu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: sunxianpeng
 * \* Date: 2019/4/16
 * \* Time: 14:37
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public abstract class SqldbBase {
    /**
     * Release Database resource. Example: close(con);
     * @param obj
     */
    public static  void close(Object obj){

    }

    /**
     * Release Database resource. Example: close(ps, rs);
     * @param obj1
     * @param obj2
     */
    public static void close(Object obj1,Object obj2){

    }

    /**
     * Release resource
     * @param obj
     */
    public static void closeObject(Object obj){
        if(obj == null)
            return;
        try {
            if(obj instanceof Connection)
                ((Connection)obj).close();
            if(obj instanceof PreparedStatement)
                ((PreparedStatement)obj).close();
            if(obj instanceof Statement)
                ((Statement)obj).close();
            if(obj instanceof ResultSet)
                ((ResultSet)obj).close();
        }
        catch(Exception e) {}
        obj = null;

    }
}