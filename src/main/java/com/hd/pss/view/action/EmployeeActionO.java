package com.hd.pss.view.action;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.hd.pss.domain.Employee;
import com.hd.pss.page.EmployeeQuery;
import com.hd.pss.page.PageResult;
import com.hd.pss.service.IEmployeeService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class EmployeeActionO extends ActionSupport implements ModelDriven<Employee>,Preparable{

	private static final long serialVersionUID = 1L;

	private IEmployeeService employeeService;
	//默认情况下设置为空
	private Employee employee;
	private Long id;
	// private List<Employee> employees ; 测试结果集
	// 分页后数据的所有封装
	private PageResult<Employee> pageResult;
	// 查询条件的封装 ：回显和获取值 【需要初始化，获取查询默认值】
	private EmployeeQuery baseQuery = new EmployeeQuery();

	// 列表
	public String list() throws Exception {
		// this.employees = employeeService.getAll();
		this.pageResult = employeeService.findPageResult(baseQuery);
		return "list";
	}

	// 删除
	public String delete() throws Exception {
		if (id != null) {
			employeeService.delete(id);
		}
		return "toList";
	}

	// 添加页面和修改页面
	public String edit() throws Exception {
		if (id!= null) {
			employee = employeeService.get(id);
		}
		return "edit";
	}

	// 处理添加和修改
	public String save() throws Exception {
		if (id == null) {
			employeeService.save(employee); // 新增
		} else {
			employeeService.update(employee);
		}
		return "toList";
	}


	//只要访问action里面任意一个方法都会先执行	
	public void prepare() throws Exception {
		System.out.println("prepare : " + id);
	}
	//action.edit方法
	public void prepareEdit() throws Exception {
		System.out.println("prepareEdit");
		if(id != null){
			employee = employeeService.get(id);
		}
	}
	
	//action.save方法
	public void prepareSave() throws Exception {
		System.out.println("prepareSave");
		if(id != null){
			employee = employeeService.get(id);//持久化对象
		}else{
			employee = new Employee();
		}
	}
	//ModelDriven  将不为null的对象设置为栈顶对象
	public Employee getModel() {
		return employee;
	}
	
	
	// get/set
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public void setEmployeeService(IEmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	public PageResult<Employee> getPageResult() {
		return pageResult;
	}

	public void setPageResult(PageResult<Employee> pageResult) {
		this.pageResult = pageResult;
	}

	public EmployeeQuery getBaseQuery() {
		return baseQuery;
	}

	public void setBaseQuery(EmployeeQuery baseQuery) {
		this.baseQuery = baseQuery;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
		System.out.println("EmployeeAction setId :" + id);
	}


}
