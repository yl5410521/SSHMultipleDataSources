package com.alien.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alien.dao.FieldDao;
import com.alien.entity.Field;
import com.alien.service.FieldService;


@Service("fieldServiceImpl")
public class FieldServiceImpl extends BaseServiceImpl<Field, String>implements FieldService {
	@Resource(name = "fieldDaoImpl")
	private FieldDao fieldDao;

	@Resource(name = "fieldDaoImpl")
	public void setBaseDao(FieldDao fieldDao) {
		super.setBaseDao(fieldDao);
		;
	}

	public boolean addField(String tableName, List<Field> fields) {
		
		return fieldDao.addField(tableName, fields);
	}

	public List<Field> fieldslist(String sheetsid) {
		
		return fieldDao.fieldslist(sheetsid);
	}

	public boolean checkfieldName(String sheetsid, String fieldName) {
		
		return fieldDao.checkfieldName(sheetsid, fieldName);
	}

	public boolean createforeignkey(String masterTabile, String subordinateTable) {
		
		return fieldDao.createforeignkey(masterTabile, subordinateTable);
	}

	public void savefield(Field field) {
		fieldDao.savefield(field);
		
	}
	public void addField(String sid) {
		fieldDao.addField(sid);	
	}


}
