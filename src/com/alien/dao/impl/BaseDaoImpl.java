package com.alien.dao.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.internal.CriteriaImpl;
import org.hibernate.internal.CriteriaImpl.OrderEntry;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.transform.ResultTransformer;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.alien.common.DynamicDataSourceGlobal;
import com.alien.common.DynamicDataSourceHolder;
import com.alien.dao.BaseDao;
import com.alien.entity.BaseEntity;
import com.alien.page.Pager;
import com.alien.util.ReflectionUtil;
import com.alien.util.Uuid16;

/**
 * Dao实现类 - 基类
 * ============================================================================
 * ============================================================================
 */
@Repository("baseDaoImpl")
public class BaseDaoImpl<T, PK extends Serializable> implements BaseDao<T, PK> {

	private static final String ORDER_LIST_PROPERTY_NAME = "orderList";// "排序"属性名称
	private static final String CREATE_DATE_PROPERTY_NAME = "createDate";// "创建日期"属性名称

	private Class<T> entityClass;
	protected SessionFactory sessionFactory;

	public Session getSession() {
		//DynamicDataSourceHolder.setDataSourceType(DynamicDataSourceGlobal.CCVZB);
		return sessionFactory.openSession();
	}

	@SuppressWarnings("unchecked")
	public BaseDaoImpl() {
		Class c = getClass();
		Type type = c.getGenericSuperclass();
		if (type instanceof ParameterizedType) {
			Type[] parameterizedType = ((ParameterizedType) type).getActualTypeArguments();
			this.entityClass = (Class<T>) parameterizedType[0];
		}
	}

