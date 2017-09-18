package com.alien.service;

import com.alien.entity.Admin;

public interface AdminService extends BaseService<Admin, String>{
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
