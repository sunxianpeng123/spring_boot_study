package com.xiaohulu.tools;

import com.xiaohulu.log.Log;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;


import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Helper {






	public static String getPre2DayYYYYMMDD(int dayCount){
		Date dNow = new Date();   //当前时间
		Date dBefore = new Date();
		Calendar calendar = Calendar.getInstance(); //得到日历
		calendar.setTime(dNow);//把当前时间赋给日历
		calendar.add(Calendar.DAY_OF_MONTH, -(dayCount));  //设置为前一天
		dBefore = calendar.getTime();   //得到前一天的时间
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd"); //设置时间格式
		String defaultStartDate = sdf.format(dBefore);    //格式化前一天
		return defaultStartDate;
	}


	public static String getLastDayOfMonth(int year,int month) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month-1);
		cal.set(Calendar.DAY_OF_MONTH,cal.getActualMaximum(Calendar.DATE));
		return  new   SimpleDateFormat( "yyyy-MM-dd").format(cal.getTime());
	}
	public static int getMonthLastDay(int year, int month){
		Calendar a = Calendar.getInstance();
		a.set(Calendar.YEAR,year);
		a.set(Calendar.MONTH,month-1);
		a.set(Calendar.DATE,1);//把日期设置为当月第一天  
		a.roll(Calendar.DATE,-1);//日期回滚一天，也就是最后一天  
		int maxDate = a.get(Calendar.DATE);
		return maxDate;
	}




	
	 public static String httpGet(String url){
		 
		  
		 RequestConfig defaultRequestConfig = RequestConfig.custom()
				  .setSocketTimeout(30000)
				  .setConnectTimeout(30000)
				  .setConnectionRequestTimeout(30000)
				  .build();
	        //get请求返回结果
	        String result = "";
	        HttpEntity entity=null;
	        CloseableHttpClient client=null;
	        CloseableHttpResponse response=null;
	        HttpGet request=null;
	        try {
	        	 client = HttpClients.custom()
	        			    .setDefaultRequestConfig(defaultRequestConfig)
	        			    .build();
	            //发送get请求
	             request = new HttpGet(url);
	            response = client.execute(request);
	            /**请求发送成功，并得到响应**/
	            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
	                /**读取服务器返回过来的json字符串数据**/
	            	entity=response.getEntity();
	                String strResult = EntityUtils.toString(entity);
	                /**把json字符串转换成json对象**/
	                result=strResult;
	            } else {
	            	result="";
	            }
	        } catch (Exception e) {
	        	System.out.println(url+":"+e.getMessage());
	        }
	        finally{
	        	
	        	try {
	        		if(entity!=null){
					  EntityUtils.consume(entity);
					}
	        		if(client!=null){
	        		  client.close();
	        		}
	        		if(response!=null){
	        			
	        			response.close();
	        		}
	        		if(request!=null){
	        			request.abort();
	        		}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
	        return result;
		 
		  
	    }
	 public static String[] getZsSql2(String pj){
//		 弹幕条数* (1/(200+分钟))*260 *平台系数
//		 弹幕人数* (1/(720+分钟))*780 *平台系数
//		礼物价值*  (1/(400+分钟))*460 *平台系数
//		 礼物人数*  (1/(1440+分钟))*1500 *平台系数
		 String messageCountXS = "((1/(200+airtime"+pj+"))*260)";
		 String messageSenderCountXS = "((1/(200+airtime"+pj+"))*260)";
		 String giftPriceXS = "((1/(200+airtime"+pj+"))*260)";
		 String giftSendCountXS = "((1/(200+airtime"+pj+"))*260)";
		 String r[]=new String[3];
		 String hlzsSQl =  "sqrt(ifnull((((("+messageCountXS+"*message_count"+pj+") / ("+messageCountXS+"*max_message_count"+pj+"))*1000 )*0.3)+(((("+messageSenderCountXS+"*message_sender_count"+pj+") / ("+messageSenderCountXS+"*max_message_sender_count"+pj+"))*1000)*0.7),0)*1000)";
		  String xjzsSQL = " sqrt(ifnull(((((("+giftPriceXS+"*gift_sum_price"+pj+") / ("+giftPriceXS+"*max_gift_price"+pj+"))*1000)*0.7)+ (((("+giftSendCountXS+"*gift_sender_count"+pj+") / ("+giftSendCountXS+"*max_gift_send_count"+pj+"))*1000)*0.3) ),0)*1000) ";
		  String xhlzsSQL="((0.6*"+hlzsSQl+")+ (0.4*"+xjzsSQL+")) ";
		 r[0]=hlzsSQl;
		 r[1]=xjzsSQL;
		 r[2]=xhlzsSQL;
		 
		 return r;
		 
	 }
	 
	 public static String[] getZsSqlLast(String pj){
//		 弹幕条数* (1/(200+分钟))*260 *平台系数
//		 弹幕人数* (1/(720+分钟))*780 *平台系数
//		礼物价值*  (1/(400+分钟))*460 *平台系数
//		 礼物人数*  (1/(1440+分钟))*1500 *平台系数

		 
		  String messageCountXS = "((1/(200+airtime"+pj+"))*260)";
		   String messageSenderCountXS = "((1/(200+airtime"+pj+"))*260)";
		   String giftPriceXS = "((1/(200+airtime"+pj+"))*260)";
		   String giftSendCountXS = "((1/(200+airtime"+pj+"))*260)";
		   String r[]=new String[3];
		   String hlzsSQl =  "(ifnull((sqrt((("+messageCountXS+"*message_count"+pj+") / ("+messageCountXS+"*max_message_count))*1000000 )*0.3)+(sqrt((("+messageSenderCountXS+"*message_sender_count"+pj+") / ("+messageSenderCountXS+"*max_message_sender_count))*1000000)*0.7),0))";
		    String xjzsSQL = " (ifnull((sqrt((("+giftPriceXS+"*gift_sum_price"+pj+") / ("+giftPriceXS+"*max_gift_sum_price))*1000000)*0.7)+ (sqrt((("+giftSendCountXS+"*gift_sender_count"+pj+") / ("+giftSendCountXS+"*max_gift_sender_count))*1000000)*0.3) ,0)) ";
		    String xhlzsSQL="((0.6*"+hlzsSQl+")+ (0.4*"+xjzsSQL+")) ";
		  
			 r[0]=hlzsSQl;
			 r[1]=xjzsSQL;
			 r[2]=xhlzsSQL;
		 return r;
		 
	 }
	 
	 
	 public static String[] getZsSql(String pj){
//		 弹幕条数* (1/(200+分钟))*260 *平台系数
//		 弹幕人数* (1/(720+分钟))*780 *平台系数
//		礼物价值*  (1/(400+分钟))*460 *平台系数
//		 礼物人数*  (1/(1440+分钟))*1500 *平台系数
//		  `max_message_top_pre_count` 
//		  `max_gift_top_pre_price` 
		 
		  String messageCountXS = "((1/(200+airtime"+pj+"))*260)";
		   String messageSenderCountXS = "((1/(200+airtime"+pj+"))*260)";
		   String giftPriceXS = "((1/(200+airtime"+pj+"))*260)";
		   String giftSendCountXS = "((1/(200+airtime"+pj+"))*260)";
		   String r[]=new String[3];
		   String hlzsSQl =  "(ifnull((sqrt((("+messageCountXS+"*message_count"+pj+") / ("+messageCountXS+"*max_message_count))*1000000 )*0.3)+(sqrt((("+messageSenderCountXS+"*message_sender_count"+pj+") / ("+messageSenderCountXS+"*max_message_sender_count))*1000000)*0.4)+(sqrt(((message_top_pre_count"+pj+") / (max_message_top_pre_count))*1000000)*0.3),0))";
		    String xjzsSQL = " (ifnull((sqrt((("+giftPriceXS+"*gift_sum_price"+pj+") / ("+giftPriceXS+"*max_gift_sum_price))*1000000)*0.5)+ (sqrt((("+giftSendCountXS+"*gift_sender_count"+pj+") / ("+giftSendCountXS+"*max_gift_sender_count))*1000000)*0.3)+(sqrt(((gift_top_pre_price"+pj+") / (max_gift_top_pre_price))*1000000)*0.2) ,0)) ";
		    String xhlzsSQL="((0.6*"+hlzsSQl+")+ (0.4*"+xjzsSQL+")) ";
		  
			 r[0]=hlzsSQl;
			 r[1]=xjzsSQL;
			 r[2]=xhlzsSQL;
		 return r;
		 
	 }

	public static String[] getNewZsSql(Map<String,Double> coeconf){
//		 弹幕条数* (1/(200+分钟))*260 *平台系数
//		 弹幕人数* (1/(720+分钟))*780 *平台系数
//		礼物价值*  (1/(400+分钟))*460 *平台系数
//		 礼物人数*  (1/(1440+分钟))*1500 *平台系数
//		  `max_message_top_pre_count`
//		  `max_gift_top_pre_price`


		String r[]=new String[3];
		//String hlzsSQl =  "(ifnull((sqrt((("+messageCountXS+"*message_count"+pj+") / ("+messageCountXS+"*max_message_count))*1000000 )*0.3)+(sqrt((("+messageSenderCountXS+"*message_sender_count"+pj+") / ("+messageSenderCountXS+"*max_message_sender_count))*1000000)*0.4)+(sqrt(((message_top_pre_count"+pj+") / (max_message_top_pre_count))*1000000)*0.3),0))";
		//String xjzsSQL = " (ifnull((sqrt((("+giftPriceXS+"*gift_sum_price"+pj+") / ("+giftPriceXS+"*max_gift_sum_price))*1000000)*0.5)+ (sqrt((("+giftSendCountXS+"*gift_sender_count"+pj+") / ("+giftSendCountXS+"*max_gift_sender_count))*1000000)*0.3)+(sqrt(((gift_top_pre_price"+pj+") / (max_gift_top_pre_price))*1000000)*0.2) ,0)) ";
		//String xhlzsSQL="((0.6*"+hlzsSQl+")+ (0.4*"+xjzsSQL+")) ";dmrsxs
		String hlzsSQl = "ifnull(message_count_yesterday *" +  (coeconf.containsKey("dmtsxs") ? coeconf.get("dmtsxs") : 1)  + " ,0) +  " +
				"ifnull(message_sender_count_yesterday *" + (coeconf.containsKey("dmrsxs") ? coeconf.get("dmrsxs") : 6.32)   + " ,0) + " +
				"(case when (airtime_yesterday is not null and airtime_yesterday >0 ) then ifnull(message_count_yesterday/airtime_yesterday,0) else 0 end ) * " +  (coeconf.containsKey("dwsjdmtsxs") ? coeconf.get("dwsjdmtsxs") : 90.54)    + " + " +
				"(0.0006*1 +0.1665) /( 0.0006 * ifnull(airtime_yesterday,0) +0.1665) * ifnull(message_sender_count_yesterday,0) * " +  (coeconf.containsKey("dwsjdmrsxs") ? coeconf.get("dwsjdmrsxs") : 11.1)  ;

		String xjzsSQL ="ifnull(gift_sum_price_yesterday *" +   (coeconf.containsKey("lwjzxs") ? coeconf.get("lwjzxs") : 2.95)   + " ,0) +  " +
				"ifnull(gift_sender_count_yesterday *" + (coeconf.containsKey("lwrsxs") ? coeconf.get("lwrsxs") : 9.37)    + " ,0) + " +
				"(case when (airtime_yesterday is not null and airtime_yesterday >0 ) then ifnull(gift_sum_price_yesterday/airtime_yesterday,0) else 0 end ) * " + (coeconf.containsKey("dwsjlwjzxs") ? coeconf.get("dwsjlwjzxs") : 290.22)  + " + " +
				"(0.0006*1 +0.1665) /( 0.0006 * ifnull(airtime_yesterday,0) +0.1665) * ifnull(gift_sender_count_yesterday,0) * " +   (coeconf.containsKey("dwsjlwrsxs") ? coeconf.get("dwsjlwrsxs") : 17.18) ;



		r[0]=hlzsSQl;
		r[1]=xjzsSQL;
		r[2]="";
		return r;

	}
	 
//	 public static String[] getZsSql(String pj){
////		 弹幕条数* (1/(200+分钟))*260 *平台系数
////		 弹幕人数* (1/(720+分钟))*780 *平台系数
////		礼物价值*  (1/(400+分钟))*460 *平台系数
////		 礼物人数*  (1/(1440+分钟))*1500 *平台系数
////      message_top_pre_count,gift_top_pre_price
//		 String messageCountTimeXS = "((1/(200+airtime"+pj+"))*260)";
//		 String messageSenderCountTimeXS = "((1/(200+airtime"+pj+"))*260)";
//		 String giftPriceTimeXS = "((1/(200+airtime"+pj+"))*260)";
//		 String giftSendCountTimeXS = "((1/(200+airtime"+pj+"))*260)";
//		 String dmCountXS=messageCountTimeXS+"*message_count"+pj;
//		 String dmSendCountXs=messageSenderCountTimeXS+"*message_sender_count"+pj;
//		 
//		 String r[]=new String[3];
//		 String hlzsSQl =  "(ifnull((sqrt((("+messageCountTimeXS+") / (max_message_count"+pj+"))*1000 )*0.3)+(((("+messageSenderCountTimeXS+") / (max_message_sender_count"+pj+"))*1000)*0.7),0)*1000)";
//		  String xjzsSQL = " sqrt(ifnull(((((("+giftPriceTimeXS+"*gift_sum_price"+pj+") / ("+giftPriceTimeXS+"*max_gift_price"+pj+"))*1000)*0.4)+ (((("+giftSendCountTimeXS+"*gift_sender_count"+pj+") / ("+giftSendCountTimeXS+"*max_gift_send_count"+pj+"))*1000)*0.6) ),0)*1000) ";
//		  String xhlzsSQL="((0.6*"+hlzsSQl+")+ (0.4*"+xjzsSQL+")) ";
//		 r[0]=hlzsSQl;
//		 r[1]=xjzsSQL;
//		 r[2]=xhlzsSQL;
//		 
//		 return r;
//		 
//	 }
	 
	 
	 public static String getFocusNumPlatIDS(){
		  String ids=null;
		  try{
		   Properties properties= new Properties();
	      String path=System.getProperty("user.dir")+"/db.properties";
	      InputStream is= new FileInputStream(path);
	      properties.load(is);
           ids = properties.getProperty("focusNumPlatIDS");
          is.close();
          }
		  catch ( Exception e ){
			  
			  Log.error(e);
		  }
          return ids;
	 }
	 public static String getDateC(String date , int p){
		 
		 
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); //设置时间格式
	    	Date dNow=null;
			try {
				dNow = sdf.parse(date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Calendar calendar = Calendar.getInstance(); //得到日历
			calendar.setTime(dNow);//把当前时间赋给日历
			calendar.add(Calendar.DAY_OF_MONTH, p);
			String defaultStartDate = sdf.format( calendar.getTime());     //格式化前一天
		   return defaultStartDate;
	 }

	public static String getLastDayByMonthOneday(String MonthfirstDay) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date dNow=null;
		try {
			dNow = df.parse(MonthfirstDay);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Calendar calendar = Calendar.getInstance();
		Date theDate = calendar.getTime();
		String s = df.format(theDate);
		calendar.setTime(dNow);

		calendar.add(Calendar.MONTH, 1); //加一个月
		calendar.set(Calendar.DATE, 1); //设置为该月第一天
		calendar.add(Calendar.DATE, -1); //再减一天即为上个月最后一天
		String day_last = df.format(calendar.getTime());
		StringBuffer endStr = new StringBuffer().append(day_last);
		day_last = endStr.toString();
		StringBuffer str = new StringBuffer().append(day_last);
		return str.toString();

	}

	 
	 public static String getSourceLinkIDSQL(){
		 String sql="substring_index(substring_index(substring_index(replace(replace(replace(replace(replace(substring_index(source_link,'/' , -1),'live?anchorid=',''),'.html',''),'.htm',''),'htm',''),'room.php?rid=',''),'uid=',-1),'&id',1),'?tempId',1)";
		 return  sql;
		 
	 }
	public static String filterEmoji( String source) {
		if (source != null && source.length() > 0) {
			String a= EmojiFilter.filterEmoji(source);
			return a;
			//return a.replaceAll("[\ud800\udc00-\udbff\udfff\ud800-\udfff]", "");
		} else {
			return source;
		}
	}
	public static String getEsServiceHostAndPort()  {
		String urlPort = "";
	 	try{
		Properties properties= new Properties();
		String path=System.getProperty("user.dir")+"/db.properties";
		InputStream is= new FileInputStream(path);
		properties.load(is);
		 urlPort = "http://" + properties.getProperty("EsHost");}
		catch (Exception e) {
				Log.error(e);
			}
		return  urlPort;
	}
	public static String getConfigSetStr(String val)  {
		String retstr = "";
		try{
			Properties properties= new Properties();
			String path=System.getProperty("user.dir")+"/db.properties";
			InputStream is= new FileInputStream(path);
			properties.load(is);
			retstr =  properties.getProperty(val);}
		catch (Exception e) {
			Log.error(e);
		}
		return  retstr;
	}
	/**
	 * 向指定 URL 发送POST方法的请求
	 *
	 * @param url
	 *            发送请求的 URL
	 * @param param
	 *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return 所代表远程资源的响应结果
	 */
	public static String sendPost(String url, String param) {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			conn.setConnectTimeout(60000);
			conn.setReadTimeout(60000);
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());
			// 发送请求参数
			out.print(param);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(
					new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送 POST 请求出现异常！"+e);
			e.printStackTrace();
		}
		//使用finally块来关闭输出流、输入流
		finally{
			try{
				if(out!=null){
					out.close();
				}
				if(in!=null){
					in.close();
				}
			}
			catch(IOException ex){
				ex.printStackTrace();
			}
		}
		return result;
	}

	public static String getEsMsgByAnchorAndDate(String platID,String roomID,Long temptimebegin,Long temptimeend){
		return "ces分词字符串";
	}
	
	public static List<String> getIndexDateName(long start ,long end){
		
		List<String> result =new ArrayList<String>();
		long startTime= start;
		long endTime =end;
		
	    SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd"); //设置时间格式
	    Date startDate = new Date(startTime*1000);
	    Date endDate = new Date(endTime*1000);
	    
	    try {
	    	startTime=	sdf.parse(sdf.format(startDate)).getTime();
	    	endTime=	sdf.parse(sdf.format(endDate)).getTime();
	    	
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    
	    Calendar calendar = Calendar.getInstance(); //得到日历
	    Date addTime =startDate;
	    int i=0;
	    long dayTime=60*60*24*1000;
	    String indexWholeName="";
	   while(startTime<=endTime){
		   	calendar.setTime(startDate);//把当前时间赋给日历
			calendar.add(Calendar.DAY_OF_MONTH, i);  
			addTime=calendar.getTime();
			indexWholeName=sdf.format(addTime);
			result.add("'"+indexWholeName+"'");
			startTime=startTime+dayTime;
			i++;
	    }
	   
	   return result;
		
	}

	public static float getListAvgOfInteger( List<Integer> listval) {

		int num = listval.size();
		if(num==0){
			return 0;
		}
		float v = 0;
		for(Integer m:listval){
			v += m;
		}
		System.out.println("数组长度: " + num + ", 数组求和 总计:" + v  );
		float result = v/num;
		return result;

	}

	//得到时间戳
	public static Long getUninxTime(String time ){
		long unixTimestamp =0l;
		try {
			//String time = "2011-07-29 14:50:11";
			Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(time);
			unixTimestamp = date.getTime()/1000;
			//System.out.println(unixTimestamp);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return unixTimestamp;
	}
	public  static  Map<String,Long> getMapdatasByKeys(Map<String,Long>mapinput,Map<String,Integer>mapkeys ){
		Map<String,Long>mapOutput = new HashMap<String, Long>();
		long totalother = 0;
		for(String item:mapinput.keySet()){
			if(mapkeys.containsKey(item)){
				mapOutput.put(item,mapinput.get(item));
			}else{
				totalother += mapinput.get(item);
			}
		}
		mapOutput.put("othermapval",totalother);
		return mapOutput;

	}

	public static long   getMapdatasByKeys(Map<String,Long>mapinput ){

		long totalother = 0;
		for(String item:mapinput.keySet()){
			totalother += mapinput.get(item);
		}
		return mapinput.size()>0 ? totalother/mapinput.size() : 0l;

	}
	public static Map<String,Long>   getMapdatasByKeysBySourcegname( Map<String,Map<String,Long> > inputData ,Map<String,Set<String>> relationDatas,Map<String,Integer> maptype  ){
		Map<String,Long> sourgegroupmap = new HashMap<String, Long>();
		Map<String,Long> returnroupmap = new HashMap<String, Long>();
		long totalnum;
		Set<String> setTemp;
		Set<String> notSet = new HashSet<String>();
		int i;

		Map<String,Long> mapinput ;
		for(String top:inputData.keySet()){
			mapinput = inputData.get(top);
			totalnum= 0;
			for(String item:mapinput.keySet()){
				totalnum += mapinput.get(item);
			}
			sourgegroupmap.put(top,mapinput.size()>0 ? totalnum/mapinput.size() : 0l);
		}
		for(String inkey : relationDatas.keySet()){
			setTemp = relationDatas.get(inkey);
			i=0;
			totalnum = 0;
			for(String thisk:setTemp){
				if(sourgegroupmap.containsKey(thisk)){
					notSet.add(thisk);
					totalnum += sourgegroupmap.get(thisk);
					i++;
				}
			}
			returnroupmap.put(inkey,i>0 ? totalnum/i :0 );
		}
		 int i1=0;
		int i2=0;
		int i3=0;
		int i4=0;
		long totalother1 = 0;
		long totalother2 = 0;
		long totalother3 = 0;
		long totalother4 = 0;
		int iall=0;
		long  totalall=0;
		for(String tempk:sourgegroupmap.keySet()){

			totalall += sourgegroupmap.get(tempk);

			if(!notSet.contains(tempk)){
				if(  maptype.get(tempk).intValue()==1){
					i1++;
					totalother1 +=sourgegroupmap.get(tempk);
				}
				if(  maptype.get(tempk).intValue()==2){
					i2++;
					totalother2 +=sourgegroupmap.get(tempk);
				}
				if(  maptype.get(tempk).intValue()==3){
					i3++;
					totalother3 +=sourgegroupmap.get(tempk);
				}
				if(  maptype.get(tempk).intValue()==4){
					i4++;
					totalother4 +=sourgegroupmap.get(tempk);
				}

			}
		}
		/*
		returnroupmap.put("PC游戏其他分类",i1>0 ? totalother1/i1 :0 );
		returnroupmap.put("手机游戏其他分类",i2>0 ? totalother2/i2 :0 );
		returnroupmap.put("主机游戏其他分类",i3>0 ? totalother3/i3 :0 );
		returnroupmap.put("娱乐其他分类",i4>0 ? totalother4/i4 :0 );
		*/
		returnroupmap.put("PC游戏其他分类",i1>0 ? totalother1 :0 );
		returnroupmap.put("手机游戏其他分类",i2>0 ? totalother2:0 );
		returnroupmap.put("主机游戏其他分类",i3>0 ? totalother3 :0 );
		returnroupmap.put("娱乐其他分类",i4>0 ? totalother4:0 );
		returnroupmap.put("全部", totalall);

		return returnroupmap;

	}
	public static Map<String,Long> getNewSourgeGnameWhithMap(Map<String,Long> sourgegroupmap,Map<String,Set<String>> relationDatas,Map<String,Integer> maptype ){
		Map<String,Long> returnroupmap = new HashMap<String, Long>();
		long totalother;
		Set<String> setTemp;
		Set<String> notSet = new HashSet<String>();
		for(String inkey : relationDatas.keySet()){
			setTemp = relationDatas.get(inkey);
			totalother = 0;
			for(String thisk:setTemp){
				if(sourgegroupmap.containsKey(thisk)){
					notSet.add(thisk);
					totalother += sourgegroupmap.get(thisk);

				}
			}
			returnroupmap.put(inkey, totalother );
		}


		long  totalall=0;
		int i1=0;
		int i2=0;
		int i3=0;
		int i4=0;
		long totalother1 = 0;
		long totalother2 = 0;
		long totalother3 = 0;
		long totalother4 = 0;
		int iall=0;

		for(String tempk:sourgegroupmap.keySet()){

			totalall += sourgegroupmap.get(tempk);

			if(!notSet.contains(tempk)){

				if(  maptype.get(tempk).intValue()==1){
					i1++;
					totalother1 +=sourgegroupmap.get(tempk);
				}
				if(  maptype.get(tempk).intValue()==2){
					i2++;
					totalother2 +=sourgegroupmap.get(tempk);
				}
				if(  maptype.get(tempk).intValue()==3){
					i3++;
					totalother3 +=sourgegroupmap.get(tempk);
				}
				if(  maptype.get(tempk).intValue()==4){
					i4++;
					totalother4 +=sourgegroupmap.get(tempk);
				}

			}
		}
		returnroupmap.put("PC游戏其他分类", totalother1);
		returnroupmap.put("手机游戏其他分类",totalother2 );
		returnroupmap.put("主机游戏其他分类", totalother3  );
		returnroupmap.put("娱乐其他分类", totalother4  );
		returnroupmap.put("全部", totalall);

		return returnroupmap;



	}

	public static Map<String,Set<String>> getRelationSourgeGname(Set<String> typeList,Map<String,Integer> sourcenameType,Map<String,String> sourgebigRelation){
		Map<String,Set<String>> returnMap= new HashMap<String, Set<String>>();
		if(typeList.size()==0){
			return returnMap;
		}
		Set<String>  ilist;
		String delsource;
		for(String item:typeList){
			if(sourgebigRelation.containsKey(item)){
				delsource = sourgebigRelation.get(item);
				if(returnMap.containsKey(delsource)){
					ilist = returnMap.get(delsource);
					ilist.add(item);
					returnMap.put(delsource,ilist);
				}else{
					ilist = new HashSet<String>();
					ilist.add(item);
					returnMap.put(delsource,ilist);
				}

			}else{
				delsource = item;
				ilist = new HashSet<String>();
				ilist.add(delsource);
				returnMap.put(delsource,ilist);
			}
		}

		return returnMap;

	}
	
	

}
