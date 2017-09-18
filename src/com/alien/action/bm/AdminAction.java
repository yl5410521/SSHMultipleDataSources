package com.alien.action.bm;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.ParentPackage;

import com.alien.action.BaseAction;
import com.alien.entity.Admin;
import com.alien.entity.Department;
import com.alien.entity.Role;
import com.alien.service.AdminService;
import com.alien.service.DepartmentService;
import com.alien.service.RoleService;

@ParentPackage("bm")
public class AdminAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1304178174092178415L;
	
	private File myExcel;
	
	private String myExcelFileName;

	@Resource(name = "adminServiceImpl")
	private AdminService adminService;
	
	@Resource(name="roleServiceImpl")
	private RoleService roleService;
	
	@Resource(name="departmentServiceImpl")
	private DepartmentService departmentService;

	private Admin admin;
	
	private List<Role> roles=new ArrayList<Role>();
	
	private List<Department> departments=new ArrayList<Department>();
	

	public String login() {
		System.out.println(admin.getPassword()+admin.getUserName());
		if(admin.getUserName()==null&&admin.getPassword().isEmpty()){
			return ajaxJson(Status.error, "用户名不能为空");	
		}else if(admin.getPassword()==null&&admin.getPassword().isEmpty()){
			return ajaxJson(Status.error, "密码不能为空");	
		}else{
			
			Admin admins=adminService.adminQuery(admin.getUserName().toLowerCase().trim());
			if(admins==null){
				return ajaxJson(Status.error, "用户名不存在");	
			}else{
				if(admin.getUserName().equals(admins.getUserName())&&admin.getPassword().equals(admins.getPassword())){
					return ajaxJson(Status.success, "bm/index.html");
				}else{
					System.out.println(admins.getPassword()+admins.getUserName());
					return ajaxJson(Status.error, "登录失败,密码错误");	
				}	
				
			}
					
		}
	}
	

	public String query() {
		admin=adminService.get(admin.getId());
		roles=roleService.getAllList();
		departments=departmentService.getAllList();
		return QUERY;
	}
	
	public String list(){
		pager=adminService.findPager(Admin.class, pager, null, parms(parmsList));
		return LIST;
	}
	
	public String  add(){
		adminService.save(admin);
		return success("添加成功");
	}
	
	public String importexcel(){
		System.out.println(myExcel);
		System.out.println(myExcelFileName);
		String sql="insert into t_admin (id,create_date,modify_date,name,mobile,userName) values (?,?,?,?,?,?)";
		adminService.ImportExcel(myExcel, myExcelFileName, sql);
		return null;
	}
	
	public String test(){
		/**
		 * 实体对象转json
		 */
//		String id="1443b4290h554j94";
//		admin=adminService.get(id);
	return ajax(admin);
	}
	
	public String input(){
		roles=roleService.getAllList();
		departments=departmentService.getAllList();
		return INPUT;
	}
	
	public String delete(){
		adminService.delete(admin.getId());
		return success("删除成功");
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}


	public List<Role> getRoles() {
		return roles;
	}


	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}


	public List<Department> getDepartments() {
		return departments;
	}


	public void setDepartments(List<Department> departments) {
		this.departments = departments;
	}


	public File getMyExcel() {
		return myExcel;
	}


	public void setMyExcel(File myExcel) {
		this.myExcel = myExcel;
	}


	public String getMyExcelFileName() {
		return myExcelFileName;
	}


	public void setMyExcelFileName(String myExcelFileName) {
		this.myExcelFileName = myExcelFileName;
	}
	
	
	
	

}
