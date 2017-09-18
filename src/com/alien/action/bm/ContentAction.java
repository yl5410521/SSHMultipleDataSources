package com.alien.action.bm;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.ParentPackage;

import com.alien.action.BaseAction;
import com.alien.entity.Content;
import com.alien.service.ContentService;


@ParentPackage("bm")
public class ContentAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1546616190866371006L;
	
	@Resource(name="contentServiceImpl")
	private ContentService contentService;
	
	private Content content;
	
	private List<Content> contents=new ArrayList<Content>();

	public String add(){
		contentService.save(content);
		return success("添加成功");
	}
	
	public String input(){
		return INPUT;
	}
	
	public String list(){
		contents=contentService.getAllList();
		return LIST;
	}
	
	public String delete(){
		contentService.delete(content.getId());
		return success("删除成功", navTabId, "", forwardUrl);
	}

	public Content getContent() {
		return content;
	}

	public void setContent(Content content) {
		this.content = content;
	}

	public List<Content> getContents() {
		return contents;
	}

	public void setContents(List<Content> contents) {
		this.contents = contents;
	}
	
	
	
	
	
}
