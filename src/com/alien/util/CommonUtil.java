package com.alien.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.core.io.ClassPathResource;

/**
 * 工具类 - 公用
 * ============================================================================
 * ============================================================================
 */

public class CommonUtil {
	
	public static final String WEB_APP_ROOT_KEY = "cec.webAppRoot";// WebRoot路径KEY
	public static final String PATH_PREPARED_STATEMENT_UUID = "\\{uuid\\}";// UUID路径占位符
	public static final String PATH_PREPARED_STATEMENT_T8UUID = "\\{T8uuid\\}";// T8UUID路径占位符
	public static final String PATH_PREPARED_STATEMENT_DATE = "\\{date(\\(\\w+\\))?\\}";// 日期路径占位符
	
	/**
	 * 获取WebRoot路径
	 * 
	 * @return WebRoot路径
	 */
	public static String getWebRootPath() {
		return System.getProperty(WEB_APP_ROOT_KEY);
	}

	/**
	 * 随机获取UUID字符串(无中划线)
	 * 
	 * @return UUID字符串
	 */
	public static String getUUID() {
		String uuid = UUID.randomUUID().toString();
		return uuid.substring(0, 8) + uuid.substring(9, 13) + uuid.substring(14, 18) + uuid.substring(19, 23) + uuid.substring(24);
	}
	

	public static String getT8UUID() {
		T8UUIDGenerator g = new T8UUIDGenerator();
		return g.generate(null, null).toString();		
	}
	/**
	 * 获取实际路径
	 * 
	 * @param path
	 *            路径
	 */
	public static String getPreparedStatementPath(String path) {
		if (StringUtils.isEmpty(path)) {
			return null;
		}
		StringBuffer uuidStringBuffer = new StringBuffer();
		Matcher uuidMatcher = Pattern.compile(PATH_PREPARED_STATEMENT_UUID).matcher(path);
		while(uuidMatcher.find()) {
			uuidMatcher.appendReplacement(uuidStringBuffer, CommonUtil.getUUID());
		}
		uuidMatcher.appendTail(uuidStringBuffer);

		StringBuffer t8uuidStringBuffer = new StringBuffer();
		Matcher t8uuidMatcher = Pattern.compile(PATH_PREPARED_STATEMENT_T8UUID).matcher(uuidStringBuffer.toString());
		while(t8uuidMatcher.find()) {
			t8uuidMatcher.appendReplacement(t8uuidStringBuffer, CommonUtil.getT8UUID());
		}
		t8uuidMatcher.appendTail(t8uuidStringBuffer);
		
		
		StringBuffer dateStringBuffer = new StringBuffer();
		Matcher dateMatcher = Pattern.compile(PATH_PREPARED_STATEMENT_DATE).matcher(t8uuidStringBuffer.toString());
		while(dateMatcher.find()) {
			String dateFormate = "yyyyMM";
			Matcher dateFormatMatcher = Pattern.compile("\\(\\w+\\)").matcher(dateMatcher.group());
			if (dateFormatMatcher.find()) {
				String dateFormatMatcherGroup = dateFormatMatcher.group();
				dateFormate = dateFormatMatcherGroup.substring(1, dateFormatMatcherGroup.length() - 1);
			}
			dateMatcher.appendReplacement(dateStringBuffer, new SimpleDateFormat(dateFormate).format(new Date()));
		}
		dateMatcher.appendTail(dateStringBuffer);
		
		return dateStringBuffer.toString();
	}

    public static String getXmlValue(){ 
    	String value = null;
		try {
			File f = new ClassPathResource("settings.xml").getFile();
	    	SAXReader reader = new SAXReader();  
	    	Document doc;
			doc = reader.read(f);
			Element root = doc.getRootElement(); 
	    	Element foo; 
	    	for (Iterator i = root.elementIterator("setting"); i.hasNext();) { 
	    	foo = (Element) i.next();  
	    	value = foo.elementText("imageUploadPath");
	    	}
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch(IOException io){
			io.printStackTrace();
		}
		return value;
    }
}