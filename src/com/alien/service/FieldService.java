package com.alien.service;

import java.util.List;

import com.alien.entity.Field;


public interface FieldService extends BaseService<Field, String> {
	/**
	 * 添加字段
	 * 
	 * @param tableName
	 * @param fields
	 * @return
	 */
	public boolean addField(String tableName, List<Field> fields);

	/**
	 * 查询表字段
	 * 
	 * @param sheetsid
	 * @return
	 */
	public List<Field> fieldslist(String sheetsid);
	/**
	 * 检查字段名是否重复
	 * 
	 * @param fieldName
	 * @return false:重复，true：不重复
	 */
	public boolean checkfieldName(String sheetsid, String fieldName);
	/**
	 *建立主外键关系 
	 * @param masterTabile主表
	 * @param subordinateTable从表
	 * @return
	 */
	public boolean createforeignkey(String masterTabile,String subordinateTable);
	/**
	 * 保存字段
	 * @param field
	 */
	public void savefield(Field field);
	 /**
	  * 添加默认字段
	  * @param sid
	  */
	 public void addField(String sid);
}
