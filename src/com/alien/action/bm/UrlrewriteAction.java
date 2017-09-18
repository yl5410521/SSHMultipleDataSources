package com.alien.action.bm;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import com.alien.action.BaseAction;
import com.alien.entity.UrlreWrite;


@ParentPackage("bm")
public class UrlrewriteAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2188917733454746668L;

	private String xmlpath = getRealPath("/").replaceAll("\\\\", "/")+ "WEB-INF/urlrewrite.xml";

	private UrlreWrite urlreWrite;

	@SuppressWarnings("rawtypes")
	public String list() throws DocumentException {
		
		System.out.println(xmlpath);
		SAXReader reader = new SAXReader();
		Document document = reader.read(new File(xmlpath));
		Element root = document.getRootElement();

		Iterator it = root.elementIterator();
		while (it.hasNext()) {
			Element element = (Element) it.next();

			// 未知属性名称情况下
			/*
			 * Iterator attrIt = element.attributeIterator(); while
			 * (attrIt.hasNext()) { Attribute a = (Attribute) attrIt.next();
			 * System.out.println(a.getValue()); }
			 */

			// 已知属性名称情况下
			// System.out.println("rule: " + element.elementText("rule"));

			// 未知元素名情况下
			/*
			 * Iterator eleIt = element.elementIterator(); while
			 * (eleIt.hasNext()) { Element e = (Element) eleIt.next();
			 * System.out.println(e.getName() + ": " + e.getText()); }
			 * System.out.println();
			 */

			// 已知元素名情况下
//			System.out.println("note: " + element.elementText("note"));
//			System.out.println("form: " + element.elementText("from"));
//			System.out.println(" to: " + element.elementText("to"));
			// 封装数组
			String notedata = element.elementText("note") + ",";
			String[] note = notedata.split(",");

			String fromdata = element.elementText("from") + ",";
			String[] from = fromdata.split(",");

			String todata = element.elementText("to") + ",";
			String[] to = todata.split(",");
			UrlreWrite write = new UrlreWrite();
			write.setNote(note);
			write.setFrom(from);
			write.setTo(to);

			List<UrlreWrite> urlreWrites = new ArrayList<UrlreWrite>();
			urlreWrites.add(write);
			
			for (UrlreWrite e : urlreWrites) {
				for(int i=0;i<e.getNote().length;i++){
					System.out.println("note"+e.getNote()[i]);
					System.out.println("from"+e.getFrom()[i]);
					System.out.println("to"+e.getTo()[i]);
				}
			}

		}
		return null;
	}
	
	public String save() throws DocumentException, IOException{
		Document document = DocumentHelper.createDocument();  
	     document.setXMLEncoding("utf-8");
	     document.addDocType("urlrewrite","-//tuckey.org//DTD UrlRewrite 3.0//EN", "http://tuckey.org/res/dtds/urlrewrite3.0.dtd");
	    Element root=document.addElement("urlrewrite");
	   
	     for (int i = 0; i < urlreWrite.getNote().length; i++) {
	    	 Element e1 = root.addElement("rule");
	    	 e1.addElement("note").addText(urlreWrite.getNote()[i]);  
	         e1.addElement("from").addText(urlreWrite.getFrom()[i]);  
	         e1.addElement("to").addText(urlreWrite.getTo()[i]).addAttribute("type", "forward");  
	        
	     }
	     FileOutputStream fos = new FileOutputStream(xmlpath);  
	     OutputStreamWriter osw = new OutputStreamWriter(fos,"utf-8");  
	     OutputFormat of = new OutputFormat(); 
	     of.setEncoding("utf-8");  
	     of.setIndent(true);  
	     of.setIndent("    ");  
	     of.setNewlines(true); 
	    
	     XMLWriter writer = new XMLWriter(osw, of);  
	     writer.write(document);  
	     writer.close();  
		return null;
	}

	public UrlreWrite getUrlreWrite() {
		return urlreWrite;
	}

	public void setUrlreWrite(UrlreWrite urlreWrite) {
		this.urlreWrite = urlreWrite;
	}

}
