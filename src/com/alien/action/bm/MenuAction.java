package com.alien.action.bm;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.ParentPackage;

import com.alien.action.BaseAction;
import com.alien.entity.Menu;
import com.alien.service.MenuService;

@ParentPackage("bm")
public class MenuAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7896195354597720998L;
	
	@Resource(name="menuServiceImpl")
	private MenuService menuService;
	
	public String list(){
		pager=menuService.findPager(Menu.class, pager, null, parms(parmsList));
		return LIST;
	}

}
