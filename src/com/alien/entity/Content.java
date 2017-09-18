package com.alien.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * 属性
 * 
 * @author alien
 *
 */
@Entity
public class Content extends BaseEntity {

	/**
		 * 
		 */
	private static final long serialVersionUID = 9207492342422928923L;

	public String contentType;// 属性<类似值：String,int,double,char,float,long>

	public String fieldsType;//字段类型
	
	public String name;// 别称

	
	@Column(name = "contentType")
	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name="fieldsType")
	public String getFieldsType() {
		return fieldsType;
	}

	public void setFieldsType(String fieldsType) {
		this.fieldsType = fieldsType;
	}
	
	
	
	
	
	

}
