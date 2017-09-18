package com.alien.util;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.springframework.core.io.ClassPathResource;

import com.alien.config.Setting;
import com.opensymphony.oscache.general.GeneralCacheAdministrator;

/**
 * 工具类 - 系统配置
 * ============================================================================
 * ============================================================================
 */

public class SettingUtil {
	
	public static final String CACHE_MANAGER_BEAN_NAME = "cacheManager";// cacheManager Bean名称
	public static final String CACHE_CLIENT_BEAN_NAME = "memcachedClient";// cacheManager Bean名称
	public static final String SETTINGS_XML_FILE_NAME = "settings.xml";// 系统配置文件名称
	public static final String SETTING_CACHE_KEY = "SYSTEM_SETTING";// Setting缓存Key
	
	public static final String MAIN_SHOP_ID = "0000000000000000";//主店id
	
	/**
	 * 读取系统配置信息
	 * 
	 * @return Setting
	 * 
	 * @throws URISyntaxException 
	 * 
	 * @throws DocumentException 
	 * 
	 * @throws IOException 
	 */
	public static Setting readSetting() throws URISyntaxException, DocumentException, IOException {
		File XmlFile = new ClassPathResource(SETTINGS_XML_FILE_NAME).getFile();
		SAXReader saxReader = new SAXReader();
		Document document = saxReader.read(XmlFile);
		Node systemNameNode = document.selectSingleNode("/cec/setting/systemName");
		Node systemVersionNode = document.selectSingleNode("/cec/setting/systemVersion");
		Node systemDescriptionNode = document.selectSingleNode("/cec/setting/systemDescription");
		Node contextPathNode = document.selectSingleNode("/cec/setting/contextPath");
		Node imageUploadPathNode = document.selectSingleNode("/cec/setting/imageUploadPath");
		Node imageBrowsePathNode = document.selectSingleNode("/cec/setting/imageBrowsePath");
	 
		Setting setting = new Setting();
		setting.setSystemName(systemNameNode.getText());
		setting.setSystemVersion(systemVersionNode.getText());
		setting.setSystemDescription(systemDescriptionNode.getText());
		setting.setContextPath(contextPathNode.getText());
		setting.setImageUploadPath(imageUploadPathNode.getText());
		setting.setImageBrowsePath(imageBrowsePathNode.getText()); 
		 
		return setting;
	}
	
	/**
	 * 获取系统配置信息
	 * 
	 * @return Setting
	 */
	public static Setting getSetting() {
		Setting setting = new Setting(); 
//		Setting setting = null;
//		GeneralCacheAdministrator generalCacheAdministrator = (GeneralCacheAdministrator) SpringUtil.getBean(CACHE_MANAGER_BEAN_NAME);
//		System.out.println(222);
//		try {
//			setting = (Setting) generalCacheAdministrator.getFromCache(SETTING_CACHE_KEY);
//		} catch (NeedsRefreshException needsRefreshException) {
//			boolean updateSucceeded = false;
//			try {
//				setting = readSetting();
//				generalCacheAdministrator.putInCache(SETTING_CACHE_KEY, setting);
//				updateSucceeded = true;
//			} catch(Exception e) {
//				e.printStackTrace();
//			} finally {
//	            if (!updateSucceeded) {
//	            	generalCacheAdministrator.cancelUpdate(SETTING_CACHE_KEY);
//	            }
//	        }
//		}
		return setting;
	}
	
	/**
	 * 写入系统配置信息
	 * 
	 * @return Setting
	 */
//	public static void writeSetting(Setting setting) {
//		File XmlFile = null;
//		Document document = null;
//		try {
//			XmlFile = new ClassPathResource(SETTINGS_XML_FILE_NAME).getFile();
//			SAXReader saxReader = new SAXReader();
//			document = saxReader.read(XmlFile);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		Element rootElement = document.getRootElement();
//		Element settingElement = rootElement.element("setting");
//		 
//	}
	
//	/**
//	 * 更新系统配置信息
//	 * 
//	 * @param setting
//	 */
//	public static void updateSetting(Setting setting) {
//		writeSetting(setting);
//		GeneralCacheAdministrator generalCacheAdministrator = (GeneralCacheAdministrator) SpringUtil.getBean(CACHE_MANAGER_BEAN_NAME);
//		generalCacheAdministrator.flushEntry(SETTING_CACHE_KEY);
//	}
	
	/**
	 * 刷新系统配置信息
	 * 
	 */
	public void flush() {
		GeneralCacheAdministrator generalCacheAdministrator = (GeneralCacheAdministrator) SpringUtil.getBean(CACHE_MANAGER_BEAN_NAME);
		generalCacheAdministrator.flushEntry(SETTING_CACHE_KEY);
	}
	 
	 
}