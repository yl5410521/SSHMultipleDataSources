package com.alien.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * 字段表
 * 
 * @author alien
 *
 */
@Entity
public class Field extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1684966900033165538L;
	private String name;// 名称
	private String fieldName;// 字段名称
	private String fieldcontent;// 字段属性
	private String fieldtype;// 数据库字段类型
	private String fieldLength=null;// 字段长度

	private SpreadSheets spreadSheets = new SpreadSheets();// 表名

	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "fieldName")
	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	@Column(name = "fieldcontent")
	public String getFieldcontent() {
		return fieldcontent;
	}

	public void setFieldcontent(String fieldcontent) {
		this.fieldcontent = fieldcontent;
	}

	@Column(name = "fieldtype")
	public String getFieldtype() {
		return fieldtype;
	}

	public void setFieldtype(String fieldtype) {
		this.fieldtype = fieldtype;
	}

	@Column(name = "fieldLength")
	public String getFieldLength() {
		return fieldLength;
	}

	public void setFieldLength(String fieldLength) {
		this.fieldLength = fieldLength;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "spreadSheets_id")
	public SpreadSheets getSpreadSheets() {
		return spreadSheets;
	}

	public void setSpreadSheets(SpreadSheets spreadSheets) {
		this.spreadSheets = spreadSheets;
	}

}
