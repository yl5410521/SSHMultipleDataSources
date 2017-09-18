package com.alien.service;

import com.alien.entity.SpreadSheets;

public interface SpreadSheetsService extends BaseService<SpreadSheets, String>{
/**
 * 创建表
 * @param tableName表明
 * @param obj
 * @param noCol
 * @return false：失败，true：成功 
 */
	 public  boolean createTable(String className);
		/**
		 * 检查表名是否重复
		 * 
		 * @param tableName
		 * @return <1不重复，>1重复
		 */
	 public int checkTable(String className);


}