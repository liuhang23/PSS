package com.hd.pss.service.Impl;

import java.util.List;

import com.hd.pss.domain.Employee;
import com.hd.pss.domain.Menu;
import com.hd.pss.service.IMenuService;

public class MenuServiceImpl extends BaseServiceImpl<Menu> implements IMenuService {
	
	//获取一级菜单
	public List<Menu> findParentMenusByLogin(Employee employee) {
		String hql = "select distinct m from Employee e join e.roles r join r.menus m where m.parent is null and e = ?";
		return findByHql(hql, employee);
	}

	//获取二级菜单
	public List<Menu> findChildrenMenusByLogin(Employee employee, Long parentMenuId) {
		String hql = "select distinct m from Employee e join e.roles r join r.menus m where m.parent.id = ? and e = ?";
		return findByHql(hql, parentMenuId,employee);
	}

}
