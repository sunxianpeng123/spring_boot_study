package com.xiaohulu.dao;

import com.xiaohulu.bean.TyrantsNumAndGiftPriceBean;
import com.xiaohulu.bean.XuehuMonthStatisticsBean;
import com.xiaohulu.log.Log;
import com.xiaohulu.tools.Helper;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;
import java.util.*;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: sunxianpeng
 * \* Date: 2019/4/16
 * \* Time: 15:08
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class PrestoSqlDao extends  SqldbBase {
        private  Connection conn ;
        public PrestoSqlDao (Connection conn){
            super();
            this.conn =conn;
        }

    /**
     * 获取Sourcegname及其对应的主播房间数,
     * 根据平台、开始、结束时间、小时来选择
     * @param platID
     * @param beginDay
     * @param endDay
     * @param Hour
     * @return
     */
    public Map<String,Long> getLiveRoomsByPlatAndtime(Integer platID, String beginDay, String endDay, String Hour) {
        Statement ps = null;
        ResultSet rs = null;
        Map <String ,Long> resultList= new HashMap<String, Long>();
        try {
            String sql =" SELECT sourcegname, count(distinct room_id) FROM hive.parquet.live_show_online " +
                    "WHERE plat =" + platID  + " and  date>= '" + beginDay + "' and date <='" + endDay + "'  " ;
            if(Hour != null){
                sql += " and substring(datetime,12,2) = '" + Hour  + "'";
            }
            sql += "   group by sourcegname";
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            System.out.println(sql);

            while (rs.next()) {
                resultList.put(rs.getString(1),rs.getLong(2));
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

    /**
     * 获取Sourcegname及其对应的主播房间数,
     * 根据平台、开始、结束时间、小时选择，并使得mapSource包含选出的sourcegname
     * @param platID
     * @param beginDay
     * @param endDay
     * @param Hour
     * @param mapSource
     * @return
     */
    public  Map <String,Long> getLiveRoomsByPlatAndtimeWithMapSourgname(Integer platID,String beginDay,String endDay,String Hour,Map<String,Integer> mapSource) {
        Statement ps = null;
        ResultSet rs = null;
        Map <String ,Long> resultList= new HashMap<String, Long>();
        try {
            String sql =" SELECT sourcegname, count(distinct room_id) FROM hive.parquet.live_show_online " +
                    "WHERE plat =" + platID  + " and  date>= '" + beginDay + "' and date <='" + endDay + "'" ;
            if(Hour != null){
                sql += " and substring(datetime,12,2) = '" + Hour  + "'";
            }
            sql += "group by sourcegname";
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            System.out.println(sql);
            String itemSourcegname;
            while (rs.next()) {
                itemSourcegname = rs.getString(1).trim();
                if(mapSource.containsKey(itemSourcegname)){
                    resultList.put(itemSourcegname,rs.getLong(2));
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

    /**
     *
     * @param platID
     * @param sourcegnameString
     * @param beginDay
     * @param endDay
     * @param beginSubTime
     * @param endSubTime
     * @return
     */
    public  Map <String,Long> getLiveRoomsByPlatAndtimeForRangTiem(Integer platID,String sourcegnameString , String beginDay,String endDay,String beginSubTime,String endSubTime) {
        Statement ps = null;
        ResultSet rs = null;
        Map <String ,Long> resultList= new HashMap<String, Long>();

        try {
            String sql =" SELECT  datetime,count(distinct room_id) FROM hive.parquet.live_show_online " +
                    " plat = " + platID + " and  date>= '" + beginDay + "' and date <='" + endDay + "'  " ;
            if(sourcegnameString != null){
                sql += " and " +  sourcegnameString ;
            }
            if(beginSubTime != null){
                sql += " and substring(datetime,12,8) >= '" + beginSubTime  + "'";
            }
            if(endSubTime != null){
                sql += " and substring(datetime,12,8) <= '" + endSubTime  + "'";
            }
            sql += "   group by datetime";
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            System.out.println(sql);
            String daypre  ;
            long liveroom;
            while (rs.next()) {
                daypre = rs.getString(1).substring(0,10);
                liveroom = rs.getLong(2);
                if(resultList.containsKey(daypre)){
                    if(liveroom>resultList.get(daypre)){
                        resultList.put(daypre,liveroom);
                    }
                }else{
                    resultList.put(daypre,liveroom);
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
    public TyrantsNumAndGiftPriceBean getFormNumsByPlatAndPriceRange(Integer platID, String beginDay, String endDay, double minprice, double maxprice, String gift_type) {
        Statement ps = null;
        ResultSet rs = null;
        TyrantsNumAndGiftPriceBean obj = null;
        try {


            String gift_typeStr =" ";
            if(gift_type !=null && gift_type.length()>0){
                gift_typeStr += " and  gift_type='" + gift_type + "'";

            }

            String sql =" select count(*) , sum(p) from (SELECT from_id , sum((cast(price as DOUBLE)* count)) as p FROM hive.parquet.live_show_gift WHERE date>='" + beginDay  + "'  and date<='" + endDay + "' and  plat=" +  platID+   gift_typeStr + "    group by from_id) t  where   " ;
            if(minprice >0 ){
                sql += " p>= "  +  minprice ;
                if(maxprice>=0){
                    sql += "  and p< "  +  maxprice ;
                }
            }else{
                if(maxprice>=0){
                    sql += "   p>= "  +  maxprice ;
                }
            }

            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            System.out.println(sql);

            while (rs.next()) {
                obj = new TyrantsNumAndGiftPriceBean();
                obj.setTyrants_num(rs.getInt(1));
                obj.setAll_gift_value(rs.getDouble(2));

            }
        }
        catch(SQLException e) {
            Log.error(e);
        }
        finally {
            close(rs, ps);
        }
        return obj;
    }

    //
    public List<XuehuMonthStatisticsBean> getMonthDataByparquet(Integer platID, String beginDay, String endDay, int limit ) {
        Statement ps = null;
        ResultSet rs = null;
        XuehuMonthStatisticsBean xb = null;
        List<XuehuMonthStatisticsBean> resultList = new ArrayList<XuehuMonthStatisticsBean>();
        try {


            //String sql =" select count(*) , sum(p) from (SELECT from_id , sum((cast(price as DOUBLE)* count)) as p FROM hive.parquet.live_show_gift WHERE date>='" + beginDay  + "'  and date<='" + endDay + "' and  plat=" +  platID+   gift_typeStr + "    group by from_id) t  where   " ;
            String sql ="  select platform_id,room_id,source_link,emcee,sourcegname, case when sum_gift_price is null then 0 else sum_gift_price end  as gift_sum_price,dayCount, '', case when message_sender_count is null then 0 else message_sender_count end  as message_sender_count,   case when gift_sender_count is null then 0 else gift_sender_count end ,case when sum_message_count is null then 0 else sum_message_count end from (" +

                    " select * from (" +
                    " select platform_id ,room_id,max(source_link) as source_link,max(sourcegname) as sourcegname,max(emcee) as emcee,count(distinct date) as dayCount  from hive.parquet.live_show_online WHERE date>='" + beginDay + "' and date <='" + endDay + "'   and plat = " + platID + " group by platform_id,room_id ) as ll " +
                    " left join (select platform_id as gplatform_id,room_id as groom_id,sum( (cast(price as DOUBLE)* count)) as sum_gift_price,count(distinct from_name) as gift_sender_count from hive.parquet.live_show_gift WHERE date>='" + beginDay + "' and date <='" + endDay + "'   and plat = " + platID + " and gift_type='1' group by platform_id,room_id order by sum_gift_price desc) as gg " +
                    " on ll.platform_id=gg.gplatform_id and ll.room_id=gg.groom_id" +
                    " left join (select platform_id as mplatform_id,room_id as mroom_id,count(content) as sum_message_count,count(distinct from_name) as message_sender_count from hive.parquet.live_show_message WHERE date>='" + beginDay + "' and date <='" + endDay +  "'   and plat = " + platID + "  group by platform_id,room_id ) mm" +
                    " on ll.platform_id=mm.mplatform_id and ll.room_id=mm.mroom_id" +
                    " ) order by sum_gift_price desc  limit  " + limit ;
            System.out.println("getMonthDataByparquet sql : " + sql);
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            int tempday;
            int row=1;
            String month = beginDay.substring(0,4) + "-" + beginDay.substring(4,6);
            while (rs.next()) {
                xb= new XuehuMonthStatisticsBean();
                xb.setPlatform_id(rs.getInt(1));
                xb.setRoom_id(rs.getString(2));
                xb.setSource_link(Helper.filterEmoji(rs.getString(3)));
                xb.setSourcegname(Helper.filterEmoji(rs.getString(5)));
                xb.setEmcee(Helper.filterEmoji(rs.getString(4)));
                xb.setGift_sum_price(rs.getDouble(6));
                tempday =rs.getInt(7);
                xb.setDayCount(tempday);
                xb.setMax_follow_number(0);
                xb.setMessage_sender_count(rs.getInt(9));
                xb.setGift_sender_count(rs.getInt(10));
                xb.setMessage_sender_count_dayavg(tempday==0 ? 0 :  rs.getInt(9)/tempday);
                xb.setGift_sender_count_dayav(tempday==0 ? 0 :rs.getInt(10) /tempday);
                xb.setStatistics_date(month);
                xb.setMessage_count(rs.getInt(11));
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

    public  Map<String,Map <String,Long>> getLiveRoomsByPlatAndtimeForRangTiemGroupSourceGname(Integer platID,String sourcegnameString , String beginDay,String endDay,String beginSubTime,String endSubTime,Map<String,Integer> filterMap) {
        Statement ps = null;
        ResultSet rs = null;
        Map <String ,Long> resultList= new HashMap<String, Long>();
        Map<String,Map <String ,Long> > sourcegnameMap = new HashMap<String, Map<String, Long>>();

        try {



            String sql =" SELECT  datetime,count(distinct room_id),sourcegname FROM hive.parquet.live_show_online WHERE plat = " + platID + " and  date>= '" + beginDay + "' and date <='" + endDay + "'  " ;
            if(sourcegnameString != null){
                sql += " and " +  sourcegnameString ;
            }
            if(beginSubTime != null){
                sql += " and substring(datetime,12,8) >= '" + beginSubTime  + "'";
            }
            if(endSubTime != null){
                sql += " and substring(datetime,12,8) <= '" + endSubTime  + "'";
            }
            sql += "   group by sourcegname,datetime";
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            System.out.println(sql);
            String daypre  ;
            long liveroom;
            String sourcegname;
            while (rs.next()) {
                sourcegname = rs.getString(3).trim();
                if(filterMap.containsKey(sourcegname)){

                    daypre = rs.getString(1).substring(0,10);
                    liveroom = rs.getLong(2);
                    if(sourcegnameMap.containsKey(sourcegname)){
                        resultList = sourcegnameMap.get(sourcegname);
                        if(resultList.containsKey(daypre)){
                            if(liveroom>resultList.get(daypre)){
                                resultList.put(daypre,liveroom);
                            }
                        }else{
                            resultList.put(daypre,liveroom);
                        }
                        sourcegnameMap.put(sourcegname,resultList);


                    }else{
                        resultList = new HashMap<String, Long>();
                        resultList.put(daypre,liveroom);
                        sourcegnameMap.put(sourcegname,resultList);
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
        return sourcegnameMap;
    }

    public int getSendsMsgNumByparquet(Integer platID, String beginDay, String endDay ) {
        Statement ps = null;
        ResultSet rs = null;
        int result=0;
        try {

            String sql ="  select count(distinct from_id) FROM hive.parquet.live_show_message WHERE date>='" + beginDay + "' and date <='" + endDay +  "'   and plat = " + platID  ;
            System.out.println("getSendsMsgNumByparquet sql : " + sql);
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            if (rs.next()) {
                result = rs.getInt(1);
            }
        }
        catch(SQLException e) {
            Log.error(e);
        }
        finally {
            close(rs, ps);
        }
        return result;
    }

    public int getSendsGiftNumByparquet(Integer platID, String beginDay, String endDay ) {
        Statement ps = null;
        ResultSet rs = null;
        int result=0;
        try {

            String sql ="  select count(distinct from_id) FROM hive.parquet.live_show_gift  WHERE gift_type='1' and  date>='" + beginDay + "' and date <='" + endDay +  "'   and plat = " + platID  ;
            System.out.println("getSendsGiftNumByparquet sql : " + sql);
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            if (rs.next()) {
                result = rs.getInt(1);
            }
        }
        catch(SQLException e) {
            Log.error(e);
        }
        finally {
            close(rs, ps);
        }
        return result;
    }
    public int getSendsGiftNumByparquetnofilterType(Integer platID, String beginDay, String endDay ) {
        Statement ps = null;
        ResultSet rs = null;
        int result=0;
        try {

            String sql ="  select count(distinct from_id) FROM hive.parquet.live_show_gift  WHERE   date>='" + beginDay + "' and date <='" + endDay +  "'   and plat = " + platID  ;
            System.out.println("getSendsGiftNumByparquet sql : " + sql);
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            if (rs.next()) {
                result = rs.getInt(1);
            }
        }
        catch(SQLException e) {
            Log.error(e);
        }
        finally {
            close(rs, ps);
        }
        return result;
    }

    public Map<String,Integer> getCombatTeamMsgTimesGroupMsgByparquet( String day,String platAndRoom) {
        Statement ps = null;
        ResultSet rs = null;
        Log.info("getCombatTeamMsgTimesGroupMsgByparquet begin");
        Map<String,Integer> resultList = new HashMap<String, Integer>();
        String platPar = " ";
        try {
            if(platAndRoom!=null ){
                platAndRoom = platAndRoom.trim();
                if(platAndRoom.length()>0){
                    int splat_ = platAndRoom.indexOf("_");
                    if(splat_ > 0){
                        String t1 = platAndRoom.substring(0,splat_).trim();
                        String t2 = platAndRoom.substring(splat_+1).trim();
                        if(t1.length()>0&& t2.length()>0){
                            platPar  = " and platform_id = " + t1  + " and room_id = '" + t2 + "' ";
                        }


                    }

                }

            }

            String sql ="  SELECT content,count(*) FROM hive.parquet.live_show_message WHERE date = '" +  day + "'" + platPar  + "  and   (content like '%AG%'" +
                    " or content like '%SV%'" +
                    " or content like '%情久%'" +
                    " or content like '%Q9%'" +
                    " or content like '%汉宫%'" +
                    " or content like '%HG%'" +
                    " or content like '%AE%'" +
                    " or content like '%白鲨%'" +
                    " or content like '%BS%'" +
                    " or content like '%EMG%'" +
                    " or content like '%5G%'" +
                    " or content like '%IG%'" +
                    " or content like '%TM%'" +
                    " or content like '%LGD%'" +
                    " or content like '%干爹%'" +
                    " or content like '%aster%'" +
                    " or content like '%茶队%'" +
                    " or content like '%VG%'" +
                    " or content like '%Ehome%'" +
                    " or content like '%后母%'" +
                    " or content like '%DBG%'" +
                    " or content like '%RNG%'" +
                    " or content like '%KG%'" +
                    " or content like '%newbee%'" +
                    " or content like '%RNG%'" +
                    " or content like '%IG%'" +
                    " or content like '%EDG%'" +
                    " or content like '%LGD%'" +
                    " or content like '%WE%'" +
                    " or content like '%OMG%'" +
                    " or content like '%BA%'" +
                    " or content like '%QG%'" +
                    " or content like '%Hero%'" +
                    " or content like '%EDG%'" +
                    " or content like '%RNG%'" +
                    " or content like '%eStar%'" +
                    " or content like '%OMG%'" +
                    " or content like '%17%'" +
                    " or content like '%4AM%'" +
                    " or content like '%EDG%'" +
                    " or content like '%RNG%'" +
                    " or content like '%LGD%')  group by content " ;
            System.out.println("getCombatTeamMsgTimesGroupMsgByparquet sql : " + sql);
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                resultList.put(rs.getString(1),rs.getInt(2));
            }
        }
        catch(SQLException e) {
            Log.error(e);
        }
        finally {
            close(rs, ps);
        }
        Log.info("getCombatTeamMsgTimesGroupMsgByparquet   end");
        return resultList;
    }

}