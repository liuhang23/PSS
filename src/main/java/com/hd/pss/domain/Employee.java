package com.hd.pss.domain;

import java.util.HashSet;
import java.util.Set;

//员工信息
public class Employee implements java.io.Serializable {

	private Long id; // 主键
	private String name; // 员工姓名
	private String password; // 员工密码
	private String email; // 员工邮箱
	private Integer age; // 员工年龄
	private Department department; // 员工对应的部门(多对一)
	//多对多 ： 当前员工拥有的角色列表
	private Set<Role> roles = new HashSet<Role>();
	
	// 给hibernate用
	public Employee() {
	}
	
	// 给EmployeeService使用
	public Employee(Long id){
		super();
		this.id = id;
	}

	//给测试代码添加数据
	public Employee(String name, String password) {
		super();
		this.name = name;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	

	
}
