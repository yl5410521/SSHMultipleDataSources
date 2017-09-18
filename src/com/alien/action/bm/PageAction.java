package com.alien.action.bm;

import com.alien.action.BaseAction;

public class PageAction extends BaseAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = -280606388233901011L;

	public String main(){
		return "main";
	}
	
	public String head(){
		return "header";
	}
	
	public String menu(){
		return "menu";
	}
	
	public String index(){
		return "index";
	}
	
	public String footer(){
		return "footer";
	}
}
