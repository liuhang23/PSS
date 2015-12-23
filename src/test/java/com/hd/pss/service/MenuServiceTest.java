package com.hd.pss.service;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hd.pss.domain.Employee;
import com.hd.pss.domain. Menu;
import com.hd.pss.service.IMenuService;

public class MenuServiceTest extends BaseServiceTest {
	@Autowired
	IMenuService menuService;

	@Test
	public void testSave() throws Exception {
		// 权限模块:资源,角色,菜单
		Menu parent = new Menu("权限模块",null,null); // 一级目录
		parent.setIcon("js/ztree/css/zTreeStyle/img/diy/1_open.png");
		menuService.save(parent);
		Menu menu = new Menu("资源管理","resource.action",parent);
		menu.setIcon("js/ztree/css/zTreeStyle/img/diy/2.png");
		menuService.save(menu);
		menu = new Menu("角色管理","role.action",parent);
		menu.setIcon("js/ztree/css/zTreeStyle/img/diy/3.png");
		menuService.save(menu);
		menu = new Menu("菜单管理","menu.action",parent);
		menu.setIcon("js/ztree/css/zTreeStyle/img/diy/4.png");
		menuService.save(menu);
		// 组织机构:公司,部门,员工
		parent = new Menu("组织机构",null,null);  // 一级目录 
		parent.setIcon("js/ztree/css/zTreeStyle/img/diy/1_close.png");
		menuService.save(parent);
		menu = new Menu("部门管理","department.action",parent);
		menu.setIcon("js/ztree/css/zTreeStyle/img/diy/5.png");
		menuService.save(menu);
		menu = new Menu("员工管理","employee.action",parent);
		menu.setIcon("js/ztree/css/zTreeStyle/img/diy/6.png");
		menuService.save(menu);
	}
	
	@Test
	public void query() throws Exception{
		Employee employee = new Employee();
		employee.setId(2L);
		List<Menu> parentMenuList = menuService.findParentMenusByLogin(employee);
		//遍历一级菜单
		for(Menu parentMenu : parentMenuList){
			System.out.println("一级菜单:" + parentMenu);
			// 二级菜单
			List<Menu> childrenlist = menuService.findChildrenMenusByLogin(employee, parentMenu.getId());
			for (Menu childmenu : childrenlist) {
				System.out.println("二级菜单:" + childmenu);
			}
			System.out.println("---------------------");
		}
	}
}
