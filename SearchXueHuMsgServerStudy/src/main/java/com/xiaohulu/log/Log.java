package com.xiaohulu.log;

import org.apache.log4j.Logger;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: sunxianpeng
 * \* Date: 2019/4/15
 * \* Time: 21:40
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class Log {
    public static Logger logger= Logger.getLogger(Log.class);

    public static void error( String string) {
        // TODO Auto-generated method stub
        logger.error(string);
    }

    public static void error( Exception e) {
        // TODO Auto-generated method stub
        logger.error(e);
        e.printStackTrace();
    }

    public static void info( String string) {
        // TODO Auto-generated method stub
        logger.info(string);
    }


}