package com.hd.pss.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.hd.pss.domain. Resource;
import com.hd.pss.service.IResourceService;

public class ResourceServiceTest extends BaseServiceTest {
	@Autowired
	IResourceService resourceService;
	
//	1      资源管理  com.hd.pss.view.action.ResourceAction.ALL
//	2      角色管理  com.hd.pss.view.action.RoleAction.ALL
//	3      删除员工  com.hd.pss.view.action.EmployeeAction.delete


	@Test
	public void testSave() throws Exception {
		Resource resource = new Resource("资源管理 ","com.hd.pss.view.action.ResourceAction.ALL");
		resourceService.save(resource);
		resource = new Resource("角色管理 ","com.hd.pss.view.action.RoleAction.ALL");
		resourceService.save(resource);
		resource = new Resource("删除员工 ","com.hd.pss.view.action.EmployeeAction.delete");
		resourceService.save(resource);
	}
}
