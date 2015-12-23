package com.hd.pss.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hd.pss.domain.Menu;
import com.hd.pss.domain.Resource;
import com.hd.pss.domain. Role;
import com.hd.pss.service.IRoleService;

public class RoleServiceTest extends BaseServiceTest {
	@Autowired
	IRoleService roleService;
	
	@Autowired
	IResourceService resourceService;
	
	@Autowired
	IMenuService menuService;
//	role
//	id     name
//	1      超级管理员
//	2      角色管理员

//	中间表role_resource
//	role_id   resource_id
//	1         1
//	1         2
//	1         3
//	2         2

	
	@Test
	public void testSave() throws Exception {
		Role role = new Role("超级管理员");
		role.getResources().addAll(resourceService.getAll()); //超级管理员有所有资源
	    role.getMenus().addAll(menuService.getAll());	//建立菜单和人员的关系
		roleService.save(role);
		//添加中间表的数据
		role = new Role("角色管理员");
		role.getResources().add(new Resource(2L)); //角色管理员
		role.getMenus().add(new Menu(1L));
		role.getMenus().add(new Menu(3L));
		roleService.save(role);
		
	}
}
