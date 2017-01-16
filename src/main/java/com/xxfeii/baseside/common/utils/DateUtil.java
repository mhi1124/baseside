package com.xxfeii.baseside.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 时间工具类
 * @ClassName DateUtil
 * @Description
 * @author davi
 * @date 2016年8月10日
 *
 */
public class DateUtil {
	private static Logger logger = LoggerFactory.getLogger(DateUtil.class);

	/**
	 * 一天的毫秒级
	 */
	public static long ONEDAY = 1000*60*60*24;
	
	/**
	 * yyyy-MM-dd HH:mm:ss
	 */
	public static String formatPatternDateTime = "yyyy-MM-dd HH:mm:ss";
	
	/**
	 * yyyy-MM-dd HH:mm:ss
	 */
	public static String formatPatternDate = "yyyy-MM-dd";
	
	/**
	 * 获取当天的时间
	 * @param hour 几点
	 * @param minute 几分
	 * @return
	 */
	public static Date getDate(int hour,int minute){
		Calendar c = Calendar.getInstance();
		c.set(Calendar.HOUR_OF_DAY, hour);
		c.set(Calendar.MINUTE, minute);
		c.set(Calendar.SECOND,0);
		return c.getTime();
	}
	
	/**
	 * 获取当天的时间
	 * @param hour 获取当天的时间
	 * @return
	 */
	public static Date getDate(int hour){
		return getDate(hour,0);
	}
	
	/**
	 * 获取前几天的日期  
	 * @param count
	 * @return
	 */
    public static String getPrefixDate(int count){  
        Calendar cal = Calendar.getInstance();  
        cal.add(Calendar.DATE,-count);   // int amount   代表天数  
        Date datNew = cal.getTime();
        SimpleDateFormat format = getDateFormat(null);
        return format.format(datNew);
    }
    
    /**
     * 日期转换成字符串 
     * @param date
     * @return
     */
    public static String dateToString(Date date){  
        SimpleDateFormat format = getDateFormat(null);  
        return format.format(date);  
    } 
    
    /**
     * 日期转换成字符串 
     * @param date
     * @return
     */
    public static String dateToString(Date date,String pattern){  
        SimpleDateFormat format = getDateFormat(pattern);  
        return format.format(date);  
    } 
    
    /**
     * 字符串转换日期  
     * @param str
     * @param pattern
     * @return
     */
    public static Date stringToDate(String str,String pattern){  
    	SimpleDateFormat format = getDateFormat(pattern);
        if(!str.equals("")&&str!=null){  
            try {
                return format.parse(str);  
            } catch (ParseException e) { 
            	logger.error(""+e.getMessage());
            }
        }
        return null;  
    }
    
    /**
     * 添加天数
     * @param date
     * @param day
     * @return
     */
    public static Date addDay(Date date,int day){
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(date);
    	cal.add(Calendar.DAY_OF_MONTH, day);
    	return cal.getTime();
    }
    
    /**
     * 添加天数
     * @param date
     * @param day
     * @return
     */
    public static Date addMonth(Date date,int count){
    	Calendar calendar = Calendar.getInstance(); //得到日历
    	calendar.setTime(date);//把当前时间赋给日历
    	calendar.add(Calendar.MONTH, -3);  //设置为前3月
    	return calendar.getTime();
    }
    
    
    /**
     * 获取一个时间格式
     * @param pattern
     * @return
     */
    private static SimpleDateFormat getDateFormat(String pattern){
    	SimpleDateFormat format = null;
    	if(StringUtils.isNotEmpty(pattern)){
    		format = new SimpleDateFormat(pattern);
    	}else{
    		format = new SimpleDateFormat(formatPatternDateTime);
    	}
    	return format;
    }
    
    public static void main(String[] args) {
		Date date = new Date();
		System.out.println(dateToString(addDay(date, 1)));
	}
}
