package com.alien.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
public class Permission extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4369856649828561984L;

	// 表
	private SpreadSheets spreadSheets;

	private Role role;

	private int add;// 新增
	
	private int upd;// 更新
	
	private int del;// 删除
	
	private int sel;// 查询

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "spreadsheetsid")
	public SpreadSheets getSpreadSheets() {
		return spreadSheets;
	}

	public void setSpreadSheets(SpreadSheets spreadSheets) {
		this.spreadSheets = spreadSheets;
	}

	@Column(name="add")
	public int getAdd() {
		return add;
	}

	public void setAdd(int add) {
		this.add = add;
	}
	@Column(name="upd")
	public int getUpd() {
		return upd;
	}

	public void setUpd(int upd) {
		this.upd = upd;
	}
	@Column(name="del")
	public int getDel() {
		return del;
	}

	public void setDel(int del) {
		this.del = del;
	}
	@Column(name="sel")
	public int getSel() {
		return sel;
	}

	public void setSel(int sel) {
		this.sel = sel;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "roleid")
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}
