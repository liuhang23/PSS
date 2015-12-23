package com.hd.pss.service;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.hd.pss.domain.Department;
import com.hd.pss.service.IDepartmentService;


public class DepartmentServiceTest extends BaseServiceTest{

	@Resource
	IDepartmentService  departmentService;
	
	
	@Test
	public void testSave() throws Exception{
	    Department department = new Department("IT部","10000");
	    departmentService.save(department);
	    Department department1 = new Department("采购部","10001");
	    departmentService.save(department1);
	    Department department2 = new Department("销售部","10002");
	    departmentService.save(department2);
	    
	}
	
}
