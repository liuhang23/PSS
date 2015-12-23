package com.hd.pss.service;

import java.util.List;
import java.util.Random;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hd.pss.domain.Department;
import com.hd.pss.domain.Employee;
import com.hd.pss.domain.Role;
import com.hd.pss.service.IDepartmentService;
import com.hd.pss.service.IEmployeeService;

public class EmployeeServiceTest extends BaseServiceTest {

	@Autowired
    IEmployeeService employeeService;
	@Autowired
	IDepartmentService departmentService;
	@Autowired
	IRoleService roleService;
	
	
	//1.CURD测试
	@Test
	public void save() throws Exception{
		Employee employee = new Employee("admin","admin");
		employee.setEmail("admin@hang.com");
		employee.setAge(23);
		employee.setDepartment(new Department(1L));
		//超级管理员拥有的所有角色
		employee.getRoles().addAll(roleService.getAll());
		employeeService.save(employee);
		
		employee = new Employee("roleAdmin","roleAdmin");
		employee.setEmail("admin@hang.com");
		employee.setAge(23);
		employee.setDepartment(new Department(1L));
		//角色管理员只拥有的所有角色管理的角色
		employee.getRoles().add(new Role(2L));  //中间表employee_role  2-2
		employeeService.save(employee);
		
		Random random = new Random();
		int deptSize = departmentService.getAll().size(); 
		for(int i = 0;i<60;i++){
			//必须在循环里面实例化employee
			employee = new Employee("admin"+i,"admin"+i);
			employee.setEmail("admin@"+i+"hang.com");
			employee.setAge(23+i);
			//随机选择部门
			int m =  random.nextInt(deptSize); //0,1,2
			Long deptId = new Long(m+1);
			employee.setDepartment(new Department(deptId));
			employeeService.save(employee);
		}
		//System.out.println(employeeService);
	}
	
	//2.测试条件分页的查询
		@Test
		public void query() throws Exception{
//				EmployeeQuery baseQuery = new EmployeeQuery();
//				baseQuery.setCurrentPage(10);
//				baseQuery.setPageSize(5);
//				baseQuery.setName("a");
				//baseQuery.setEmail("a");
//				PageResult<Employee> pageResult = employeeService.findPageResult(baseQuery);
//				System.out.println(pageResult);
				//1.测试查询所有资源方法
			//	List<String> allMethods =	employeeService.getAllResourceMethods();
			 // System.out.println(allMethods);
			   //2.测试登陆用户拥有的资源方法
			   List<String> list = employeeService.findResourceMethodByLogin(1L);
			   for(String rString : list){
				   System.out.println(rString);
			   }
		}	
	

//	@Test
//	public void update() throws Exception{
//		Employee employee = new Employee();
//		employee.setName("admin2");
//		employee.setPassword("admin2");
//		employee.setId(2L);
//		employeeService.update(employee);
//	}

//	@Test
//	public void delete() throws Exception{
//		employeeService.delete(2L);
//	}
	
//	@Test
//	public void get() throws  Exception{
//		System.out.println(employeeService.get(1L));
//	}
	
//	@Test
//	public void getAll() throws Exception{
//		 List<Employee> employeeList =  employeeService.getAll();
//		 for(Employee employee : employeeList){
//			 System.out.println(employee);
//		 }
//	}
	
}
