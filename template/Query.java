package com.hd.pss.page;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * 查询对象:封装${domain}的查询条件(名称),还必须有当前页码,一页条数(由父类处理)
 * 
 */
public class ${domain}Query extends BaseQuery {
	private String name;

	public ${domain}Query() {
		super("${domain}");
	}

	@Override
	protected void addWhere() {
		if (StringUtils.isNotBlank(name)) {
			addWhere("o.name LIKE ?", "%" + name + "%");
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
