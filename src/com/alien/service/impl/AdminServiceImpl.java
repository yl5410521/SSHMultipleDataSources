package com.alien.service.impl;

import javax.annotation.Resource;



import org.springframework.stereotype.Service;

import com.alien.dao.AdminDao;
import com.alien.entity.Admin;
import com.alien.service.AdminService;

@Service("adminServiceImpl")
public class AdminServiceImpl extends BaseServiceImpl<Admin, String> implements
		AdminService {

	@Resource(name = "adminDaoImpl")
	private AdminDao adminDao;

	@Resource(name = "adminDaoImpl")
	public void setBaseDao(AdminDao adminDao) {
		super.setBaseDao(adminDao);
	}

	public boolean login(Admin admin) {
		
		return adminDao.login(admin);
	}

	public Admin adminQuery(String name) {
		
		return adminDao.adminQuery(name);
	}


}
