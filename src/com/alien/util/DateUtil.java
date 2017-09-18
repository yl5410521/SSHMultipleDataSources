package com.alien.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间转换
 * @author Alien
 *
 */
public class DateUtil {

	
	public static String formatDate(Date date,String format){
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String time = sdf.format(date);
		return time;
	}
}
