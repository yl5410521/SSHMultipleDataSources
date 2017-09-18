package com.alien.dao.impl;

import org.springframework.stereotype.Repository;

import com.alien.dao.RoleDao;
import com.alien.entity.Role;
@Repository("roleDaoImpl")
public class RoleDaoImpl extends BaseDaoImpl<Role, String> implements RoleDao{

}
