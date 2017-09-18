package com.alien.service.impl;


import com.alien.service.MenuService;


import com.alien.dao.MenuDao;


import com.alien.entity.Menu;


import javax.annotation.Resource;


import org.springframework.stereotype.Service;


@Service("menuServiceImpl")
public class MenuServiceImpl extends BaseServiceImpl<Menu, String> implements MenuService {


@Resource(name="menuDaoImpl")
private MenuDao menuDao;


@Resource(name="menuDaoImpl")
public void setBaseDao(MenuDao menuDao) {
super.setBaseDao(menuDao);
}


}

