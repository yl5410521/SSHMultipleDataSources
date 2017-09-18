package com.alien.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.Timestamp;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class FileUtil {

	/**
	 * 将文件转成base64 字符串
	 * 
	 * @param path文件路径
	 * @return *
	 * @throws Exception
	 */
	
	public final static String basedir = getWebRoot() + "upload/";

	public static String encodeBase64File(String path) throws Exception {
		File file = new File(path);
		;
		FileInputStream inputFile = new FileInputStream(file);
		byte[] buffer = new byte[(int) file.length()];
		inputFile.read(buffer);
		inputFile.close();
		return new BASE64Encoder().encode(buffer);

	}

	/**
	 * 将base64字符解码保存文件
	 * 
	 * @param base64Code
	 * @param targetPath
	 * @throws Exception
	 */

//	public static void decoderBase64File(String base64Code, String targetPath) throws Exception {
//		byte[] buffer = new BASE64Decoder().decodeBuffer(base64Code);
//		FileOutputStream out = new FileOutputStream("basedir"+targetPath);
//		out.write(buffer);
//		out.close();
//		byte[] buffer =Base64Coder.decode(base64Code);
//		System.out.println(buffer);
//		FileOutputStream out = new FileOutputStream("basedir"+targetPath);
//		out.write(buffer);
//		out.close();
//
//	}

	/**
	 * 将base64字符保存文本文件
	 * 
	 * @param base64Code
	 * @param targetPath
	 * @throws Exception
	 */

//	public static void toFile(String base64Code, String targetPath) throws Exception {
//
//		byte[] buffer = base64Code.getBytes();
//		FileOutputStream out = new FileOutputStream("basedir"+targetPath);
//		out.write(buffer);
//		out.close();
//	}
	/**
	 * 获取路径
	 * @return
	 */
	private static String getWebRoot() {
		StringBuffer classPathBuff = new StringBuffer(FileUtil.class
				.getResource("/").getPath());
		// 替换路径中可能存在的空格字符
		int index = classPathBuff.indexOf("%20");
		while (index > -1) {
			classPathBuff.replace(index, index + 3, " ");
			index = classPathBuff.indexOf("%20");
		}
		String path = classPathBuff.substring(1, classPathBuff.length() - 16);
		return path;
	}
/**
 * base64转图片
 * @param imgStr
 * @return
 */
	
	 public static String GenerateImage(String base64Code,String targetPath,String imgtype)  
	    {   //对字节数组字符串进行Base64解码并生成图片  
		 System.out.println("***************************——————————————————————");
	        if (base64Code == null) //图像数据为空  
	            return null;  
	        BASE64Decoder decoder = new BASE64Decoder();  
	        try   
	        {  
	            //Base64解码  
	            byte[] buffer = decoder.decodeBuffer(base64Code);  
	            for(int i=0;i<buffer.length;++i)  
	            {  
	                if(buffer[i]<0)  
	                {//调整异常数据  
	                	buffer[i]+=256;  
	                }  
	            }  
	            //生成jpeg图片
	           StringBuffer imgpath = new StringBuffer(DateBean.toString(new Timestamp(
	    				System.currentTimeMillis()), "yyyyMMddHHmmssSSS")+imgtype);
	           System.out.println(basedir+targetPath+imgpath);
	            FileOutputStream out = new FileOutputStream(basedir+targetPath+imgpath);
	                  
	            out.write(buffer);  
	            out.flush();  
	            out.close();  
	            return targetPath+imgpath;  
	        }   
	        catch (Exception e)   
	        {  
	            return null;  
	        }
	    }  
	 
	 
	public static void main(String[] args) {
		try {
			
			 String dir=System.getProperty("user.dir");
			 System.out.println(dir);
			
//			String base64Code = encodeBase64File("D:/0101-2011-qqqq.tif");
//			System.out.println(base64Code);
//			decoderBase64File(base64Code, "D:/2.tif");
//			toFile(base64Code, "D:\\three.txt");
		} catch (Exception e) {
			e.printStackTrace();

		}

	}

}
