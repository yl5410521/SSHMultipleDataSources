package com.alien.action.bm;


import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.ParentPackage;

import com.alien.action.BaseAction;
import com.alien.entity.Department;
import com.alien.service.DepartmentService;

@ParentPackage("bm")
public class DepartmentAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1862180128796928618L;

	@Resource(name = "departmentServiceImpl")
	private DepartmentService departmentService;

	private Department department;

	public String list() {
		pager = departmentService.findPager(Department.class, pager, null, parms(parmsList));
		return LIST;
	}

	public String input() {
		return INPUT;
	}

	public String query() {
		department = departmentService.get(department.getId());
		return QUERY;
	}

	public String delete() {
		// departmentService.delete(department.getId());
		// /redirectUrl="bm/department!list.action";
		// return SUCCESS;
		return null;
	}
	
	
	public String add() {
		departmentService.save(department);
		return null;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

}
