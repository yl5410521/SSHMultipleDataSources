package com.alien.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 工具类 —— 文件
 */
public class UploadManager {
	public final static String basedir = getWebRoot() + "upload/";// windows

	// final static String basedir="/home/fansImg/";//linux

	/*
	 * 上传文件封装的方法
	 */
	public UploadManager() {
	}

	/**
	 * 上传文件
	 * 
	 * @param fileName
	 * @param file
	 * @param folderName
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public String uploadFile(final String fileName, final File file, final String folderName)
			throws FileNotFoundException, IOException {
		StringBuffer sb = new StringBuffer(
				DateBean.toString(new Timestamp(System.currentTimeMillis()), "yyyyMMddHHmmssSSS")
						+ fileName.substring(fileName.lastIndexOf(".")));
		new File(basedir + folderName).mkdirs();
		FileOutputStream out = new FileOutputStream(basedir + folderName + "/" + sb.toString());
		FileInputStream in = new FileInputStream(file);
		byte[] buf = new byte[1024];
		int len = 0;
		while ((len = in.read(buf)) != -1) {
			out.write(buf, 0, len);
		}
		in.close();
		out.close();
		return sb.toString();
	}

	/**
	 * EXCEL 导出到服务器
	 * 
	 * @param fileName
	 * @param exp
	 * @param folderName
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public String uploadFileExcel(final String fileName, ExportExcelUtil exp, final String folderName)
			throws FileNotFoundException, IOException {
		new File(basedir + folderName).mkdirs();
		StringBuffer buff = new StringBuffer(basedir).append(folderName).append(fileName).append(".xls");
		FileOutputStream fout = new FileOutputStream(buff.toString());
		exp.getWb().write(fout);
		fout.flush();
		fout.close();
		return buff.toString();
	}

	public String saveFile(final String phoneNums, final String folderName) throws FileNotFoundException, IOException {
		StringBuffer sb = new StringBuffer(
				DateBean.toString(new Timestamp(System.currentTimeMillis()), "yyyyMMddHHmmssSSS") + ".txt");
		new File(basedir + folderName).mkdirs();
		FileOutputStream out = new FileOutputStream(basedir + folderName + "/" + sb.toString());
		;
		String[] phoneNum = phoneNums.split("\n");
		for (String telNum : phoneNum) {
			out.write(telNum.getBytes());
			out.write("\r\n".getBytes());
		}
		out.close();
		return sb.substring(0, sb.indexOf("."));
	}

	/**
	 * 按行读取文件
	 * 
	 * @param fileName
	 * @param folderName
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public List<String> getUploadFile(final String fileName, final String folderName)
			throws FileNotFoundException, IOException {
		List<String> list = new ArrayList<String>();
		File file = new File(basedir + folderName + "/" + fileName);
		if (file.isFile() && file.exists()) {
			InputStreamReader read = new InputStreamReader(new FileInputStream(file));
			BufferedReader bufferedReader = new BufferedReader(read);
			String lineTXT = null;
			while ((lineTXT = bufferedReader.readLine()) != null) {
				list.add(lineTXT.toString().trim());
			}
			read.close();
		} else {
			System.out.println("找不到指定的文件！");
		}
		return list;
	}

	/**
	 * 文件下载
	 * 
	 * @param path
	 *            路径
	 * @param response
	 * @param request
	 * @return
	 */
	public HttpServletResponse download(String path, HttpServletResponse response, HttpServletRequest request) {

		try {
			path = basedir + "count/poll/" + path;
			File file = new File(path);
			String filename = file.getName();
			String ext = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();

			InputStream fis = new BufferedInputStream(new FileInputStream(path));
			byte[] buffer = new byte[fis.available()];
			fis.read(buffer);
			fis.close();
			response.reset();
			if (request.getHeader("User-Agent").toLowerCase().indexOf("firefox") > 0) {
				response.addHeader("Content-Disposition",
						"attachment;filename=" + new String(filename.getBytes("UTF-8"), "ISO8859-1"));
			} else {
				response.addHeader("Content-Disposition",
						"attachment;filename=" + URLEncoder.encode(filename, "UTF-8"));
			}

			response.addHeader("Content-Length", "" + file.length());
			OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
			response.setContentType("application/octet-stream");
			toClient.write(buffer);
			toClient.flush();
			toClient.close();
		} catch (Exception e) {
			return null;
		}
		return response;
	}

	/**
	 * 文件下载
	 * 
	 * @param folderName
	 *            文件路径 相对于basedir的路径
	 * @param path
	 *            路径
	 * @param response
	 * @param request
	 * @return
	 */
	public HttpServletResponse download(String folderName, String path, HttpServletResponse response,
			HttpServletRequest request) {

		try {
			path = basedir + folderName + path;
			File file = new File(path);
			String filename = file.getName();
			InputStream fis = new BufferedInputStream(new FileInputStream(path));
			byte[] buffer = new byte[fis.available()];
			fis.read(buffer);
			fis.close();
			response.reset();
			if (request.getHeader("User-Agent").toLowerCase().indexOf("firefox") > 0) {
				response.addHeader("Content-Disposition",
						"attachment;filename=" + new String(filename.getBytes("UTF-8"), "ISO8859-1"));
			} else {
				response.addHeader("Content-Disposition",
						"attachment;filename=" + URLEncoder.encode(filename, "UTF-8"));
			}

			response.addHeader("Content-Length", "" + file.length());
			OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
			response.setContentType("application/octet-stream");
			toClient.write(buffer);
			toClient.flush();
			toClient.close();
		} catch (Exception e) {
			return null;
		}
		return response;
	}

	private static String getWebRoot() {
		StringBuffer classPathBuff = new StringBuffer(UploadManager.class.getResource("/").getPath());
		// 替换路径中可能存在的空格字符
		int index = classPathBuff.indexOf("%20");
		while (index > -1) {
			classPathBuff.replace(index, index + 3, " ");
			index = classPathBuff.indexOf("%20");
		}
		String path = classPathBuff.substring(1, classPathBuff.length() - 16);
		return path;
	}
}
