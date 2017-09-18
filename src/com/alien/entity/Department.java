package com.alien.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ForeignKey;

import com.google.gson.annotations.Expose;



/**
 * 部门
 * @author Administrator
 *
 */
@Entity
public class Department extends BaseEntity{
	
	@Expose
	private String name; //部门名称
	
	
	private Set<Admin> admin=new HashSet<Admin>();
	
	

	@Column(name="name",nullable=false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "department")
	public Set<Admin> getAdmin() {
		return admin;
	}

	public void setAdmin(Set<Admin> admin) {
		this.admin = admin;
	}
	
	
	
	
	
		
}
