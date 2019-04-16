package com.xiaohulu.dao;

import com.xiaohulu.bean.PriceAndMsgInfoBean;
import com.xiaohulu.bean.PriceAndMsgInfoDeviceBean;
import com.xiaohulu.bean.TyrantsNumAndGiftPriceBean;
import com.xiaohulu.bean.XuehuMonthStatisticsBean;
import com.xiaohulu.log.Log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author xj
 */



public class LiveShowXuehuSqlDao extends SqldbBase {
	 private  Connection con;

    public LiveShowXuehuSqlDao(Connection con) {
		super();
		this.con = con;
	}





public boolean insertXuehusourcegname_statistics_info(List<PriceAndMsgInfoBean> lists,String tablename)  {
	Log.info("insertXuehusourcegname_statistics_info begin");
    PreparedStatement pstmt = null;
    boolean result =false;
    try {
        //xuehu_sourcegname_statistics_info
        String sql =  "insert into " +  tablename + "( " +
                "platform_id,sourcegname,room_live_num,all_msg_num,msg_sender_num," +
                "gift_sender_num,all_gift_value,rownum," +
                "room_live_num_twelve,room_live_num_twenty,room_live_num_twenty_four,statistics_date,update_time,msg_people_avg,gift_people_avg,gift_sender_num_all,gift_people_avg_all) " +
                "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,now(),?,?,?,?)";

        pstmt = con.prepareStatement(sql);
         for(PriceAndMsgInfoBean itemnn :lists){
            pstmt.setInt(1, itemnn.getPlatform_id());
            pstmt.setString(2,itemnn.getSourcegname());
            pstmt.setLong(3,itemnn.getRoom_live_num());
            pstmt.setLong(4,itemnn.getAll_msg_num());
            pstmt.setLong(5,itemnn.getMsg_sender_num());
            pstmt.setLong(6,itemnn.getGift_sender_num());
            pstmt.setDouble(7,itemnn.getAll_gift_value());
            pstmt.setLong(8,itemnn.getRownum());
            //System.out.println("zhegezhi:" + itemnn.getRoom_live_num_twelve());
            pstmt.setLong(9,itemnn.getRoom_live_num_twelve());
            pstmt.setLong(10,itemnn.getRoom_live_num_twenty());
            pstmt.setLong(11,itemnn.getRoom_live_num_twenty_four());
            pstmt.setString(12,itemnn.getStatistics_date());
            pstmt.setLong(13,itemnn.getMsg_people_avg());
            pstmt.setLong(14,itemnn.getGift_people_avg());
            pstmt.setLong(15,itemnn.getGift_sender_num_all());
            pstmt.setLong(16,itemnn.getGift_people_avg_all());
            pstmt.addBatch();
        }
        pstmt.executeBatch();
        result=true;
    }
    catch(Exception e){
           e.printStackTrace();
      }
    finally {
        close(pstmt);
    }
    Log.info("insertXuehusourcegname_statistics_info end");
    return result;
}

