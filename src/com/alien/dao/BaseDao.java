package com.alien.dao;

import java.io.File;
import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;

import com.alien.page.Pager;

/**
 * Dao接口 - Dao基接口
 * ============================================================================
 * ============================================================================
 */

public interface BaseDao<T, PK extends Serializable> {

	/**
	 * 获取Session
	 */
	public Session getSession();

	/**
	 * 根据ID获取实体对象
	 * 
	 * @param id
	 *            记录ID
	 * @return 实体对象
	 */
	public T get(PK id);

	/**
	 * 执行sql语句，存储过程需写{call xxx(?,?,?)}
	 * 
	 * @param sql
	 * @param param
	 * @return
	 */

	public boolean runsql(String sql, List<String> list);

	/**
	 * 根据SQL获取实体对象
	 * 
	 * @param sql
	 * 
	 * @return 实体对象
	 */
	public T getBySql(String sql);

	/**
	 * 根据ID获取实体对象
	 * 
	 * @param id
	 *            记录ID
	 * @return 实体对象
	 */
	public T load(PK id);

	/**
	 * 获取所有实体对象集合
	 * 
	 * @return 实体对象集合
	 */
	public List<T> getAllList();

	/**
	 * 获取所有实体对象总数
	 * 
	 * @return 实体对象总数
	 */
	public Long getTotalCount();

	/**
	 * 保存实体对象
	 * 
	 * @param entity
	 *            对象
	 * @return ID
	 */
	public PK save(T entity);

	/**
	 * 更新实体对象
	 * 
	 * @param entity
	 *            对象
	 */
	public void update(T entity);

	/**
	 * 删除实体对象
	 * 
	 * @param entity
	 *            对象
	 * @return
	 */
	public void delete(T entity);

	/**
	 * 根据ID删除实体对象
	 * 
	 * @param id
	 *            记录ID
	 */
	public void delete(PK id);

	/**
	 * 根据ID数组删除实体对象
	 * 
	 * @param ids
	 *            ID数组
	 */
	public void delete(PK[] ids);

	/**
	 * 刷新session
	 * 
	 */
	public void flush();

	/**
	 * 清除对象
	 * 
	 * @param object
	 *            需要清除的对象
	 */
	public void evict(Object object);

	/**
	 * 清除Session
	 * 
	 */
	public void clear();

	/**
	 * 根据SQL查询
	 * 
	 * @param id
	 *            记录ID
	 * @return 实体对象
	 */
	public List<T> getBySQL(String sql);

	/**
	 * 根据Pager进行查询(提供分页、查找、排序功能)
	 * 
	 * @param pager
	 *            Pager对象
	 * 
	 * @return Pager对象
	 */
	public Pager findPager(Pager pager);

	/**
	 * 根据Pager、Criterion进行查询(提供分页、查找、排序功能)
	 * 
	 * @param pager
	 *            Pager对象
	 * 
	 * @param criterions
	 *            Criterion数组
	 * 
	 * @return Pager对象
	 */
	public Pager findPager(Pager pager, Criterion... criterions);

	/**
	 * 根据Pager、Criterion进行查询(提供分页、查找、排序功能)
	 * 
	 * @param pager
	 *            Pager对象
	 * 
	 * @param orders
	 *            Order数组
	 * 
	 * @return Pager对象
	 */
	public Pager findPager(Pager pager, Order... orders);

	/**
	 * 根据Pager、Criteria进行查询(提供分页、查找、排序功能)
	 * 
	 * @param pager
	 *            Pager对象
	 * 
	 * @param criteria
	 *            Criteria对象
	 * 
	 * @return Pager对象
	 */
	public Pager findPager(Pager pager, Criteria criteria);

	/**
	 * 根据Pager、Criteria进行查询(提供分页、查找、排序功能)
	 * 
	 * @param pager
	 *            Pager对象
	 * 
	 * @param criteria
	 *            Criteria对象
	 * 
	 * @return Pager对象 无二级缓存
	 */
	public Pager findPagers(Pager pager, Criteria criteria);

	
	
	/**
	 * 分页
	 * @param clazz
	 * @param pager
	 * @param criteriaList
	 * @param criterions
	 * @return
	 */
	public Pager findPager(Class clazz, Pager pager, List<String> criteriaList, Criterion... criterions);

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
	public Pager findPager(Class clazz, Pager pager, Criteria criteria);

	/**
	 * excel导入
	 * 
	 * @param myExcel
	 * @param myExcelFileName
	 * @return
	 */
	public void ImportExcel(File myExcel, String myExcelFileName, String sql);

	/*-------------------sql分页-----------------*/
	/**
	 * 
	 * @param sql
	 *            条件sql
	 * @param param
	 *            list集合(条件集合)
	 * @param pager
	 * @return
	 */
	public Pager runsqlpage(String sql, List<String> list, Pager pager);

	/**
	 * 计算总条数
	 * 
	 * @param sql
	 * @param param
	 * @return
	 */
	public Integer findtotalcount(String sql, List<String> list);

	/**
	 * 
	 * 执行存储过程并返回一个参数 参数值在存储过程中必须申明为sta
	 * 
	 * @param sql
	 * @param list
	 * @return
	 */

	public String runcallsql(String sql, List<String> list);

	/**
	 * 根据sql查询集合
	 * @param sql
	 * @param list
	 * @return
	 */
	public List<T> runsqlList(String sql,List<String> list);
	
	
}