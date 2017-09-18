package com.alien.dao.impl;


import com.alien.dao.MenuDao;


import com.alien.entity.Menu;


import org.springframework.stereotype.Repository;


@Repository("menuDaoImpl")
public class MenuDaoImpl extends BaseDaoImpl<Menu, String> implements MenuDao {
}

