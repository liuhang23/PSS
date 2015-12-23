package com.hd.pss.domain;

//部门信息
public class Department {

	private Long id; // 部门id
	private String name; // 部门名称
	private String phone; // 部门电话

	// 给hibernate用
	public Department() {

	}

	// 给EmployeeServiceTest使用
	public Department(Long id) {
		super();
		this.id = id;
	}
	
	//给测试代码添加数据
	public Department(String name,String phone){
		super();
		this.name = name;
		this.phone = phone;
	}

	// set/get
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
