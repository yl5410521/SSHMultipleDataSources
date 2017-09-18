package com.alien.action.bm;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.ParentPackage;

import com.alien.action.BaseAction;
import com.alien.entity.Field;
import com.alien.service.FieldService;


@ParentPackage("bm")
public class FieldAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8275505116619292059L;

	@Resource(name="fieldServiceImpl")
	private FieldService fieldService;
	
	private List<Field> fields=new ArrayList<Field>();
	
	
	
	public String list(){
		
		fields=fieldService.getAllList();
		
		return LIST;
	}



	public List<Field> getFields() {
		return fields;
	}



	public void setFields(List<Field> fields) {
		this.fields = fields;
	}
	
	
}
