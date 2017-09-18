package com.alien.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alien.dao.DepartmentDao;
import com.alien.entity.Department;
import com.alien.service.DepartmentService;
@Service("departmentServiceImpl")
public class DepartmentServiceImpl extends BaseServiceImpl<Department, String> implements DepartmentService{

	@Resource(name="departmentDaoImpl")
	private DepartmentDao departmentDao;

	@Resource(name="departmentDaoImpl")
	public void setBaseDao(DepartmentDao departmentDao) {
		super.setBaseDao(departmentDao);;
	}
	
	
}
