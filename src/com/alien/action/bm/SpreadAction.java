package com.alien.action.bm;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.ParentPackage;

import com.alien.action.BaseAction;
import com.alien.entity.Content;
import com.alien.entity.Field;
import com.alien.entity.Permission;
import com.alien.entity.SpreadSheets;
import com.alien.service.ContentService;
import com.alien.service.FieldService;
import com.alien.service.PermissionService;
import com.alien.service.SpreadSheetsService;
import com.alien.util.GenDaoImplUtil;
import com.alien.util.GenDaoUtil;
import com.alien.util.GenEntityUtil;
import com.alien.util.GenServiceImplUtil;
import com.alien.util.GenServiceUtil;

@ParentPackage("bm")
public class SpreadAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6784588656280026842L;

	@Resource(name = "fieldServiceImpl")
	private FieldService fieldService;

	@Resource(name = "spreadSheetsServiceImpl")
	private SpreadSheetsService spreadSheetsService;

	@Resource(name = "contentServiceImpl")
	private ContentService contentService;

	private SpreadSheets spreadSheets;

	private List<Content> contents = new ArrayList<Content>();

	private List<SpreadSheets> sheets = new ArrayList<SpreadSheets>();
	
	private List<Field> fields=new ArrayList<Field>();

	private Field field = new Field();;

	private Content content;// 属性对象

	private String[] contentids; // 属性表id

	private String subordinateTable;// 从表

	private String[] fieldNames;// 字段名

	private String[] name;// 名称

	private String[] fieldlength;// 字段长度
	
	@Resource(name="permissionServiceImpl")
	private PermissionService permissionService;
	
	private Permission permission;

	public String add() {

		try {
			if (spreadSheetsService.checkTable(spreadSheets.getClassName()) > 0) {
				return ajax("表名重复,生成失败");
			} else {
				// 添加实体类名并生成表
				spreadSheetsService.createTable(spreadSheets.getClassName());
				String className = spreadSheets.getClassName().substring(0, 1).toUpperCase()
						+ spreadSheets.getClassName().substring(1);
				System.out.println(className);
				spreadSheets.setClassName(className.trim().replace(" ", ""));
				spreadSheets.setTableName("t_" + spreadSheets.getClassName().toLowerCase());
				String sid = spreadSheetsService.save(spreadSheets);
				// 保存字段到字段表
				for (int i = 0; i < contentids.length; i++) {
					content = contentService.get(contentids[i]);
					field.setFieldcontent(content.getContentType());
					field.setFieldtype(content.getFieldsType());
					field.setFieldName(fieldNames[i].toLowerCase());
					field.getSpreadSheets().setId(sid);
					field.setName(name[i]);
					field.setFieldLength(fieldlength[i].trim().replace(" ", ""));
					fieldService.savefield(field);
				}

				List<Field> fields = fieldService.fieldslist(sid);
				// 给表添加字段
				fieldService.addField("t_" + spreadSheets.getClassName().toLowerCase(), fields);
				fieldService.addField(sid);
				//生成实体类
				//new GenEntityUtil(spreadSheets.getClassName());
				//生成dao层
				new GenDaoUtil(spreadSheets.getClassName());
				//生成dao实现层impl
				new GenDaoImplUtil(spreadSheets.getClassName());
				//生成service层
				new GenServiceUtil(spreadSheets.getClassName());
				//生成service层Impl
				new GenServiceImplUtil(spreadSheets.getClassName());
				// 添加主外键关系
				if (subordinateTable != null && subordinateTable != "") {
					fieldService.createforeignkey("t_" + spreadSheets.getClassName().toLowerCase(), subordinateTable);
				
				} else {
					System.out.println("创建主外键失败");
				}
				return ajax("生成数据表成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ajax("生成数据表成功");

	}

	public String input() {
		sheets = spreadSheetsService.getAllList();
		contents = contentService.getAllList();
		return "input";
	}
	
	public String query(){
		spreadSheets=spreadSheetsService.get(spreadSheets.getId());
		fields=fieldService.fieldslist(spreadSheets.getId());
		return "query";
	}

	public String list() {
		sheets = spreadSheetsService.getAllList();
		return LIST;
	}
	
	public void delete(){
		System.out.println("-----------"+spreadSheets.getId());
		spreadSheetsService.delete(spreadSheets.getId());
	
	}

	public SpreadSheets getSpreadSheets() {
		return spreadSheets;
	}

	public void setSpreadSheets(SpreadSheets spreadSheets) {
		this.spreadSheets = spreadSheets;
	}

	public Field getField() {
		return field;
	}

	public void setField(Field field) {
		this.field = field;
	}

	public List<Content> getContents() {
		return contents;
	}

	public void setContents(List<Content> contents) {
		this.contents = contents;
	}

	public Content getContent() {
		return content;
	}

	public void setContent(Content content) {
		this.content = content;
	}

	public String getSubordinateTable() {
		return subordinateTable;
	}

	public void setSubordinateTable(String subordinateTable) {
		this.subordinateTable = subordinateTable;
	}

	public List<SpreadSheets> getSheets() {
		return sheets;
	}

	public void setSheets(List<SpreadSheets> sheets) {
		this.sheets = sheets;
	}

	public String[] getContentids() {
		return contentids;
	}

	public void setContentids(String[] contentids) {
		this.contentids = contentids;
	}

	public String[] getFieldNames() {
		return fieldNames;
	}

	public void setFieldNames(String[] fieldNames) {
		this.fieldNames = fieldNames;
	}

	public String[] getName() {
		return name;
	}

	public void setName(String[] name) {
		this.name = name;
	}
	
	
	
	

	public List<Field> getFields() {
		return fields;
	}

	public void setFields(List<Field> fields) {
		this.fields = fields;
	}

	public String[] getFieldlength() {
		return fieldlength;
	}

	public void setFieldlength(String[] fieldlength) {
		this.fieldlength = fieldlength;
	}

	public Permission getPermission() {
		return permission;
	}

	public void setPermission(Permission permission) {
		this.permission = permission;
	}
	
	

}
