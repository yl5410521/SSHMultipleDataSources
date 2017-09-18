package com.alien.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alien.dao.RoleDao;
import com.alien.entity.Role;
import com.alien.service.RoleService;

@Service("roleServiceImpl")
public class RoleServiceImpl extends BaseServiceImpl<Role, String>implements RoleService {

	@Resource(name = "roleDaoImpl")
	private RoleDao roleDao;

	@Resource(name = "roleDaoImpl")
	public void setBaseDao(RoleDao roleDao) {
		super.setBaseDao(roleDao);
		;
	}

}