    public boolean insertXuehusourcegname_statistics_info_Newtable(List<PriceAndMsgInfoBean> lists)  {
        Log.info("insertXuehusourcegname_statistics_info begin");
        PreparedStatement pstmt = null;
        boolean result =false;
        try {
            String sql =  "insert into xuehu_sourcegname_statistics_info_newtable( " +
                    "platform_id,sourcegname,room_live_num,all_msg_num,msg_sender_num," +
                    "gift_sender_num,all_gift_value,rownum," +
                    "room_live_num_twelve,room_live_num_twenty,room_live_num_twenty_four,statistics_date,update_time,msg_people_avg,gift_people_avg,gift_sender_num_all,gift_people_avg_all) " +
                    "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,now(),?,?,?,?)";

            pstmt = con.prepareStatement(sql);
            for(PriceAndMsgInfoBean itemnn :lists){
                pstmt.setInt(1, itemnn.getPlatform_id());
                pstmt.setString(2,itemnn.getSourcegname());
                pstmt.setLong(3,itemnn.getRoom_live_num());
                pstmt.setLong(4,itemnn.getAll_msg_num());
                pstmt.setLong(5,itemnn.getMsg_sender_num());
                pstmt.setLong(6,itemnn.getGift_sender_num());
                pstmt.setDouble(7,itemnn.getAll_gift_value());
                pstmt.setLong(8,itemnn.getRownum());
                //System.out.println("zhegezhi:" + itemnn.getRoom_live_num_twelve());
                pstmt.setLong(9,itemnn.getRoom_live_num_twelve());
                pstmt.setLong(10,itemnn.getRoom_live_num_twenty());
                pstmt.setLong(11,itemnn.getRoom_live_num_twenty_four());
                pstmt.setString(12,itemnn.getStatistics_date());
                pstmt.setLong(13,itemnn.getMsg_people_avg());
                pstmt.setLong(14,itemnn.getGift_people_avg());
                pstmt.setLong(15,itemnn.getGift_sender_num_all());
                pstmt.setLong(16,itemnn.getGift_people_avg_all());
                pstmt.addBatch();
            }
            pstmt.executeBatch();
            result=true;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally {
            close(pstmt);
        }
        Log.info("insertXuehusourcegname_statistics_info end");
        return result;
    }

    public boolean insertXuehu_month_statistics(List<XuehuMonthStatisticsBean> lists)  {
        Log.info("insertXuehu_month_statistics begin");
        PreparedStatement pstmt = null;
        boolean result =false;
        try {
            String sql =  "insert into xuehu_month_statistics( " +
                    "platform_id,room_id,source_link,emcee,sourcegname," +
                    "statistics_date,update_time,message_count,gift_sum_price,dayCount," +
                    "max_follow_number, rownum,message_sender_count_dayavg,gift_sender_count_dayav,airtime) " +
                    "VALUES(?,?,?,?,?, ?, now(),?,?,?,  ?,?,?,?,?)";


            pstmt = con.prepareStatement(sql);

            for(XuehuMonthStatisticsBean itemnn :lists){
                pstmt.setInt(1, itemnn.getPlatform_id());
                pstmt.setString(2,itemnn.getRoom_id());
                pstmt.setString(3,itemnn.getSource_link());
                pstmt.setString(4,itemnn.getEmcee());
                pstmt.setString(5,itemnn.getSourcegname());
                pstmt.setString(6,itemnn.getStatistics_date());
                pstmt.setLong(7,itemnn.getMessage_count());
                pstmt.setDouble(8,itemnn.getGift_sum_price());

                pstmt.setLong(9,itemnn.getDayCount());
                pstmt.setLong(10,itemnn.getMax_follow_number());
                pstmt.setLong(11,itemnn.getRownum());
                pstmt.setLong(12,itemnn.getMessage_sender_count_dayavg());
                pstmt.setLong(13,itemnn.getGift_sender_count_dayav());
                pstmt.setInt(14, itemnn.getAirtime());

                pstmt.addBatch();
            }
            pstmt.executeBatch();
            result=true;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally {
            close(pstmt);
        }
        //System.out.println("charujilushu: " + lists.size());
        Log.info("insertXuehu_month_statistics end");
        return result;
    }

    public boolean insertXuehuTyransStatistics(List<TyrantsNumAndGiftPriceBean> lists)  {
        Log.info("insertXuehuTyransStatistics begin");
        PreparedStatement pstmt = null;
        boolean result =false;
        try {
            String sql =  "insert into xuehu_tyrans_statistics( " +
                    "platform_id,statistics_date,update_time,tyrants_num,all_gift_value,level,memo) " +

                    " VALUES(?,?,now(),?,?,?,? )";

            pstmt = con.prepareStatement(sql);
            for(TyrantsNumAndGiftPriceBean itemnn :lists){
                pstmt.setInt(1, itemnn.getPlatform_id());
                pstmt.setString(2,itemnn.getStatistics_date());
                pstmt.setLong(3,itemnn.getTyrants_num());
                pstmt.setDouble(4,itemnn.getAll_gift_value());
                pstmt.setLong(5,itemnn.getLevel());
                pstmt.setString(6,itemnn.getMemo());

                pstmt.addBatch();
            }
            pstmt.executeBatch();
            result=true;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally {
            close(pstmt);
        }
        Log.info("insertXuehuTyransStatistics end");
        return result;
    }
    public Map<String,Integer> getSourcegnameConfigType(Integer platID) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Map<String,Integer> resultList = new HashMap<String, Integer>();

        try {
            String sql = "SELECT sourcegname,type from sourcegname_config where  platform_id= ? ";
            System.out.println("getSourcegnameConfigType  sql: "  +  sql);
            ps = con.prepareStatement(sql);
            ps.setInt(1,platID);
            rs = ps.executeQuery();
            int tempday=0;
            int row=1;
            String sourgegname;

            while (rs.next()) {
                sourgegname = rs.getString(1);
                if(sourgegname!=null){
                    sourgegname = sourgegname.trim();
                    if(sourgegname.length()>0){
                        resultList.put(sourgegname,rs.getInt(2));
                    }
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
    public Map<String,String> getSourcegnameBigType(Integer platID) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Map<String,String> resultList = new HashMap<String, String>();

        try {
            String sql = "SELECT sourcegname,sourcegname_sub from sourcegname_sub where  platform_id= ? ";
            System.out.println("getSourcegnameBigType  sql: "  +  sql);
            ps = con.prepareStatement(sql);
            ps.setInt(1,platID);
            rs = ps.executeQuery();
            String subsourge;
            String sourge;
            while (rs.next()) {
                subsourge = rs.getString(2);
                sourge = rs.getString(1);
                if(subsourge!=null && sourge !=null){
                    subsourge = subsourge.trim();
                    sourge = sourge.trim();
                    if(subsourge.length()>0 && sourge.length()>0){
                        resultList.put(subsourge,sourge);
                    }
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

    public boolean insertXuehu_msgandgift_sender_people_statistics(int platid,String month,int msgsendsernum,int giftsendsernum,int giftsendsernumnofiltertype )  {
        Log.info("insertXuehu_msgandgift_sender_people_statistics begin");
        PreparedStatement pstmt = null;
        boolean result =false;
        try {
            String sql =  "insert into xuehu_msgandgift_sender_people_statistics( " +
                    "platform_id,`statistics_date`,`update_time`,msgsendercount,giftsendercount,giftsendercountalltype) " +
                    "VALUES(?,?,now(),?,?,?)";


            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1,platid);
            pstmt.setString(2,month);
            pstmt.setInt(3,msgsendsernum);
            pstmt.setInt(4,giftsendsernum);
            pstmt.setInt(5,giftsendsernumnofiltertype);
            pstmt.execute();
            result=true;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally {
            close(pstmt);
        }

        Log.info("insertXuehu_msgandgift_sender_people_statistics end");
        return result;
    }
    public boolean insertXuehu_combatteam_msgtimes(String day,Map<String,Integer> mapv,String platAndRoom)  {
        Log.info("insertXuehu_combatteam_msgtimes begin");
        
        PreparedStatement pstmt = null;
        boolean result =false;
        try {
            String sql =  "insert into xuehu_combatteam_msgtimes( " +
                    "`statistics_date`,`update_time`,combatteam,times,platid_roomid) " +
                    "VALUES(?,now(),?,?,?)";


            pstmt = con.prepareStatement(sql);
            for(String itemnn :mapv.keySet()){
                pstmt.setString(1, day);
                pstmt.setString(2,itemnn);
                pstmt.setInt(3,mapv.get(itemnn));
                pstmt.setString(4,platAndRoom==null ? "" : platAndRoom);
                pstmt.addBatch();
            }
            pstmt.executeBatch();
            result=true;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally {
            close(pstmt);
        }

        Log.info("insertXuehu_combatteam_msgtimes end");
        return result;
    }
    public boolean insertXuehusourcegname_statistics_info_Device(List<PriceAndMsgInfoDeviceBean> lists)  {
        Log.info("insertXuehusourcegname_statistics_info_Device begin");
        //System.out.println("记录数：" + lists.size());
        PreparedStatement pstmt = null;
        boolean result =false;
        try {
            String sql =  "insert into xuehu_sourcegname_statistics_info_with_driver( " +
                    "platform_id,sourcegname,pc_all_msg_num,pc_msg_sender_num,pc_gift_sender_num,pc_all_gift_value,rownum," +
                    "statistics_date,update_time,pc_msg_people_avg,pc_gift_people_avg ," +
                    "mobile_all_msg_num,mobile_msg_sender_num,mobile_gift_sender_num,mobile_all_gift_value,mobile_msg_people_avg,mobile_gift_people_avg,all_gift_value) " +
                    " VALUES(?,?,?,?,?,?,?,?,now(),?,?,?,?,?,?,?,?,?)";

            pstmt = con.prepareStatement(sql);
            for(PriceAndMsgInfoDeviceBean itemnn :lists){
                pstmt.setInt(1, itemnn.getPlatform_id());
                pstmt.setString(2,itemnn.getSourcegname());
                pstmt.setLong(3,itemnn.getPc_all_msg_num());
                pstmt.setLong(4,itemnn.getPc_msg_sender_num());
                pstmt.setLong(5,itemnn.getPc_gift_sender_num());
                pstmt.setDouble(6,itemnn.getPc_all_gift_value());
                pstmt.setDouble(7,itemnn.getRownum());
                pstmt.setString(8,itemnn.getStatistics_date());
                pstmt.setLong(9,itemnn.getPc_msg_people_avg());
                pstmt.setLong(10,itemnn.getPc_gift_people_avg());


                pstmt.setLong(11,itemnn.getMobile_all_msg_num());
                pstmt.setLong(12,itemnn.getMobile_msg_sender_num());
                pstmt.setLong(13,itemnn.getMobile_gift_sender_num());
                pstmt.setDouble(14,itemnn.getMobile_all_gift_value());
                pstmt.setLong(15,itemnn.getMobile_msg_people_avg());
                pstmt.setLong(16,itemnn.getMobile_gift_people_avg());
                pstmt.setDouble(17,itemnn.getAll_gift_value());
                pstmt.addBatch();
            }
            pstmt.executeBatch();
            result=true;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally {
            close(pstmt);
        }
        Log.info("insertXuehusourcegname_statistics_info end");
        return result;
    }


}
