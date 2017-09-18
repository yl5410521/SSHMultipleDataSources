package com.alien.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
/**
 * 表类
 * @author Alien
 *
 */
@Entity
public class SpreadSheets extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6771061647542800956L;
	
	private String name;//属性名称
	
	private String tableName;//表名
	
	private String className;//类名
	
	private Set<Field> fields=new HashSet<Field>();
	
	private Set<Permission> permissions=new HashSet<Permission>();

	@Column(name="tableName")
	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY, mappedBy = "spreadSheets")
	public Set<Field> getFields() {
		return fields;
	}

	public void setFields(Set<Field> fields) {
		this.fields = fields;
	}
	@Column(name="className")
	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY, mappedBy = "spreadSheets")
	public Set<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(Set<Permission> permissions) {
		this.permissions = permissions;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
	

	
	

	
	
	
	
	
	
	

}
