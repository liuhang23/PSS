package com.hd.pss.page;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * 查询对象 ：封装Department的查询条件(名称，部门电话,)还必须有当前页码，一页数据（由父类处理）
 *
 */
public class DepartmentQuery extends BaseQuery {

	private String name;
	private String phone;
	
    public DepartmentQuery() {
		super("Department");
	}

	@Override
	protected void addWhere() {
		if(StringUtils.isNotBlank(name)){ //用户名不为空,name为传入的参数
			addWhere("o.name LIKE ?", "%" + name + "%"); //模糊查询,"%"不能存在空格
		}
		if(StringUtils.isNotBlank(phone)){
			addWhere("o.phone LIKE ?","%" + phone + "%");	
		}
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
