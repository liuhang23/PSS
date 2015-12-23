package com.hd.pss.service;

import java.util.List;

import com.hd.pss.domain.Employee;

public interface IEmployeeService extends IBaseService<Employee>{
		//<tx:method name="get*"  read-only="true" />
		//<tx:method name="find*" read-only="true"/>
		//验证用户名是否重复：方法取名，和事务管理有关系
		boolean findByName(String name);

		//验证登陆
		Employee findByLogin(String name, String password);
	
		// 哪些action的方法要拦截:在资源管理的方法出现的就要拦截
		List<String> getAllResourceMethods();

		// 怎样处理需要拦截的action的方法:看当前登录的用户是否具有这个资源:employee->employee_role->role->role_resource->resource
		List<String> findResourceMethodByLogin(Long loginUserId);
		
}
