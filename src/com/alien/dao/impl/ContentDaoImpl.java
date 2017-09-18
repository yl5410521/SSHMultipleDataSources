package com.alien.dao.impl;

import org.springframework.stereotype.Repository;

import com.alien.dao.ContentDao;
import com.alien.entity.Content;

@Repository("contentDaoImpl")
public class ContentDaoImpl extends BaseDaoImpl<Content, String> implements ContentDao{

}
