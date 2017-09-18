package com.alien.util;

/**
 * 自动生成实体类
 * @author alien
 *
 */
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

public class GenDaoUtil {
	//private String tablename = "t_bbby";

	private boolean f_util = false; // 是否需要导入包java.util.*

	private boolean f_sql = false; // 是否需要导入包java.sql.*

	public GenDaoUtil(String tablename) throws SQLException {
	tablename=tablename.substring(0, 1).toUpperCase()
			+ tablename.substring(1);
			String content = parse(tablename);
			try {
				FileWriter fw = new FileWriter(PathUtil.getCurrentPath()+"dao/"+initcap(tablename) + "Dao.java");
				PrintWriter pw = new PrintWriter(fw);
				pw.println(content);
				pw.flush();
				pw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		
	}

	/**
	 * 解析处理(生成实体类主体代码)
	 */
	private String parse(String tablename) {
		StringBuffer sb = new StringBuffer();
		sb.append("package com.alien.dao;\r\n\r\n\r\n");
		if (f_util) {
			sb.append("import java.util.Date;\r\n");
		}
		if (f_sql) {
			sb.append("import java.sql.*;\r\n\r\n\r\n");
		}
		sb.append("import com.alien.entity."+tablename+";\r\n\r\n\r\n");
		sb.append("public interface " + initcap(tablename) + "Dao extends BaseDao<"+tablename+", String> {\r\n");
		sb.append("}\r\n");
		System.out.println(sb.toString());
		return sb.toString();

	}


	/**
	 * 把输入字符串的首字母改成大写
	 * 
	 * @param str
	 * @return
	 */
	private String initcap(String str) {
		char[] ch = str.toCharArray();
		if (ch[0] >= 'a' && ch[0] <= 'z') {
			ch[0] = (char) (ch[0] - 32);
		}
		return new String(ch);
	}

/*	@SuppressWarnings("unused")
	private String sqlType2JavaType(String sqlType) {
		if (sqlType.equalsIgnoreCase("bit")) {
			return "bool";
		} else if (sqlType.equalsIgnoreCase("tinyint")) {
			return "byte";
		} else if (sqlType.equalsIgnoreCase("smallint")) {
			return "short";
		} else if (sqlType.equalsIgnoreCase("int")) {
			return "int";
		} else if (sqlType.equalsIgnoreCase("bigint")) {
			return "long";
		} else if (sqlType.equalsIgnoreCase("float")) {
			return "float";
		} else if (sqlType.equalsIgnoreCase("decimal") || sqlType.equalsIgnoreCase("numeric")
				|| sqlType.equalsIgnoreCase("real")) {
			return "double";
		} else if (sqlType.equalsIgnoreCase("money") || sqlType.equalsIgnoreCase("smallmoney")) {
			return "double";
		} else if (sqlType.equalsIgnoreCase("varchar") || sqlType.equalsIgnoreCase("char")
				|| sqlType.equalsIgnoreCase("nvarchar") || sqlType.equalsIgnoreCase("nchar")) {
			return "String";
		} else if (sqlType.equalsIgnoreCase("datetime")) {
			return "Date";
		}

		else if (sqlType.equalsIgnoreCase("image")) {
			return "Blob";
		} else if (sqlType.equalsIgnoreCase("text")) {
			return "Clob";
		}
		return null;
	}*/

	public static void main(String[] args) throws SQLException {
		new GenDaoUtil("admin");
	}
}
