package com.alien.action.bm;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.ParentPackage;

import com.alien.action.BaseAction;
import com.alien.service.AdminService;

@ParentPackage("bm")
public class TestAction extends BaseAction {

	@Resource(name = "adminServiceImpl")
	private AdminService adminService;

	public String test() {
		String sql = "{call test(?,?)}";
		List<String> list = new ArrayList<String>();
		list.add("哈哈，成功了");
		String msg = adminService.runcallsql(sql, list);

		return ajaxJson(Status.success, msg);
	}
}
