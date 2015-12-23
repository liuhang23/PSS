package com.hd.pss.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 
 * 
 * Role<BR>
 * 角色
 */
public class Role {
	private Long id;
	private String name;
	// 多对多 : 当前角色拥有的资源列表 ,不重复的需求使用：set
	private Set<Resource> resources = new HashSet<Resource>();

	// 多对多 ： 便于扩展，角色拥有的菜单栏
	private Set<Menu> menus = new HashSet<Menu>();

	public Role() {
		super();
	}

	public Role(Long id) {
		super();
		this.id = id;
	}

	public Role(String name) {
		super();
		this.name = name;
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

	public Set<Resource> getResources() {
		return resources;
	}

	public void setResources(Set<Resource> resources) {
		this.resources = resources;
	}

	public Set<Menu> getMenus() {
		return menus;
	}

	public void setMenus(Set<Menu> menus) {
		this.menus = menus;
	}

	@Override
	public String toString() {
		return name;
	}

}
