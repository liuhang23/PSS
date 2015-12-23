package com.hd.pss.service;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * 
 * 放前面(单表)的先运行，依赖的测试应该放后面
 */
@RunWith(Suite.class)
@SuiteClasses({ SystemDictionaryTypeServiceTest.class,
				SystemDictionaryDetailServiceTest.class,
				SupplierServiceTest.class,
				ResourceServiceTest.class,
				MenuServiceTest.class,
				RoleServiceTest.class,
				DepartmentServiceTest.class,
				EmployeeServiceTest.class,
				ProductTypeServiceTest.class
				})
public class AllTests {

	
	
}
