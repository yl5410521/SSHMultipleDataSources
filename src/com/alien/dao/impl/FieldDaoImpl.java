package com.alien.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.alien.dao.FieldDao;
import com.alien.entity.Field;


@Repository("fieldDaoImpl")
public class FieldDaoImpl extends BaseDaoImpl<Field, String>implements FieldDao {

	public boolean addField(String tableName, List<Field> fields) {
		boolean flag = false;
		String sql;
		try {
			for (Field f : fields) {
					if (f.getFieldtype().toLowerCase() == "datetime" && f.getFieldtype().toLowerCase() == "text") {
						sql = "alter table " + tableName + " add " + f.getFieldName() + " " + f.getFieldtype() + ";";
					} else if (f.getFieldtype().toLowerCase() == "decimal") {
						sql = "alter table " + tableName + " add " + f.getFieldName() + " " + f.getFieldtype()
								+ "(2,9);";

					} else {
						sql = "alter table " + tableName + " add " + f.getFieldName() + " " + f.getFieldtype() + "("
								+ f.getFieldLength() + ");";

					}

					getSession().createSQLQuery(sql).executeUpdate();

				}
			
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@SuppressWarnings("unchecked")
	public List<Field> fieldslist(String sheetsid) {
		String hql = "from Field f where f.spreadSheets.id=?";
		List<Field> fields = new ArrayList<Field>();
		try {
			fields = getSession().createQuery(hql).setString(0, sheetsid).list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fields;
	}

	@SuppressWarnings("unchecked")
	public boolean checkfieldName(String sheetsid, String fieldName) {
		boolean flag = false;
		String hql = "from Field f where f.spreadSheets.id=?";
		List<Field> fields = new ArrayList<Field>();
		try {
			fields = getSession().createQuery(hql).setString(0, fieldName).list();
			for (Field f : fields) {

				if (f.getFieldName() == fieldName) {
					flag = true;
				} else {
					flag = false;
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	public boolean createforeignkey(String masterTabile, String subordinateTable) {
		boolean flag = false;
		StringBuffer sb = new StringBuffer("");
		sb.append("alter table " + masterTabile + " add " + subordinateTable.replace("t_", "") + "_id varchar(32);,");
		sb.append("alter table " + masterTabile + "");
		sb.append(" add constraint " + subordinateTable.replace("t_", "") + "_id foreign key ("
				+ subordinateTable.replace("t_", "") + "_id) references " + subordinateTable + " (id);");
		String sql = sb.toString();
		String[] sqls = sql.split(",");
		try {
			for (int i = 0; i < sqls.length; i++) {
				getSession().createSQLQuery(sqls[i]).executeUpdate();
			}
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	public void savefield(Field field) {
		getSession().save(field);
		getSession().flush();
		getSession().clear();
	}
	
	public void addField(String sid) {
		Field field=new Field();
		StringBuffer sb=new StringBuffer();
		sb.append("32,");
		sb.append(" ,");
		sb.append(" ");
		
		String[] length=sb.toString().split(",");
		
		StringBuffer sb1=new StringBuffer();
		sb1.append("id,");
		sb1.append("createDate,");
		sb1.append("modifyDate");
		String[] fieldName=sb1.toString().split(",");
		
		StringBuffer sb2=new StringBuffer();
		sb2.append("String,");
		sb2.append("Date,");
		sb2.append("Date,");
		
		String[] fieldcontent=sb2.toString().split(",");
		
		StringBuffer sb3=new StringBuffer();
		sb3.append("主键,");
		sb3.append("创建时间,");
		sb3.append("修改时间");
		
		String[] name=sb3.toString().split(",");
		
		StringBuffer sb4=new StringBuffer();
		sb4.append("varchar,");
		sb4.append("datetime,");
		sb4.append("datetime");
		
		String[] fieldtype=sb4.toString().split(",");
		try {
			for(int i=0;i<fieldtype.length;i++){
				field.setFieldName(fieldName[i]);
				field.setFieldLength(length[i]);
				field.setName(name[i]);
				field.setFieldtype(fieldtype[i]);
				field.setFieldcontent(fieldcontent[i]);
				field.getSpreadSheets().setId(sid);
				getSession().save(field);
				getSession().flush();
				getSession().clear();
			}
		
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
