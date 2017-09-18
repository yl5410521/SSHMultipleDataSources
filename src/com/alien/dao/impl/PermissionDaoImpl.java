package com.alien.dao.impl;


import org.springframework.stereotype.Repository;

import com.alien.dao.PermissionDao;
import com.alien.entity.Permission;
@Repository("permissionDaoImpl")
public class PermissionDaoImpl extends BaseDaoImpl<Permission, String> implements PermissionDao{

}