	@Resource(name = "sessionFactory")
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * 根据SQL获取实体对象
	 * 
	 * @param sql
	 * 
	 * @return 实体对象
	 */
	public T getBySql(String sql) {
		return (T) getSession().createSQLQuery(sql).addEntity(entityClass).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public T get(PK id) {
		Assert.notNull(id, "id is required");
		return (T) getSession().get(entityClass, id);
	}

	@SuppressWarnings("unchecked")
	public T load(PK id) {
		Assert.notNull(id, "id is required");
		return (T) getSession().load(entityClass, id);
	}

	@SuppressWarnings("unchecked")
	public List<T> getAllList() {
		ClassMetadata classMetadata = sessionFactory.getClassMetadata(entityClass);
		String hql;
		if (ArrayUtils.contains(classMetadata.getPropertyNames(), ORDER_LIST_PROPERTY_NAME)) {
			hql = "from " + entityClass.getName() + " as entity order by entity." + ORDER_LIST_PROPERTY_NAME + " desc";
		} else {
			hql = "from " + entityClass.getName();
		}
		return getSession().createQuery(hql).list();
	}

	public Long getTotalCount() {
		String hql = "select count(*) from " + entityClass.getName();
		return (Long) getSession().createQuery(hql).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public PK save(T entity) {
		Assert.notNull(entity, "entity is required");
		if (entity instanceof BaseEntity) {
			try {
				Method method = entity.getClass().getMethod(BaseEntity.ON_SAVE_METHOD_NAME);
				method.invoke(entity);
				return (PK) getSession().save(entity);
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		} else {
			return (PK) getSession().save(entity);
		}
	}

	public void update(T entity) {
		Assert.notNull(entity, "entity is required");
		if (entity instanceof BaseEntity) {
			try {
				Method method = entity.getClass().getMethod(BaseEntity.ON_UPDATE_METHOD_NAME);
				method.invoke(entity);
				getSession().update(entity);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			getSession().update(entity);
		}
	}

	public void delete(T entity) {
		Assert.notNull(entity, "entity is required");
		getSession().delete(entity);
	}

	@SuppressWarnings("unchecked")
	public void delete(PK id) {
		Assert.notNull(id, "id is required");
		T entity = (T) getSession().load(entityClass, id);
		getSession().delete(entity);
	}

	@SuppressWarnings("unchecked")
	public void delete(PK[] ids) {
		Assert.notEmpty(ids, "ids must not be empty");
		for (PK id : ids) {
			T entity = (T) getSession().load(entityClass, id);
			getSession().delete(entity);
		}
	}

	public void flush() {
		getSession().flush();
	}

	public void evict(Object object) {
		Assert.notNull(object, "object is required");
		getSession().evict(object);
	}

	public void clear() {
		getSession().clear();
	}

	public List<T> getBySQL(String sql) {
		return (List<T>) getSession().createSQLQuery(sql).addEntity(entityClass).list();
	}

	public Pager findPager(Pager pager) {
		Criteria criteria = getSession().createCriteria(entityClass);
		return findPager(pager, criteria);
	}

	public Pager findPager(Pager pager, Criterion... criterions) {
		Criteria criteria = getSession().createCriteria(entityClass);
		for (Criterion criterion : criterions) {
			criteria.add(criterion);
		}
		return findPager(pager, criteria);
	}

	public Pager findPager(Pager pager, Order... orders) {
		Criteria criteria = getSession().createCriteria(entityClass);
		for (Order order : orders) {
			criteria.addOrder(order);
		}
		return findPager(pager, criteria);
	}

	public Pager findPager(Pager pager, Criteria criteria) {
		Assert.notNull(pager, "pager is required");
		Assert.notNull(criteria, "criteria is required");

		Integer pageNumber = pager.getPageNumber();
		Integer pageSize = pager.getPageSize();
		String searchBy = pager.getSearchBy();
		String keyword = pager.getKeyword();
		String orderBy = pager.getOrderBy();
		Pager.Order order = pager.getOrder();

		if (StringUtils.isNotEmpty(searchBy) && StringUtils.isNotEmpty(keyword)) {
			if (searchBy.contains(".")) {
				String alias = StringUtils.substringBefore(searchBy, ".");
				criteria.createAlias(alias, alias);
			}
			criteria.add(Restrictions.like(searchBy, "%" + keyword + "%"));
		}

		pager.setTotalCount(criteriaResultTotalCount(criteria));

		if (StringUtils.isNotEmpty(orderBy) && order != null) {
			if (order == Pager.Order.asc) {
				criteria.addOrder(Order.asc(orderBy));
			} else {
				criteria.addOrder(Order.desc(orderBy));
			}
		}

		ClassMetadata classMetadata = sessionFactory.getClassMetadata(entityClass);
		if (!StringUtils.equals(orderBy, ORDER_LIST_PROPERTY_NAME)
				&& ArrayUtils.contains(classMetadata.getPropertyNames(), ORDER_LIST_PROPERTY_NAME)) {
			criteria.addOrder(Order.asc(ORDER_LIST_PROPERTY_NAME));
			criteria.addOrder(Order.desc(CREATE_DATE_PROPERTY_NAME));
			if (StringUtils.isEmpty(orderBy) || order == null) {
				pager.setOrderBy(ORDER_LIST_PROPERTY_NAME);
				pager.setOrder(Pager.Order.asc);
			}
		} else if (!StringUtils.equals(orderBy, CREATE_DATE_PROPERTY_NAME)
				&& ArrayUtils.contains(classMetadata.getPropertyNames(), CREATE_DATE_PROPERTY_NAME)) {
			criteria.addOrder(Order.desc(CREATE_DATE_PROPERTY_NAME));
			if (StringUtils.isEmpty(orderBy) || order == null) {
				pager.setOrderBy(CREATE_DATE_PROPERTY_NAME);
				pager.setOrder(Pager.Order.desc);
			}
		}
		criteria.setFirstResult((pageNumber - 1) * pageSize);
		criteria.setMaxResults(pageSize);

		criteria.setCacheable(true);

		pager.setResult(criteria.list());
		return pager;
	}

	public Pager findPagers(Pager pager, Criteria criteria) {
		Assert.notNull(pager, "pager is required");
		Assert.notNull(criteria, "criteria is required");

		Integer pageNumber = pager.getPageNumber();
		Integer pageSize = pager.getPageSize();
		String searchBy = pager.getSearchBy();
		String keyword = pager.getKeyword();
		String orderBy = pager.getOrderBy();
		Pager.Order order = pager.getOrder();

		if (StringUtils.isNotEmpty(searchBy) && StringUtils.isNotEmpty(keyword)) {
			if (searchBy.contains(".")) {
				String alias = StringUtils.substringBefore(searchBy, ".");
				criteria.createAlias(alias, alias);
			}
			criteria.add(Restrictions.like(searchBy, "%" + keyword + "%"));
		}

		pager.setTotalCount(criteriaResultTotalCount(criteria));

		if (StringUtils.isNotEmpty(orderBy) && order != null) {
			if (order == Pager.Order.asc) {
				criteria.addOrder(Order.asc(orderBy));
			} else {
				criteria.addOrder(Order.desc(orderBy));
			}
		}

		ClassMetadata classMetadata = sessionFactory.getClassMetadata(entityClass);
		if (!StringUtils.equals(orderBy, ORDER_LIST_PROPERTY_NAME)
				&& ArrayUtils.contains(classMetadata.getPropertyNames(), ORDER_LIST_PROPERTY_NAME)) {
			criteria.addOrder(Order.asc(ORDER_LIST_PROPERTY_NAME));
			criteria.addOrder(Order.desc(CREATE_DATE_PROPERTY_NAME));
			if (StringUtils.isEmpty(orderBy) || order == null) {
				pager.setOrderBy(ORDER_LIST_PROPERTY_NAME);
				pager.setOrder(Pager.Order.asc);
			}
		} else if (!StringUtils.equals(orderBy, CREATE_DATE_PROPERTY_NAME)
				&& ArrayUtils.contains(classMetadata.getPropertyNames(), CREATE_DATE_PROPERTY_NAME)) {
			criteria.addOrder(Order.desc(CREATE_DATE_PROPERTY_NAME));
			if (StringUtils.isEmpty(orderBy) || order == null) {
				pager.setOrderBy(CREATE_DATE_PROPERTY_NAME);
				pager.setOrder(Pager.Order.desc);
			}
		}
		criteria.setFirstResult((pageNumber - 1) * pageSize);
		criteria.setMaxResults(pageSize);

		pager.setResult(criteria.list());
		return pager;
	}

	public Pager findPager(Class clazz, Pager pager, List<String> criteriaList, Criterion... criterions) {
		Criteria criteria = getSession().createCriteria(clazz);
		if (criteriaList != null) {
			for (String str : criteriaList) {
				criteria.createCriteria(str, str);
			}
		}
		for (Criterion criterion : criterions) {
			criteria.add(criterion);
		}
		return findPager(clazz, pager, criteria);
	}

	/**
	 * 新增指定对象类型查询接口
	 * 
	 * @author hpzxyj
	 * @createTime 2012-8-23
	 * @param clazz
	 * @param pager
	 * @param criteria
	 * @return
	 */
	public Pager findPager(Class clazz, Pager pager, Criteria criteria) {
		Assert.notNull(pager, "pager is required");
		Assert.notNull(criteria, "criteria is required");

		Integer pageNumber = pager.getPageNumber();
		Integer pageSize = pager.getPageSize();
		String searchBy = pager.getSearchBy();
		String keyword = pager.getKeyword();
		String orderBy = pager.getOrderBy();
		Pager.Order order = pager.getOrder();

		if (StringUtils.isNotEmpty(searchBy) && StringUtils.isNotEmpty(keyword)) {
			if (searchBy.contains(".")) {
				String alias = StringUtils.substringBefore(searchBy, ".");
				criteria.createAlias(alias, alias);
			}
			criteria.add(Restrictions.like(searchBy, "%" + keyword + "%"));
		}

		pager.setTotalCount(criteriaResultTotalCount(criteria));

		if (StringUtils.isNotEmpty(orderBy) && order != null) {
			if (order == Pager.Order.asc) {
				criteria.addOrder(Order.asc(orderBy));
			} else {
				criteria.addOrder(Order.desc(orderBy));
			}
		}

		ClassMetadata classMetadata = sessionFactory.getClassMetadata(clazz);
		if (!StringUtils.equals(orderBy, ORDER_LIST_PROPERTY_NAME)
				&& ArrayUtils.contains(classMetadata.getPropertyNames(), ORDER_LIST_PROPERTY_NAME)) {
			criteria.addOrder(Order.asc(ORDER_LIST_PROPERTY_NAME));
			criteria.addOrder(Order.desc(CREATE_DATE_PROPERTY_NAME));
			if (StringUtils.isEmpty(orderBy) || order == null) {
				pager.setOrderBy(ORDER_LIST_PROPERTY_NAME);
				pager.setOrder(Pager.Order.asc);
			}
		} else if (!StringUtils.equals(orderBy, CREATE_DATE_PROPERTY_NAME)
				&& ArrayUtils.contains(classMetadata.getPropertyNames(), CREATE_DATE_PROPERTY_NAME)) {
			criteria.addOrder(Order.desc(CREATE_DATE_PROPERTY_NAME));
			if (StringUtils.isEmpty(orderBy) || order == null) {
				pager.setOrderBy(CREATE_DATE_PROPERTY_NAME);
				pager.setOrder(Pager.Order.desc);
			}
		}

		criteria.setFirstResult((pageNumber - 1) * pageSize);
		criteria.setMaxResults(pageSize);

		criteria.setCacheable(true);

		pager.setResult(criteria.list());
		return pager;
	}

	// 获取Criteria查询数量
	@SuppressWarnings("unchecked")
	private int criteriaResultTotalCount(Criteria criteria) {
		Assert.notNull(criteria, "criteria is required");

		int criteriaResultTotalCount = 0;
		try {
			CriteriaImpl criteriaImpl = (CriteriaImpl) criteria;

			Projection projection = criteriaImpl.getProjection();
			ResultTransformer resultTransformer = criteriaImpl.getResultTransformer();
			List<OrderEntry> orderEntries = (List) ReflectionUtil.getFieldValue(criteriaImpl, "orderEntries");
			ReflectionUtil.setFieldValue(criteriaImpl, "orderEntries", new ArrayList());

			Integer totalCount = ((Long) criteriaImpl.setProjection(Projections.rowCount()).uniqueResult()).intValue();
			if (totalCount != null) {
				criteriaResultTotalCount = totalCount;
			}

			criteriaImpl.setProjection(projection);
			if (projection == null) {
				criteriaImpl.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
			}
			if (resultTransformer != null) {
				criteriaImpl.setResultTransformer(resultTransformer);
			}
			ReflectionUtil.setFieldValue(criteriaImpl, "orderEntries", orderEntries);
		} catch (Exception e) {

		}
		return criteriaResultTotalCount;
	}

	/**
	 * 执行sql语句
	 * 
	 * @param sql
	 * @param param
	 * @return
	 */

	public boolean runsql(String sql, List<String> list) {
		boolean flag = false;
		try {
			SQLQuery query = getSession().createSQLQuery(sql);
			if (list != null) {
				for (int i = 0; i < list.size(); i++) {
					query.setString(i, list.get(i));
				}
			}

			query.executeUpdate();
			getSession().flush();
			getSession().clear();
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
			getSession().beginTransaction().rollback();
		} // finally {
		// session.close();
		// }

		return flag;
	}

	@Override
	public void ImportExcel(File myExcel, String myExcelFileName, String sql) {
		Workbook wookbook = null;
		String[] columns = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (myExcelFileName.endsWith(".xls")) {
			try {
				// 创建对Excel工作簿文件的引用
				wookbook = new HSSFWorkbook(new FileInputStream(myExcel));
				List<T> entitylist = new ArrayList<T>();

				// 在Excel文档中，第一张工作表的缺省索引是0
				// 其语句为：HSSFSheet sheet = workbook.getSheetAt(0);
				HSSFSheet sheet = (HSSFSheet) wookbook.getSheet("Sheet1");
				// 获取到Excel文件中的所有行数
				int rows = sheet.getPhysicalNumberOfRows();
				int cells = sheet.getRow(rows - 1).getPhysicalNumberOfCells();
				// 遍历行
				for (int i = 1; i < rows; i++) {
					List<String> list = new ArrayList<String>();
					list.add(Uuid16.create().toString());
					list.add(sdf.format(new Date()));
					list.add(sdf.format(new Date()));

					// 读取左上端单元格
					HSSFRow row = sheet.getRow(i);
					// 行不为空
					if (row != null) {
						columns = new String[cells];
						// 遍历列
						for (int j = 0; j < cells; j++) {
							// 获取列
							HSSFCell cell = row.getCell(j);
							if (cell != null) {
								cell.setCellType(Cell.CELL_TYPE_STRING);
								columns[j] = cell.getStringCellValue() == null || "".equals(cell.getStringCellValue())
										? "" : cell.getStringCellValue();
							}
						}
						for (int b = 0; b < columns.length; b++) {
							if (columns[b] != null) {
								System.out.println(columns[b]);
								list.add(columns[b]);
							}

						}
					}
					runsql(sql, list);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (myExcelFileName.endsWith(".xlsx")) {
			System.out.println();
			try {
				// 创建对Excel工作簿文件的引用
				wookbook = new XSSFWorkbook(new FileInputStream(myExcel));

				// 在Excel文档中，第一张工作表的缺省索引是0
				// 其语句为：HSSFSheet sheet = workbook.getSheetAt(0);
				XSSFSheet sheet = (XSSFSheet) wookbook.getSheet("Sheet1");
				// 获取到Excel文件中的所有行数
				int rows = sheet.getPhysicalNumberOfRows();
				// int rows =sheet.getLastRowNum();
				// 获取总列数
				int cells = sheet.getRow(rows - 1).getPhysicalNumberOfCells();
				// 遍历行
				for (int i = 1; i < rows; i++) {
					List<String> list = new ArrayList<String>();
					list.add(Uuid16.create().toString());
					list.add(sdf.format(new Date()));
					list.add(sdf.format(new Date()));
					// 读取左上端单元格
					XSSFRow row = sheet.getRow(i);
					// 行不为空
					if (row != null) {
						columns = new String[cells];
						// 遍历列
						for (int j = 0; j < cells; j++) {

							// 获取列
							XSSFCell cell = row.getCell(j);
							if (cell != null) {
								cell.setCellType(XSSFCell.CELL_TYPE_STRING);
								columns[j] = cell.getStringCellValue() == null || "".equals(cell.getStringCellValue())
										? "" : cell.getStringCellValue();
							}
						}
						for (int b = 0; b < columns.length; b++) {
							if (columns[b] != null) {
								System.out.println(columns[b]);
								list.add(columns[b]);
							}

						}

					}

					runsql(sql, list);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

	/**
	 * 数组中添加元素
	 * 
	 * @param ori原数组
	 * @param val添加的元素值
	 * @param index下标
	 * @return
	 */
	public static String[] insert(String[] ori, String val, int index) {
		for (int i = ori.length - 1; i > index; i--)
			ori[i] = ori[i - 1];
		ori[index] = val;
		return ori;
	}

	/** -------------sql分页---------- */
	/**
	 * compcode传入的参数 pageNumber页码 pageSize每页记录数
	 */
	@SuppressWarnings("unchecked")

	public Pager runsqlpage(String sql, List<String> list, Pager pager) {
		List<T> param = new ArrayList<T>();
		try {
			SQLQuery query = getSession()
					.createSQLQuery(pager.getPageMySQL(sql, pager.getPageNumber(), pager.getPageSize()))
					.addEntity(entityClass);
			if (param != null) {
				for (int i = 0; i < list.size(); i++) {
					query.setString(i, list.get(i));
				}
				list = query.list();
				pager.initPageBean(pager.getTotalCount(), pager.getPageSize());
				pager.setResult(list);
				getSession().flush();
				getSession().clear();
			}
		} catch (Exception e) {
			e.printStackTrace();
			getSession().beginTransaction().rollback();
		}
		return pager;
	}

	/**
	 * return 总页码
	 */
	public Integer findtotalcount(String sql, List<String> list) {
		Integer totalcount = 0;
		try {
			SQLQuery query = getSession().createSQLQuery(sql);
			if (list != null) {
				for (int i = 0; i < list.size(); i++) {
					query.setString(i, list.get(i));
				}
				totalcount = Integer.valueOf(query.uniqueResult().toString());
			}
			getSession().flush();
			getSession().clear();
		} catch (Exception e) {
			e.printStackTrace();
			getSession().beginTransaction().rollback();
		}
		return totalcount;
	}

	public String runcallsql(String sql, List<String> list) {
		String status = null;
		Session session = sessionFactory.openSession();
		ConnectionProvider cp = ((SessionFactoryImplementor) sessionFactory).getConnectionProvider();
		Transaction transaction = session.beginTransaction();
		CallableStatement statement;
		try {
			statement = (CallableStatement) cp.getConnection().prepareCall(sql);
			if (list != null) {
				for (int i = 0; i < list.size(); i++) {
					System.out.println(i + 1);
					System.out.println(list.get(i));
					statement.setString(i + 1, list.get(i));
				}
				statement.registerOutParameter("sta", Types.VARCHAR);
			}
			statement.executeUpdate();
			transaction.commit();
			status = statement.getString("sta");
		} catch (SQLException e) {
			e.printStackTrace();
			transaction.rollback();
		} catch (HibernateException e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}

		return status;

	}

	@Override
	public List<T> runsqlList(String sql, List<String> list) {
		List<T> lists = new ArrayList<T>();
		try {
			SQLQuery query = getSession().createSQLQuery(sql).addEntity(entityClass);
			if (list != null) {
				for (int i = 0; i < list.size(); i++) {
					query.setString(i, list.get(i));
				}
				lists = query.list();
			}
			return lists;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}