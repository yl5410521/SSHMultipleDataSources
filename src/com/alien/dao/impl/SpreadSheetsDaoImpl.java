package com.alien.dao.impl;


import org.springframework.stereotype.Repository;

import com.alien.dao.SpreadSheetsDao;
import com.alien.entity.SpreadSheets;


@Repository("spreadSheetsDaoImpl")
public class SpreadSheetsDaoImpl extends BaseDaoImpl<SpreadSheets, String>implements SpreadSheetsDao {
	/**
	 * 创建表
	 */
	public boolean createTable(String className) {
		boolean flag = false;
		StringBuffer sb = new StringBuffer("");
		sb.append("create table t_" + className + "(");
		sb.append("id varchar(32) not null,");
		sb.append("create_date datetime,");
		sb.append("modify_date datetime,");
		sb.append("primary key (id))");
		sb.append("ENGINE=InnoDB default charset=utf8 collate=utf8_unicode_ci;");
		try {

			getSession().createSQLQuery(sb.toString()).executeUpdate();
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	public int checkTable(String className) {
		System.out.println("----------------" + className);
		int count = 1;
		String hql = "from SpreadSheets s where s.className=?";

		String classNames = className.substring(0, 1).toUpperCase() + className.substring(1);
		int countNo1 = getSession().createQuery(hql).setString(0, classNames.trim().replace(" ", "")).list().size();
		int countNo2 = getSession().createQuery(hql).setString(0, classNames.trim().replace(" ", "")).list().size();
		if (countNo1 == 0 && countNo2 == 0) {
			count = 0;
		} else if (countNo1 > 0 || countNo2 > 0) {
			count = 1;
		}
		return count;
	}

	
}
