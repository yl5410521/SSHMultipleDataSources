package com.alien.util;

import java.io.File;
import java.io.IOException;

public class PathUtil {
	/**
	 * 获取路径
	 */
	public static String getCurrentPath() {
		// 取得根目录路径
		File file = new File(".\\src");
		String parentPath = null;
		try {
			parentPath = file.getCanonicalPath()+"\\com\\alien\\";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return parentPath;

	}
	
}
