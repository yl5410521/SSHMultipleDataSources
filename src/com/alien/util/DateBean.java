package com.alien.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *  工具类 —— 日期
 */
public class DateBean {
	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(DateBean.class);
	private static final  String fm="yyyy-MM-dd hh:mm:ss";
	/**
	 * 将时间字符串转换为Date类型
	 * 
	 * @param dateStr
	 * @return Date
	 */
	public static Date toDate(String dateStr) {
		Date date = null;
		SimpleDateFormat formater = new SimpleDateFormat();
		formater.applyPattern("yyyy-MM-dd");
		try {
			date = formater.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	public static Date toDate(Date date) {
		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
		try {
			date = formater.parse(date.toString());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 按照提供的格式将字符串转换成Date类型
	 * 
	 * @param dateStr
	 * @param formaterString
	 * @return
	 */
	public static Date toDate(String dateStr, String formaterString) {
		Date date = null;
		SimpleDateFormat formater = new SimpleDateFormat();
		formater.applyPattern(formaterString);
		try {
			date = formater.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	public static Date toDate(String dateStr, String formaterString,Locale local) {
		Date date = null;
		SimpleDateFormat formater = new SimpleDateFormat(formaterString,local);
		try {
			date = formater.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 将Date类型时间转换为字符串
	 * 
	 * @param date
	 * @return
	 */
	public static String toString(Date date) {

		String time;
		SimpleDateFormat formater = new SimpleDateFormat();
		formater.applyPattern("yyyy-MM-dd");
		time = formater.format(date);
		return time;
	}
	
	/**
	 * 将Date类型时间转换为字符串
	 * 
	 * @param date
	 * @return
	 */
	public static String toString(Timestamp date) {
		String time="";
		if(date!=null){
			SimpleDateFormat formater = new SimpleDateFormat();
			formater.applyPattern("yyyy-MM-dd HH:mm:ss");
			time = formater.format(date);
		}
		return time;
	}
	
	/**
	 * 将Date类型时间转换为字符串
	 * 
	 * @param date
	 * @return
	 */
	public static String toString(Timestamp date,String fmt) {
		String time="";
		if(date!=null){
			SimpleDateFormat formater = new SimpleDateFormat();
			formater.applyPattern(fmt);
			time = formater.format(date);
		}
		return time;
	}

	/**
	 * 按照参数提供的格式将Date类型时间转换为字符串
	 * 
	 * @param date
	 * @param formaterString
	 * @return
	 */
	public static String toString(Date date, String formaterString) {
		String time;
		SimpleDateFormat formater = new SimpleDateFormat();
		formater.applyPattern(formaterString);
		time = formater.format(date);
		return time;
	}

	/**
	 * method 将字符串类型的日期转换为一个timestamp（时间戳记java.sql.Timestamp）
	 * 
	 * @param dateString
	 *            需要转换为timestamp的字符串
	 * @return dataTime timestamp
	 */
	public final static java.sql.Timestamp string2Time(String dateString)
			throws java.text.ParseException {
		DateFormat dateFormat;
		// dateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss.SSS",
		// Locale.ENGLISH);// 设定格式
		dateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss.S");
		dateFormat.setLenient(false);
		java.util.Date timeDate = dateFormat.parse(dateString);// util类型
		java.sql.Timestamp dateTime = new java.sql.Timestamp(timeDate.getTime());// Timestamp类型,timeDate.getTime()返回一个long型
		return dateTime;
	}

	/**
	 * method 将字符串类型的日期按照转换为一个timestamp（时间戳记java.sql.Timestamp）
	 * 
	 * @param dateString
	 *            需要转换为timestamp的字符串
	 * @param formaterString
	 *            dateString字符串的解析格式
	 * @return
	 * @throws java.text.ParseException
	 */
	public final static java.sql.Timestamp string2Time(String dateString,
			String formaterString) throws java.text.ParseException {
		DateFormat dateFormat;
		dateFormat = new SimpleDateFormat(formaterString);// 设定格式
		// dateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
		dateFormat.setLenient(false);
		java.util.Date timeDate = dateFormat.parse(dateString);// util类型
		java.sql.Timestamp dateTime = new java.sql.Timestamp(timeDate.getTime());// Timestamp类型,timeDate.getTime()返回一个long型
		return dateTime;
	}

	public static void main(String[] args) throws ParseException {
//		String t = DateBean.toString(new Date());
//		System.out.println(t);
//		Date date = DateBean.toDate("2010-06-17");
//		System.out.println(date);
		System.out.println(Integer.MAX_VALUE);
		System.out.println(DateBean.toString(new Timestamp(System.currentTimeMillis()), "yyyyMMddHHmmssSSS"));
		// String sToTimestamp = "2005-8-18 14:21:12.123";//
		// 用于转换成java.sql.Timestamp的字符串
//		String sToTimestamp = "2005-8-18 14:21";// 用于转换成java.sql.Timestamp的字符串
//		Timestamp Timestamp = string2Time(sToTimestamp);
//		System.out.println(Timestamp);
//		System.out.println(new Date());
//		System.out.println(DateBean.toDate(new Date("Thu May 17 00:00:00 CST 2012")).getDate());
	}

	public static String processTime(Date date) {

		long tm = (Calendar.getInstance().getTimeInMillis() - date.getTime()); 
		// int ms = (int)(tm%1000);
		tm /= 1000;
		int sc = (int) (tm % 60);
		tm /= 60;
		int mn = (int) (tm % 60);
		tm /= 60;
		int hr = (int) (tm % 24);
		long dy = tm / 24;

		if (dy > 0) {
			return dy + "天前";
		} else if (hr > 0) {
			return hr + "小时前";
		} else {
			if (mn==0) {
				return 1 + "分前";
			}else{
				return mn + "分前";
			}
			
		} 
	}
	public static Long toStirngTime(Date date){
		return DateBean.toDate(DateBean.toString(date,fm),fm).getTime()/1000;
	}
}
