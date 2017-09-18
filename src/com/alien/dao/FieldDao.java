package com.alien.dao;

import java.util.List;

import com.alien.entity.Field;


public interface FieldDao extends BaseDao<Field, String> {
	/**
	 * 生成字段到表中
	 * 
	 * @param tableName
	 * @param fields
	 * @return false:失败，true：成功
	 */
	public boolean addField(String tableName, List<Field> fields);

	/**
	 * 查询表所拥有的字段
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
	public boolean checkfieldName(String sheetsid,String fieldName);
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
	 * @return
	 */
	public void savefield(Field field);
	/**
	 * 默认字段加入到表中
	 * @param sid
	 */
	public void addField(String sid);
	
}
