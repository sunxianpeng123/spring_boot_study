package com.xiaohulu.dao;

import com.xiaohulu.bean.PriceAndMsgDeviceBean;
import com.xiaohulu.bean.PriceAndMsgTotalBean;
import com.xiaohulu.bean.XuehuMonthStatisticsBean;
import com.xiaohulu.log.Log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * @author xj
 */



public class LiveShowSqlDao  extends SqldbBase {
	 private  Connection con;

    public LiveShowSqlDao(Connection con) {
		super();
		this.con = con;
	}

    public  List <PriceAndMsgTotalBean> getByPlatAndDate(Integer platID,String beginDate,String endDate ) {
        //System.out.println("getByPlatAndDate beginDate " + beginDate + "_endDate_" +endDate);
        PreparedStatement ps = null;
        ResultSet rs = null;
        List <PriceAndMsgTotalBean> resultList= new ArrayList<PriceAndMsgTotalBean>();
        try {


            String sql = "SELECT sourcegname, sum(all_msg_num),sum(msg_sender_num),sum(all_gift_value),sum(gift_sender_num),sum(gift_sender_num_all) " +
                    " from  plat_info.sourcegname_statistics_info where platform_id = ? " +
                    " and statistics_date >= ? and statistics_date <= ? GROUP  by sourcegname  order by sum(all_gift_value) desc ";



            System.out.println("getByPlatAndDate  sql: "  +  sql);
            ps = con.prepareStatement(sql);
            ps.setInt(1,platID);
            ps.setString(2,beginDate);
            ps.setString(3,endDate);

            rs = ps.executeQuery();
            PriceAndMsgTotalBean  xb;
            while (rs.next()) {
                    xb= new PriceAndMsgTotalBean();
                    xb.setSourcegname(rs.getString(1));
                    xb.setAll_msg_num(rs.getLong(2));
                    xb.setMsg_sender_num(rs.getLong(3));
                    xb.setAll_gift_value(rs.getDouble(4));
                    xb.setGift_sender_num(rs.getLong(5));
                    xb.setGift_sender_num_all(rs.getLong(6));
                    resultList.add(xb);
            }
        }
        catch(SQLException e) {
            Log.error(e);
        }
        finally {
            close(rs, ps);
        }
        return resultList;
    }
    public  List <PriceAndMsgTotalBean> getByPlatAndDateOnlySources(Integer platID,String beginDate,String endDate ,Map<String,Integer> onlymaps) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List <PriceAndMsgTotalBean> resultList= new ArrayList<PriceAndMsgTotalBean>();
        try {
            String sql = "SELECT sourcegname, sum(all_msg_num),sum(msg_sender_num),sum(all_gift_value),sum(gift_sender_num),sum(gift_sender_num_all) " +
                    " from  plat_info.sourcegname_statistics_info where platform_id = ? " +
                    " and statistics_date >= ? and statistics_date <= ? GROUP  by sourcegname  order by sum(all_gift_value) desc ";

System.out.println("mysql par: _" + platID + "_" + beginDate + "_" + endDate);

            System.out.println("getByPlatAndDate  sql: "  +  sql);
            ps = con.prepareStatement(sql);
            ps.setInt(1,platID);
            ps.setString(2,beginDate);
            ps.setString(3,endDate);

            rs = ps.executeQuery();
            PriceAndMsgTotalBean  xb;
           //System.out.println("onlymaps:" + onlymaps.toString());
           String itemSourcegname ;
            while (rs.next()) {
                itemSourcegname = rs.getString(1).trim();
                if(onlymaps.containsKey(itemSourcegname)){
                    xb= new PriceAndMsgTotalBean();
                    xb.setSourcegname(itemSourcegname);
                    xb.setAll_msg_num(rs.getLong(2));
                    xb.setMsg_sender_num(rs.getLong(3));
                    xb.setAll_gift_value(rs.getDouble(4));
                    xb.setGift_sender_num(rs.getLong(5));
                    xb.setGift_sender_num_all(rs.getLong(6));
                    resultList.add(xb);
                }

            }
        }
        catch(SQLException e) {
            Log.error(e);
        }
        finally {
            close(rs, ps);
        }
        return resultList;
    }
    public  PriceAndMsgTotalBean getByPlatAndDateAllValOnlySources(Integer platID,String beginDate,String endDate ,Map<String,Integer> onlymaps) {
        //System.out.println("getByPlatAndDate beginDate " + beginDate + "_endDate_" +endDate);
        PreparedStatement ps = null;
        ResultSet rs = null;
        PriceAndMsgTotalBean xb= new PriceAndMsgTotalBean();
        try {


            String sql = "SELECT sourcegname, sum(all_msg_num),sum(msg_sender_num),sum(all_gift_value),sum(gift_sender_num) ,sum(gift_sender_num_all) " +
                    " from  plat_info.sourcegname_statistics_info where platform_id = ? " +
                    " and statistics_date >= ? and statistics_date <= ? GROUP  by sourcegname  order by sum(all_gift_value) desc ";



            System.out.println("getByPlatAndDate  sql: "  +  sql);
            ps = con.prepareStatement(sql);
            ps.setInt(1,platID);
            ps.setString(2,beginDate);
            ps.setString(3,endDate);
            rs = ps.executeQuery();
            long all_msg_num =0;
            long msg_sender_num=0;
            double all_gift_value = 0;
            long gift_sender_num = 0;
            long gift_sender_num_all=0;
            String itemsourcegname;
            while (rs.next()) {
                itemsourcegname = rs.getString(1).trim();
                if(onlymaps.containsKey(itemsourcegname)){
                    all_msg_num +=  rs.getLong(2);
                    msg_sender_num +=  rs.getLong(3);
                    all_gift_value +=  rs.getDouble(4);
                    gift_sender_num += rs.getLong(5);
                    gift_sender_num_all += rs.getLong(6);
                }
            }
            xb.setSourcegname("全部");
            xb.setAll_msg_num(   all_msg_num );
            xb.setMsg_sender_num(msg_sender_num);
            xb.setAll_gift_value(all_gift_value);
            xb.setGift_sender_num(gift_sender_num);
            xb.setGift_sender_num_all(gift_sender_num_all);
        }
        catch(SQLException e) {
            Log.error(e);
        }
        finally {
            close(rs, ps);
        }
        return xb;
    }


    public PriceAndMsgTotalBean  getByPlatAndDateAll(Integer platID,String beginDate,String endDate ) {
        //System.out.println("getByPlatAndDate beginDate " + beginDate + "_endDate_" +endDate);
        PreparedStatement ps = null;
        ResultSet rs = null;
        PriceAndMsgTotalBean  xb = null ;
        try {


            String sql = "SELECT sourcegname, sum(all_msg_num),sum(msg_sender_num),sum(all_gift_value),sum(gift_sender_num) ,sum(gift_sender_num_all) " +
                    " from  plat_info.sourcegname_statistics_info where platform_id = ? " +
                    " and statistics_date >= ? and statistics_date <= ?  ";



            System.out.println("getByPlatAndDateAll  sql: "  +  sql);
            ps = con.prepareStatement(sql);
            ps.setInt(1,platID);
            ps.setString(2,beginDate);
            ps.setString(3,endDate);

            rs = ps.executeQuery();

            while (rs.next()) {
                xb= new PriceAndMsgTotalBean();
                xb.setSourcegname("全部");
                xb.setAll_msg_num(rs.getLong(2));
                xb.setMsg_sender_num(rs.getLong(3));
                xb.setAll_gift_value(rs.getDouble(4));
                xb.setGift_sender_num(rs.getLong(5));
                xb.setGift_sender_num_all(rs.getLong(6));

            }
        }
        catch(SQLException e) {
            Log.error(e);
        }
        finally {
            close(rs, ps);
        }
        return xb;
    }



    public  List <XuehuMonthStatisticsBean> getMonthStatisticsByPlatAndDate(Integer platID,String day ,int limitnum) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List <XuehuMonthStatisticsBean> resultList= new ArrayList<XuehuMonthStatisticsBean>();

        try {


            String sql = "SELECT platform_id,room_id,source_link,sourcegname,emcee,gift_sum_price,dayCount, max_follow_number, message_sender_count,gift_sender_count, message_count,ifnull(airtime,0) from  live_platform.xiaohulu_growth_calendar_month_statistics where platform_id = ? " +
                    " and statistics_date = ?   order by  gift_sum_price desc limit  " + limitnum;


            System.out.println("getAnchorBusinessYearStatisticsInfoByAnchorAndDate  sql: "  +  sql);
            System.out.println("getAnchorBusinessYearStatisticsInfoByAnchorAndDate par: platID " + platID + " day " + day +  " limitnum " +  limitnum );
            ps = con.prepareStatement(sql);
            ps.setInt(1,platID);
            ps.setString(2,day);


            rs = ps.executeQuery();
            int tempday=0;
            int row=1;
            XuehuMonthStatisticsBean xb;
            while (rs.next()) {
                xb= new XuehuMonthStatisticsBean();
                xb.setPlatform_id(rs.getInt(1));
                xb.setRoom_id(rs.getString(2));
                xb.setSource_link(rs.getString(3));
                xb.setSourcegname(rs.getString(4));
                xb.setEmcee(rs.getString(5));
                xb.setGift_sum_price(rs.getDouble(6));
                tempday =rs.getInt(7);
                xb.setDayCount(tempday);
                xb.setMax_follow_number(rs.getInt(8));
                xb.setMessage_sender_count(rs.getInt(9));
                xb.setGift_sender_count(rs.getInt(10));
                xb.setMessage_sender_count_dayavg(tempday==0 ? 0 :  rs.getInt(9)/tempday);
                xb.setGift_sender_count_dayav(tempday==0 ? 0 :rs.getInt(10) /tempday);
                xb.setStatistics_date(day.substring(0,7));
                xb.setMessage_count(rs.getInt(11));
                xb.setAirtime(rs.getInt(12));
                xb.setRownum(row);
                row ++;

                resultList.add(xb);
            }
        }
        catch(SQLException e) {
            Log.error(e);
        }
        finally {
            close(rs, ps);
        }
        return resultList;
    }
    public  List <PriceAndMsgDeviceBean> getByPlatAndDateOnlySourcesByDevice(Integer platID,String beginDate,String endDate ,Map<String,Integer> onlymaps) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List <PriceAndMsgDeviceBean> resultList= new ArrayList<PriceAndMsgDeviceBean>();
        try {
            String sql = "SELECT sourcegname, sum(pc_all_msg_num),sum(pc_msg_sender_num),sum(pc_all_gift_val),sum(pc_gift_sender_num),sum(mobile_all_msg_num),sum(mobile_msg_sender_num),sum(mobile_all_gift_val),sum(mobile_gift_sender_num)  " +
                    " from  plat_info.sourcegname_statistics_info where platform_id = ? " +
                    " and statistics_date >= ? and statistics_date <= ? GROUP  by sourcegname  ";

            System.out.println("mysql par: _" + platID + "_" + beginDate + "_" + endDate);

            System.out.println("getByPlatAndDateOnlySourcesByDevice  sql: "  +  sql);
            ps = con.prepareStatement(sql);
            ps.setInt(1,platID);
            ps.setString(2,beginDate);
            ps.setString(3,endDate);

            rs = ps.executeQuery();
            PriceAndMsgDeviceBean xb;
            //System.out.println("onlymaps:" + onlymaps.toString());
            String itemSourcegname ;
            while (rs.next()) {
                itemSourcegname = rs.getString(1).trim();
                if(onlymaps.containsKey(itemSourcegname)){
                    xb= new PriceAndMsgDeviceBean();
                    xb.setSourcegname(itemSourcegname);
                    xb.setPc_all_msg_num(rs.getLong(2));
                    xb.setPc_msg_sender_num(rs.getLong(3));
                    xb.setPc_all_gift_value(rs.getDouble(4));
                    xb.setPc_gift_sender_num(rs.getLong(5));
                    xb.setMobile_all_msg_num(rs.getLong(6));
                    xb.setMobile_msg_sender_num(rs.getLong(7));
                    xb.setMobile_all_gift_value(rs.getDouble(8));
                    xb.setMobile_gift_sender_num(rs.getLong(9));
                    resultList.add(xb);
                }

            }
        }
        catch(SQLException e) {
            Log.error(e);
        }
        finally {
            close(rs, ps);
        }
        return resultList;
    }


    public  PriceAndMsgDeviceBean getByPlatAndDateAllValOnlySourcesDevice(Integer platID,String beginDate,String endDate ,Map<String,Integer> onlymaps) {
        //System.out.println("getByPlatAndDate beginDate " + beginDate + "_endDate_" +endDate);
        PreparedStatement ps = null;
        ResultSet rs = null;
        PriceAndMsgDeviceBean xb= new PriceAndMsgDeviceBean();
        try {


            String sql = "SELECT sourcegname,sum(pc_all_msg_num),sum(pc_msg_sender_num),sum(pc_all_gift_val),sum(pc_gift_sender_num),sum(mobile_all_msg_num),sum(mobile_msg_sender_num),sum(mobile_all_gift_val),sum(mobile_gift_sender_num)  " +
                    " from  plat_info.sourcegname_statistics_info where platform_id = ? " +
                    " and statistics_date >= ? and statistics_date <= ? GROUP  by sourcegname   ";



            //System.out.println("getByPlatAndDateAllValOnlySourcesDevice  sql: "  +  sql);
            ps = con.prepareStatement(sql);
            ps.setInt(1,platID);
            ps.setString(2,beginDate);
            ps.setString(3,endDate);
            rs = ps.executeQuery();
            long all_msg_num =0;
            long msg_sender_num=0;
            double all_gift_value = 0;
            long gift_sender_num = 0;

            long all_msg_num_m =0;
            long msg_sender_num_m=0;
            double all_gift_value_m = 0;
            long gift_sender_num_m = 0;

            String itemsourcegname;
            while (rs.next()) {
                itemsourcegname = rs.getString(1).trim();
                if(onlymaps.containsKey(itemsourcegname)){
                    all_msg_num +=  rs.getLong(2);
                    msg_sender_num +=  rs.getLong(3);
                    all_gift_value +=  rs.getDouble(4);
                    gift_sender_num += rs.getLong(5);

                    all_msg_num_m +=  rs.getLong(6);
                    msg_sender_num_m +=  rs.getLong(7);
                    all_gift_value_m +=  rs.getDouble(8);
                    gift_sender_num_m += rs.getLong(9);
                }
            }
            xb.setSourcegname("全部");
            xb.setPc_all_msg_num(   all_msg_num );
            xb.setPc_msg_sender_num(msg_sender_num);
            xb.setPc_all_gift_value(all_gift_value);
            xb.setPc_gift_sender_num(gift_sender_num);

            xb.setMobile_all_msg_num(   all_msg_num_m );
            xb.setMobile_msg_sender_num(msg_sender_num_m);
            xb.setMobile_all_gift_value(all_gift_value_m);
            xb.setMobile_gift_sender_num(gift_sender_num_m);
        }
        catch(SQLException e) {
            Log.error(e);
        }
        finally {
            close(rs, ps);
        }
        return xb;
    }


}
