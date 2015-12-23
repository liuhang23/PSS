package com.hd.pss.domain;

import org.apache.struts2.json.annotations.JSON;
/**
 * 
 * 产品类型 
 * 		
 * ProductType<BR>
 * 创建人:航大 <BR>
 * 时间：2015年12月23日-下午7:22:26 <BR>
 * @version 1.0.0
 *
 */
public class ProductType {
	private Long id;
	private String name;
	// 自关联 一对多 
	private ProductType parent;  

	public ProductType() {
		super();
	}

	public ProductType(Long id) {
		super();
		this.id = id;
	}

	public ProductType(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public ProductType(String name, ProductType parent) {
		super();
		this.name = name;
		this.parent = parent;
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

	@JSON(serialize = false)
	public ProductType getParent() {
		return parent;
	}

	public void setParent(ProductType parent) {
		this.parent = parent;
	}

}
