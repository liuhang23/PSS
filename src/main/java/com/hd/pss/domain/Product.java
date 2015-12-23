package com.hd.pss.domain;

import java.math.BigDecimal;
/**
 * 
 * 产品
 * 	1.订单明细与产品 单向多对一
 *  2.产品与品牌    单向多对一
 *  3.产品与产品类型 单向多对一 
 *  4.产品与产品品牌 单向多对一
 *  
 * Product<BR>
 * 创建人:航大 <BR>
 * 时间：2015年12月23日-下午7:22:39 <BR>
 * @version 1.0.0
 *
 */
public class Product {
	private Long id;
	private String name;
	private String color;
	/**
	 * 以下2个价格都是参考价格
	 */
	private BigDecimal costPrice;		   // 成本价
	private BigDecimal salePrice;		   // 销售价
	private String pic;// 图片,存储一个相对于webapp的路径
	private ProductType types;			   // 产品类型 单向多对一
 	private SystemDictionaryDetail brand;  // 产品品牌 单向多对一
	private SystemDictionaryDetail unit;   // 产品单位 单向多对一

	public Product() {
		super();
	}

	public Product(Long id) {
		super();
		this.id = id;
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

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public BigDecimal getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(BigDecimal costPrice) {
		this.costPrice = costPrice;
	}

	public BigDecimal getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(BigDecimal salePrice) {
		this.salePrice = salePrice;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public ProductType getTypes() {
		return types;
	}

	public void setTypes(ProductType types) {
		this.types = types;
	}

	public SystemDictionaryDetail getBrand() {
		return brand;
	}

	public void setBrand(SystemDictionaryDetail brand) {
		this.brand = brand;
	}

	public SystemDictionaryDetail getUnit() {
		return unit;
	}

	public void setUnit(SystemDictionaryDetail unit) {
		this.unit = unit;
	}

}
