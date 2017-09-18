package com.alien.action.chat;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.ParentPackage;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alien.action.BaseAction;
import com.alien.entity.Admin;
import com.alien.service.AdminService;

@ParentPackage("chat")
public class TestAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5532610643039816154L;
	
	@Resource(name = "adminServiceImpl")
	private AdminService adminService;
	
	private Admin admin;
	
	public String list(){
		List<Admin> admins=adminService.getAllList();
		JSONArray json=new JSONArray();
		for(int i=0;i<5;i++){
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("1", 1);
		map.put("2", 2);
		json.add(map);
		}
		return toJson(json,true);
	}

}
