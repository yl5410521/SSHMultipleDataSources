package com.alien.dao.impl;


import com.alien.common.DynamicDataSourceGlobal;
import com.alien.common.DynamicDataSourceHolder;
import com.alien.dao.AdminDao;


import com.alien.entity.Admin;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;


@Repository("adminDaoImpl")
public class AdminDaoImpl extends BaseDaoImpl<Admin, String> implements AdminDao {
	@SuppressWarnings("unchecked")
	public boolean login(Admin admin) {
		DynamicDataSourceHolder.setDataSourceType(DynamicDataSourceGlobal.CCSC);
		boolean flag=false;
		String hql="from Admin a where a.userName=? and a.password=?";
		try {
			Query query=getSession().createQuery(hql);
			query.setString(0, admin.getUserName());
			query.setString(1,admin.getPassword());
			List<Admin> admins=query.list();
			if(admins!=null&&admins.size()==1){
				flag=true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return flag;
	}

	@SuppressWarnings("unchecked")
	public Admin adminQuery(String name) {
		DynamicDataSourceHolder.setDataSourceType(DynamicDataSourceGlobal.CCSC);
		String hql="from Admin a where a.userName=?";
		Admin admin=null;
		try {
			Query query=getSession().createQuery(hql);
			query.setString(0, name);
			List<Admin> admins=(List<Admin>) query.list();
			if(admins.size()==1){
				admin=admins.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return admin;
	}

}

