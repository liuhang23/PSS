package com.hd.pss.domain;

/**
 * 
 * 数据字典类型:n多的类似domain(id,name),
 * 		用来描述数据基本信息
 */
public class SystemDictionaryType {
	// 定义N个domain的sn的名称	
	// 客户来源
	public static final String CLIENT_RESOUCE="clientResouce";
	// 产品品牌
	public static final String PRODUCT_BRAND="productBrand";
	// 产品单位
	public static final String PRODUCT_UNIT ="productUnit";
	
	
	private Long id;  	// 主键
	// sn唯一,不能修改,unique="true" update="false"
	private String sn;	
	// 来自模型的名称
	private String name; 

	public SystemDictionaryType() {
		super();
	}

	public SystemDictionaryType(Long id) {
		super();
		this.id = id;
	}

	public SystemDictionaryType(String sn, String name) {
		super();
		this.sn = sn;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
