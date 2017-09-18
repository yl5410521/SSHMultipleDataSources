package com.alien.dao.impl;

import org.springframework.stereotype.Repository;

import com.alien.dao.DepartmentDao;
import com.alien.entity.Department;
@Repository("departmentDaoImpl")
public class DepartmentDaoImpl extends BaseDaoImpl<Department, String> implements DepartmentDao{

}
