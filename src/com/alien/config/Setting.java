package com.alien.config;
 
import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

import com.alien.util.CommonUtil; 


/**
 * Bean类 - 系统设置
 * ============================================================================
 * ============================================================================
 */

public class Setting implements Serializable{

	private static final long serialVersionUID = -3718508291043694321L;
	
	 
	
	// 水印位置(无、左上、右上、居中、左下、右下)
	public enum WatermarkPosition {
		no, topLeft, topRight, center, bottomLeft, bottomRight
	}
	
	 
	 

	private String systemName;// 系统名称
	private String systemVersion;// 系统版本
	private String systemDescription;// 系统描述
	private String contextPath;// 虚拟路径
	private String imageUploadPath;// 图片上传路径
	private String imageBrowsePath;// 图片浏览路径 
 
	
	public String getSystemName() {
		return systemName;
	}

	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}

	public String getSystemVersion() {
		return systemVersion;
	}

	public void setSystemVersion(String systemVersion) {
		this.systemVersion = systemVersion;
	}

	public String getSystemDescription() {
		return systemDescription;
	}

	public void setSystemDescription(String systemDescription) {
		this.systemDescription = systemDescription;
	}

	public String getContextPath() {
		return contextPath;
	}

	public void setContextPath(String contextPath) {
		this.contextPath = contextPath;
	}

	public String getImageUploadPath() {
		return imageUploadPath;
	}

	public void setImageUploadPath(String imageUploadPath) {
		this.imageUploadPath = StringUtils.removeEnd(imageUploadPath, "/");
	}
	
	public String getImageBrowsePath() {
		return imageBrowsePath;
	}

	public void setImageBrowsePath(String imageBrowsePath) {
		this.imageBrowsePath = StringUtils.removeEnd(imageBrowsePath, "/");
	}

	  
	
	// 获取图片上传真实路径
	public String getImageUploadRealPath() {  
		return CommonUtil.getPreparedStatementPath(CommonUtil.getXmlValue());
	}
	 
	
	/**
	 * 获取推荐位图真实路径
	 * @return
	 */
	public String getImageBrowseRealPath(){
		return CommonUtil.getPreparedStatementPath(imageBrowsePath);
	}
 

}