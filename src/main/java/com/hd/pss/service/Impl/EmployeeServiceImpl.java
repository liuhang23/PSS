package com.hd.pss.service.Impl;

import java.util.List;

import com.hd.pss.domain.Employee;
import com.hd.pss.service.IEmployeeService;

public class EmployeeServiceImpl extends BaseServiceImpl<Employee> implements IEmployeeService{

	//返回false表示用户名重复了
	public boolean findByName(String name) {
		//String hql = "select o form Employee o where o.name = ?";  此处查出o对象的所有属性，效率不高
		String hql = "SELECT count(o) FROM Employee o WHERE o.name = ? ";  //0 , >0
		Long count = (Long)findByHql(hql, name).get(0);
		if(count > 0){
			return false;
		}
		return true;
	}

	public Employee findByLogin(String name, String password) {
		String hql = "SELECT o FROM Employee o WHERE o.name = ? and o.password = ?";
		List<Employee> list  =	findByHql(hql,name,password);
		//Employee.hbm.xml 映射文件:<property name="name" unique="true" />
		if(list.size() == 1){
			return list.get(0);  //取第一个对象
		}
		return null;
	}
	//查询所有的资源方法
	public List<String> getAllResourceMethods() {
		return findByHql("SELECT o.method FROM Resource o");
	}

	//看当前登录的用户是否具有这个资源:employee->employee_role->role->role_resource->resource
	public List<String> findResourceMethodByLogin(Long loginUserId) {
		//hql join:不写on子句,join后面的对象是通过前面对象的别名.出来
		//关键词 distinct用于返回唯一不同的值。
		String hql = "select distinct res.method from Employee e join e.roles r join r.resources res where e.id=?";
		return findByHql(hql, loginUserId);
	}
	
	

}
