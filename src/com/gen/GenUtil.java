package com.gen;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.alien.comm.DBConn;
import com.alien.entity.SpreadSheets;
import com.alien.util.GenDaoImplUtil;
import com.alien.util.GenDaoUtil;
import com.alien.util.GenEntityUtil;
import com.alien.util.GenServiceImplUtil;
import com.alien.util.GenServiceUtil;
import com.alien.util.Uuid16;

public class GenUtil {
/**
 * 
 * @param name:实体类名称
 */
	public void genclass(String name) {
		try {
			//new GenEntityUtil(name);
			new GenDaoUtil(name);
			new GenDaoImplUtil(name);
			new GenServiceUtil(name);
			new GenServiceImplUtil(name);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * 查询实体类在尸体管理表中对应的id值
	 * @param className
	 * @return
	 * @throws SQLException
	 */
	public String findId(String className) throws SQLException{
		String id="";
		 DBConn dbconn = new DBConn();
		Connection conn = dbconn.getConn();
		String sql="select id from t_spread_sheets where className='"+initcap(className)+"'";	
		Statement stmt;
		try {
			stmt = conn.createStatement();
			 ResultSet rs=stmt.executeQuery(sql);
			 while(rs.next()){ 
				 id=rs.getString("id"); 
			 }
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			conn.close();
		}
		return id;
	}
	
	
	/**
	 * 把输入字符串的首字母改成大写
	 * 
	 * @param str
	 * @return
	 */
	@SuppressWarnings("unused")
	private String initcap(String str) {
		char[] ch = str.toCharArray();
		if (ch[0] >= 'a' && ch[0] <= 'z') {
			ch[0] = (char) (ch[0] - 32);
		}
		return new String(ch);
	}
	@SuppressWarnings("javadoc")
	public void  insertPermission(String className,String id) throws SQLException{
		 DBConn dbconn = new DBConn();
			Connection conn = dbconn.getConn();// 得到数据库连接
			String strsql="insert into t_permission(id,spreadsheetsid)values('"+Uuid16.create().toString()+"','"+className+"')";
			Statement stmt;
			try {
				stmt = conn.createStatement();
				 int i=stmt.executeUpdate(strsql);
		           if(i==1){
		        	   System.out.println("添加成功");
		           }else{
		        	   System.out.println("添加失败");
		           }
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				conn.close();
			}
		
	}
	
	/*public void  createdao(String tablename) throws SQLException{
		List<SpreadSheets> sheets=new ArrayList<SpreadSheets>();
		 DBConn dbconn = new DBConn();
			Connection conn = dbconn.getConn();// 得到数据库连接
			String strsql = "select * from "+tablename+" where className<>'Admin' and  className<>'Department' and  className<>'Role'";
			Statement stmt;
			try {
				stmt = conn.createStatement();
				 ResultSet rs=stmt.executeQuery(strsql);
		              while(rs.next()){                        // 对结果集进行遍历
		                SpreadSheets sheet = new SpreadSheets();
		              sheet.setClassName(rs.getString("className"));    // 需要查询什么就要像这样设置什么
		                sheets.add(sheet);                        //添加到集合
					   }
		              for(SpreadSheets s:sheets){
						  new GenUtil().genclass(s.getClassName());
					  }
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				conn.close();
			}
			 
		
  
		
	}*/

	public static void main(String[] args) throws SQLException {
		/*String className="Department";
		String id=new GenUtil().findId("Department");
		new GenUtil().insertPermission(className, id);*/
		
		new GenUtil().genclass("Menu");
	}

}
