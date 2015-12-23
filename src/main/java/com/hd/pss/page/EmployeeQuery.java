package com.hd.pss.page;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * 查询对象 ：封装employee的查询条件（名称，email模糊查询，部门名称的查询） EmployeeQuery<BR>
 *			当前页码，一页条数（由父类处理）
 */
public class EmployeeQuery extends BaseQuery{
	private String name;    //按照名称查询
	private String email;	//按照邮箱查询
	private Long deptId;	//按照部门id查询
	
	public EmployeeQuery() {
		super("Employee");
	}
	
	@Override
	public void addWhere() {
		if(StringUtils.isNotBlank(name)){ //用户名不为空,name为传入的参数
			addWhere("o.name LIKE ?", "%" + name + "%"); //模糊查询,"%"不能存在空格
		}
		if(StringUtils.isNotBlank(email)){
			addWhere("o.email LIKE ?","%" + email + "%");	
		}
		if(deptId != null && deptId != -1L){//注意类型
			addWhere("o.department.id=?", deptId);  
		}
	}

	//
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}


}
