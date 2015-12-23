package com.hd.pss.domain;

import org.apache.struts2.json.annotations.JSON;

/***
 * 
 * 菜单 Menu<BR>
 * 创建人:航大 <BR>
 * 时间：2015年11月2日-上午10:49:52 <BR>
 * 
 * @version 1.0.0
 *
 */
public class Menu {
	private Long id;
	private String name; // 菜单名字
	private String icon; // 菜单图片相对webapp的相对路径
	private String url; // 请求的链接
	// 父菜单 ： 自关联的多对一（多个子菜单对应一个父菜单）
	private Menu parent;

	// 无参构造
	public Menu() {

	}

	// 带id的构造
	public Menu(Long id) {
		this.id = id;
	}

	public Menu(String name, String url, Menu parent) {
		super();
		this.name = name;
		this.url = url;
		this.parent = parent;
	}
	
	//添加额外的输出属性  : 解决页面跳转不能在main页面中显示
	public Boolean getIsParent(){
		return this.parent == null;
	}
	public String getTarget() {
		return getIsParent() ? null : "main";
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

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@JSON(serialize = false)
	public Menu getParent() {
		return parent;
	}

	public void setParent(Menu parent) {
		this.parent = parent;
	}

	@Override
	public String toString() {
		return "Menu [name=" + name + ", url=" + url + "]";
	}

}
