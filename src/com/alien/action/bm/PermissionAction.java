package com.alien.action.bm;

import org.apache.struts2.convention.annotation.ParentPackage;

import com.alien.action.BaseAction;
@ParentPackage("bm")
public class PermissionAction extends BaseAction{
	
	public String list(){
		return LIST;
	}

}
