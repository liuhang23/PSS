package com.hd.pss.domain;
/**
 * 
 * 供应商与客户和订单有关系
 * 	  供应商与客户（客户具体来源于数据字典）
 * 	  订单与客户  （单向多对一）
 * Supplier<BR>
 * 创建人:航大 <BR>
 * 时间：2015年12月23日-下午7:20:37 <BR>
 * @version 1.0.0
 *
 */
public class Supplier {
	private Long id;
	private String name;
	private String address;

	public Supplier() {
		super();
	}

	public Supplier(Long id) {
		super();
		this.id = id;
	}

	public Supplier(String name, String address) {
		super();
		this.name = name;
		this.address = address;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
