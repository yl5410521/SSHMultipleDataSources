package com.alien.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alien.dao.ContentDao;
import com.alien.entity.Content;
import com.alien.service.ContentService;

@Service("contentServiceImpl")
public class ContentServiceImpl extends BaseServiceImpl<Content, String>implements ContentService {
	@Resource(name = "contentDaoImpl")
	private ContentDao contentDao;

	@Resource(name = "contentDaoImpl")
	public void setBaseDao(ContentDao contentDao) {
		super.setBaseDao(contentDao);
	}

}
