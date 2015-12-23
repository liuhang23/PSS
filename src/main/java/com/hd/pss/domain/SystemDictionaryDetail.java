package com.hd.pss.domain;

/**
 * 
 * 1.数据字典明细明细:产品和明细关联,里面有n个数据
 * 	   1.1 用来描述数据基本信息
 * 
 * 2.产品与数据字典明细(n 对 1) 多对一
 * 	   2.1 产品属于哪个品牌(name) 
 * 	   2.2 产品属于哪个单位(name)
 * 
 * 3.数据字典明细与数据字典类型(n 对 1) 多对一
 * 	   	
 */
public class SystemDictionaryDetail {
	private Long id;     // 主键
	private String name; //	对应明细名字  
	// 多对一
	private SystemDictionaryType types;// 对应数据字典类型对象  [数据字典类型 : 客户来源、产品品牌、产品单位]

	public SystemDictionaryDetail() {
		super();
	}

	public SystemDictionaryDetail(Long id) {
		super();
		this.id = id;
	}

	public SystemDictionaryDetail(String name, SystemDictionaryType types) {
		super();
		this.name = name;
		this.types = types;
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

	public SystemDictionaryType getTypes() {
		return types;
	}

	public void setTypes(SystemDictionaryType types) {
		this.types = types;
	}

}
