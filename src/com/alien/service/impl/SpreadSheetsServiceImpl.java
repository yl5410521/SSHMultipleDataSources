package com.alien.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alien.dao.SpreadSheetsDao;
import com.alien.entity.SpreadSheets;
import com.alien.service.SpreadSheetsService;


@Service("spreadSheetsServiceImpl")
public class SpreadSheetsServiceImpl extends BaseServiceImpl<SpreadSheets, String> implements SpreadSheetsService {

	@Resource(name="spreadSheetsDaoImpl")
	private SpreadSheetsDao spreadSheetsDao;
	
	@Resource(name="spreadSheetsDaoImpl")
	public void setBaseDao(SpreadSheetsDao spreadSheetsDao) {
		super.setBaseDao(spreadSheetsDao);
	}


	public boolean createTable(String className) {
		return spreadSheetsDao.createTable(className);
	}


	public int checkTable(String className) {
		
		return spreadSheetsDao.checkTable(className);
	}


	
}
