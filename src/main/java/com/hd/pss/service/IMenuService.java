package com.hd.pss.service;

import java.util.List;

import com.hd.pss.domain.Employee;
import com.hd.pss.domain.Menu;
public interface IMenuService extends IBaseService<Menu> {
	
	//查询一级菜单
	List<Menu> findParentMenusByLogin(Employee employee);
	
	//查询二级菜单
	List<Menu> findChildrenMenusByLogin(Employee employee ,Long parentId);
	
}
