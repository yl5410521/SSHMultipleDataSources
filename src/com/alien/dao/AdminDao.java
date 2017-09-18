package com.alien.dao;


import com.alien.entity.Admin;


public interface AdminDao extends BaseDao<Admin, String> {
	/**
	 * 登录
	 * 
	 * @param admin
	 * @return
	 */
	public boolean login(Admin admin);

	/**
	 * 根据用户名获取全部信息
	 * @param name
	 * @return
	 */
	public Admin adminQuery(String name);
	
}

