package com.hd.pss.page;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * 
 * 查询对象，封装公共的内容  : 一个条数，页码，封装2条hql以及参数值
 *
 */
public abstract class BaseQuery {
	private StringBuilder hql;  //没有同步需求使用StringBuilder 
	//hql : select o from Employee o where o.name like ? and o.email like ? and o.department.id = ?
	private StringBuilder countHql;  //求总数
	//countHql : select count(o) from Employee o where o.name like ? and  o.emai like ? and o.department.id = ?
	
	//添加参数值
	private List paramList = new ArrayList();
	//封装公共: 当前页码、一页条数
	private int currentPage = 1;
	private int pageSize = 10;
	
	public BaseQuery(String className){
		hql = new StringBuilder("SELECT o FROM " + className + " o" ); // select o from Employee o
		countHql =  new StringBuilder("SELECT count(o) FROM " + className + " o");
	}
	
	//添加一个让子类处理查询提交的方法  abstract抽象的方法 子类必须去实现
	protected abstract void addWhere();
	
	//添加一个方法：直接让子类传入查询的条件和参数值[定义为可变数组]
	protected void addWhere(String where , Object... objects) {
		//使用where还是adn
		if(paramList.size() == 0){ //表示从没有放入过
			hql.append(" WHERE ").append(where); //空格
			countHql.append(" WHERE ").append(where);
		}else{
			hql.append(" AND ").append(where);
			countHql.append(" AND ").append(where);
		}
		//设置参数：1.addAll方法(返回集合) ， 2.把object数组变成list
		paramList.addAll(Arrays.asList(objects));
	}
	
	private boolean flag; //默认为false
	
	//保证addWhere只能调用一次
	private void builerWhere(){
		if(!flag){  // !flag  不为
			addWhere();
			flag = true;
		}
	}
	
	//因为BaseDao必须获取到hql,countHql,paramList,所有需要提供给get方法。
	public String getHql() {
		builerWhere();
		return hql.toString();
	}

	public String getCountHql() {
		builerWhere();
		return countHql.toString();
	}

	public List getParamList() {
		builerWhere();
		return paramList;
	}
	
	//封装公共当前页码，一页条数：都必须有get,set
	public int getCurrentPage() {
		return currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}	
	
	
}
