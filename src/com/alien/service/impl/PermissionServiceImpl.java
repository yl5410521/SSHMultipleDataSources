package com.alien.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alien.dao.PermissionDao;
import com.alien.entity.Permission;
import com.alien.service.PermissionService;
@Service("permissionServiceImpl")
public class PermissionServiceImpl extends BaseServiceImpl<Permission, String> implements PermissionService {

	@Resource(name="permissionDaoImpl")
	private PermissionDao permissionDao;
	@Resource(name="permissionDaoImpl")
	public void setBaseDao(PermissionDao permissionDao) {
		super.setBaseDao(permissionDao);;
	}
	
	
}
