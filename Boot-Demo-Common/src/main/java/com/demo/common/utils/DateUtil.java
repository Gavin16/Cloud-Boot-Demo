package com.demo.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * className: DateUtil
 * @author Minsky
 * date: 2017-12-9 上午11:14:22
 * Description:判断身份证中的日期是否是有效日期 
 */
public class DateUtil {

	/** 单位为ms的场景下一秒钟的取值 */
	public static final long SECOND = 1000;
	public static final long MINUTE = 60*SECOND;
	public static final long HOUR = 60*MINUTE;
	public static final long DAY = 24*HOUR;


	//身份证中的日期有可能是农历也有可能是公历
	
	/**
	 * @Title: isValidGregDate
	 * @param dateStr
	 * @return boolean
	 * date: 2017-12-9 上午11:13:10
	 * @Description: 判断公历是否有效
	 * @throws 
	 */
	public static boolean isValidGregDate(String dateStr) {
		if(isDigit(dateStr)) {
			int dateInt = Integer.parseInt(dateStr);
			int year = dateInt/10000;
			int month = (dateInt/100)%100;
			int day = dateInt%100;
			
			return isValidDay(year,month,day)?true:false;
		}
		return false;
	}
	
	/**
	 * @Title: isDigit
	 * @param digStr
	 * @return boolean
	 * date: 2017-12-9 上午11:13:35
	 * @Description: 判断是否是数字
	 * @throws
	 */
	public static boolean isDigit(String digStr) {
		boolean res = false;
		if(StringUtils.isNotEmpty(digStr)) {
			for(int i=0;i<digStr.length();i++) {
				if(!Character.isDigit(digStr.charAt(i))) {
					return res;
				}
			}
			res = true;
		}
		// 若传入字符串为空
		return res;
	}
	
	/**
	 * @Title: isValidDay
	 * @param year
	 * @param month
	 * @param day
	 * @return boolean
	 * date: 2017-12-9 上午11:13:58
	 * @Description: 判断某一天是否是存在的一天
	 * @throws
	 */
	public static boolean isValidDay(int year,int month,int day){
		if(month >=1 && month <=12 && day >=1 && day <=31) {
			boolean isLeapYear = isLeapYear(year);
			switch(month) {
			case 1: case 3:	case 5:case 7:case 8:case 10:case 12:
					return day>0 && day<=31 ? true:false; 
			case 2: if(isLeapYear){
						return day>0 && day<=29 ? true:false; 
					}else{
						return day>0 && day<=28 ? true:false;
					}
			case 4: case 6: case 9: case 11: 
				return day>0 && day<=30 ? true:false; 
			default:
				return false;
			}
		}
		return false;
	}
	
	/**
	 * @Title: isLeapYear
	 * @param year
	 * @return boolean
	 * date: 2017-12-9 上午11:14:42
	 * @Description: 判断是否是闰年
	 * @throws
	 */
	public static boolean isLeapYear(int year){
		return (year%4 == 0 && year %100 != 0)||(year % 400 == 0)?true:false;
	}



	/**
	 * 计算任意两个日期中间相隔多少天
	 * @param begin_date
	 * @param end_date
	 * @return
	 * @throws Exception
	 */
	public static long getInterval(Date begin_date, Date end_date) throws ParseException{
		long day = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		if(begin_date != null){
			String begin = sdf.format(begin_date);
			begin_date  = sdf.parse(begin);
		}
		if(end_date!= null){
			String end= sdf.format(end_date);
			end_date= sdf.parse(end);
		}
		day = (end_date.getTime()-begin_date.getTime())/(24*60*60*1000);
		return day;
	}

}
