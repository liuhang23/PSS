package com.hd.pss.service;

import java.io.File;
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;

import com.hd.pss.page.BaseQuery;
import com.hd.pss.page.PageResult;

public interface IBaseService<T> {
	/**
	 * 保存
	 */
	void save(T t);

	/**
	 * 修改
	 */
	void update(T t);

	/**
	 * 删除
	 */
	void delete(Serializable id);

	/**
	 * 获取单个对象
	 */
	T get(Serializable id);

	/**
	 * 得到多个对象
	 */
	List<T> getAll();
	
	/**
	 * 按条件并分页查询
	 */
	PageResult<T> findPageResult(BaseQuery baseQuery);
	
	/**
	 * 传hql 和 多个参数的查询
	 * */	
	 List  findByHql(String hql , Object... objects);
	
	 /**
	 * 导出execel文件(xsl) : 查询有数据，生成一个xls文件
	 */
	// 导出xls文件:查询有数据,生成InputStream,让用户下载
	InputStream export(String[] heads, List<String[]> list) throws Exception;
	
	//超级管理员:导入xls文件:让用户上传一个xls文件(格式必须固定,每列放什么字段的数据必须规定好),解析
	List<String[]> imp(File file) throws Exception;
	
}
