package com.alien.comm;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
	 * 定义数据源常量类
	 * @author alien
	 * @version 1.0
	 */
	public class JdbcConfig {
		 /** 数据库驱动 */
		 public static  String DRIVERCLASSNAME;
		 /** 数据库URL */
		 public static  String URL;
		 /** 数据库用户名 */
		 public static  String USERNAME;
		 /** 数据库密码 */
		 public static  String PASSWORD;
		 
		 
		 static {
				Properties prop = new Properties();
				InputStream in = Object.class.getResourceAsStream("/database.properties");
				try {
					prop.load(in);
					URL = prop.getProperty("jdbc.url").trim();
					DRIVERCLASSNAME = prop.getProperty("jdbc.driver").trim();
					USERNAME = prop.getProperty("jdbc.username").trim();
					PASSWORD = prop.getProperty("jdbc.password").trim();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

}
