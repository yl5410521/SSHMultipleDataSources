package com.alien.action.bm;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.ParentPackage;

import com.alien.action.BaseAction;
import com.alien.entity.Role;
import com.alien.service.RoleService;
@ParentPackage("bm")
public class RoleAction extends BaseAction {
	
	@Resource(name="roleServiceImpl")
	private RoleService roleService;
	
	private  Role role;

	public String list(){
		pager=roleService.findPager(Role.class, pager, null, parms(parmsList));
		return LIST;
	}
	
	public String query(){
		return QUERY;
	}
	
	public String input(){
		return INPUT;
	}
	public String add(){
		roleService.save(role);
		return null;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	
}
