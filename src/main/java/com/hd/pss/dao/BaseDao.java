package com.hd.pss.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.hd.pss.page.BaseQuery;
import com.hd.pss.page.PageResult;

/**
 * 
 * 公共dao,直接操作数据库 BaseDao<BR>
 * 创建人:航大 <BR>
 * 时间：2015年10月20日-下午6:34:29 <BR>
 * 
 * @version 1.0.0
 *
 */
@SuppressWarnings("unchecked")
public class BaseDao<T> extends HibernateDaoSupport {

	/**
	 * 保存
	 */
	public void save(T t) {
		getHibernateTemplate().save(t);
	}

	/**
	 * 更新
	 */
	public void update(T t) {
		getHibernateTemplate().update(t);
	}

	/**
	 * 只要实现序列化接口，都可以作为id
	 * 
	 * @param Serializable
	 *            id ;通用的好处 删除
	 */
	public void delete(Class<T> entityClass, Serializable id) {
		// getHibernateTemplate().delete(id); 报错
		T t = get(entityClass, id);
		if (t != null) {
			getHibernateTemplate().delete(t);
		}
	}

	/**
	 * 获取单个对象
	 */
	public T get(Class<T> entityClass, Serializable id) {
		return getHibernateTemplate().get(entityClass, id);
	}

	/**
	 * 得到多个对象
	 */
	public List<T> getAll(Class<T> entityClass) {
		return getHibernateTemplate().find("from " + entityClass.getName());
	}

	/**
	 * 按条件分页查询
	 */
	public PageResult<T> findPageResult(final BaseQuery baseQuery) {
		//System.out.println("findPageResult countHql:" + baseQuery.getCountHql());
		//System.out.println("findPageResult hql:" + baseQuery.getHql());
		//System.out.println("findPageResult paramList:" + baseQuery.getParamList());
		//计算总数
		int count = getHibernateTemplate().executeWithNativeSession( //获取本地session
				new HibernateCallback<Integer>() {  //new出的是一个接口，需要再内部进行实现（匿名内部类）
					public Integer doInHibernate(Session session) throws HibernateException,SQLException { //回调获取
						//发出hql
						Query query = session.createQuery(baseQuery.getCountHql()); //内部类要访问外部属性,只能把变量定义为常量
						int index = 0;  //设置参数位子
						for(Object object : baseQuery.getParamList()){
							query.setParameter(index++, object); //用完后在加，不能写成index+1
							//index++
						}
						Long countLong = (Long)query.uniqueResult(); //获取唯一值	
						return countLong.intValue();
					}
		});
		//没有查询到数据
		if(count == 0){
			return new PageResult<T>();
		}
		//定义为final只是表示对象引用地址不能改变，但是地址对应值是可以改变的
		final  PageResult<T> pageResult = new PageResult<T>(baseQuery.getCurrentPage(),baseQuery.getPageSize(),count);
		
		//当前页数据
		List<T> rows = getHibernateTemplate().executeWithNativeSession(
				new HibernateCallback<List<T>>() {
					public List<T> doInHibernate(Session session) throws HibernateException, SQLException {
						//发送hql
						Query query = session.createQuery(baseQuery.getHql());
						//设置参数
						int index = 0;
						for(Object object : baseQuery.getParamList()){
							query.setParameter(index++, object);
						}
						//设置分页条件
						//baseQuery对象里面的CurrentPage,PageSize没有经过错误处理，必须使用pageResult对象。
						int first = (pageResult.getCurrentPage() -1) * pageResult.getPageSize();
						int max = pageResult.getPageSize();
						//System.out.println(first + "==" + max);
						query.setFirstResult(first).setMaxResults(max);
						return query.list();
					}
				});
		pageResult.setRows(rows);
		return pageResult;
	}
	
	/**
	 * 
	 * 方法名：findByHql<BR>
	 * 时间：2015年10月24日-下午8:25:35 <BR>
	 * @param hql
	 * 		1.  select count(o) from Employee o where o.name = ? 
	 * 		2.	select o form Employee o where o.name = ?
	 * 		3.  select o.name,o.email form Employee o where o.name = ?
	 * @param objects
	 * @return List<BR> 直接返回List,没有T
	 * @exception <BR>
	 * @since  1.0.0
	 */
	public List  findByHql(String hql , Object... objects){
		return getHibernateTemplate().find(hql, objects);
	}

}
