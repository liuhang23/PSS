package com.hd.pss.domain;

/**
 * 
 * 
 * Role<BR>
 * 资源
 */
public class Resource {
	private Long id;
	private String name;
	private String method; // 规则: 全类名.方法名(如果所有方法ALL)
	private String descs; // 备注: desc为数据库关键字，不推荐使用

	// hibernate使用
	public Resource() {

	}

	// 测试使用
	public Resource(Long id) {
		super();
		this.id = id;
	}

	// 给测试代码添加数据
	public Resource(String name, String method) {
		super();
		this.name = name;
		this.method = method;
		this.descs = name + "," + method;
	}

	//
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

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getDescs() {
		return descs;
	}

	public void setDescs(String descs) {
		this.descs = descs;
	}

}
