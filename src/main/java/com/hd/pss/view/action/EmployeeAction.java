package com.hd.pss.view.action;

import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.map.HashedMap;
import org.apache.struts2.ServletActionContext;

import com.hd.pss.domain.Department;
import com.hd.pss.domain.Employee;
import com.hd.pss.domain.Role;
import com.hd.pss.page.EmployeeQuery;
import com.hd.pss.page.PageResult;
import com.hd.pss.service.IDepartmentService;
import com.hd.pss.service.IEmployeeService;
import com.hd.pss.service.IRoleService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com.sun.xml.internal.bind.v2.runtime.Name;

public class EmployeeAction extends CURDAction<Employee> {

	private IEmployeeService employeeService;
	private PageResult<Employee> pageResult;
	private EmployeeQuery baseQuery = new EmployeeQuery();
	private Employee employee;
	private IDepartmentService departmentService;
	// 注入角色列表
	private IRoleService roleService;
	private Long[] ids;  //角色列表ids

	public Employee getModel() {
		return employee;
	}

	// 父类进行了返回,列表
	@Override
	protected void list() throws Exception {
		logger.debug("list");
		// 添加部门的查询列表
		putContext("allDepts", departmentService.getAll());
		this.pageResult = employeeService.findPageResult(baseQuery);

	}

	// 跳转到添加页面和修改页面
	@Override
	public String input() throws Exception {
		logger.debug("input");
		// 添加一个部门的选择列表
		putContext("allDepts", departmentService.getAll());
		// 添加一个角色的选项列表
		putContext("allRoles", roleService.getAll());
		return INPUT;
	}

	// 保存操作或修改操作
	@Override
	// 如果出现转换异常，把字符串放到了Interger属性里面，默认会跳转到input指向的jsp页面
	// 设置@InputConfig(methodName="input")，出现转换异常则跳到input方法中
	@InputConfig(methodName = "input")
	public String save() throws Exception {
		logger.debug("save");
		// 请选择部门: 没有部门，部门==null
		Department department = employee.getDepartment();
		if (department != null && department.getId() == -1L) {
			employee.setDepartment(null);
		}
		// 添加中间表
		for (Long id : ids) {
			employee.getRoles().add(new Role(id));
		}

		// 保存或修改
		if (id == null) {
			employeeService.save(employee);
		} else {
			employeeService.update(employee);
			addActionMessage("修改成功");
			
		}
		return RELOAD;
	}

	// 删除
	// @Override
	// public String delete() throws Exception {
	// logger.debug("delete");
	// if (id != null) {
	// employeeService.delete(id);
	// addActionMessage("删除成功!");
	// }
	// return RELOAD;
	// }
	// 高级删除 : ajax
	@Override
	public String delete() throws Exception {
		logger.debug("delete");
		Map<String, Object> map = new HashMap<String,Object>();
		try {
			if (id != null) {
				employeeService.delete(id);
				map.put("success", true);
				map.put("msg", "删除成功");
				// addActionMessage("删除成功!"); 不能,ajax删除不能通过addActionMessage方法消息
			} else {
				map.put("success", false);
				map.put("msg", "不能获取id的值");
			}
		} catch (Exception e) {
			map.put("success", false);
			map.put("msg", e.getMessage());
		}
		putContext("map", map);
		return JSON;
	}

	// 这里是Preparable拦截器起了作用，会先执行下面的方法（prepareSave(),prepareInput()）
	@Override
	protected void beforeInput() throws Exception {
		logger.debug("beforeInput");
		if (id != null) {
			employee = employeeService.get(id); // 回显员工信息
			// 把employee.getRoles()变成ids回显
			ids = new Long[employee.getRoles().size()]; // 实例化ids的数组
			int index = 0;
			for (Role role : employee.getRoles()) {
				ids[index++] = role.getId();
			}
		}

	}

	@Override
	protected void beforeSave() throws Exception {
		logger.debug("beforeSave");
		if (id == null) {
			employee = new Employee(); // 实例化，防止为空,表示新建对象
		} else {
			// employee为持久化状态,持久化状态无法更改
			employee = employeeService.get(id); // 把数据从数据库加载出来，没有在jsp页面出现的属性不会丢失
			// employee.getDepartment();持久化状态
			// 改变状态：把持久状态变成瞬时状态
			employee.setDepartment(null);
			//清除员工的角色
			//employee.setRoles(null);    //java.lang.NullPointerException
			//employee.getRoles(new HashSet<Role>());   //浪费资源，重新new一个新的对象
			employee.getRoles().clear();	//清空集合中的值
		}
	}

	// 处理ajax验证，用户名不能重复:返回值是false就表示用户名重复了
	public String check() throws Exception {
		logger.debug("check:name= " + name);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		// 新增的处理和修改的处理是不同的 ：判断Id
		if (id == null) {
			out.print(employeeService.findByName(name)); // 新增
		} else {
			Employee employee = employeeService.get(id); // 获取修改的对象
			// 没有修改名字，不要验证用户名重复
			if (employee.getName().equals(name)) {
				out.print(true);
			} else {
				// 修改了，就进行查询验证
				out.print(employeeService.findByName(name));
			}
		}
		return null;
	}

	private String name; // 获取用户查询的名字

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	//导成xls文件
	public String download() throws Exception{
		String[] heads = { "编号", "用户名", "密码", "email", "年龄", "部门名称" };
		// 获取到查询的数据
		baseQuery.setPageSize(Integer.MAX_VALUE);// 一页装所有查询后数据
		this.pageResult = employeeService.findPageResult(baseQuery);// 默认一页10行
		List<Employee> employees = pageResult.getRows();
		List<String[]> list = new ArrayList<String[]>();
		for (Employee employee : employees) {
	    	String[] strings = new String[heads.length];  //一行的数据长度
			strings[0] = employee.getId().toString();
			strings[1] = employee.getName();
			strings[2] = employee.getPassword();
			strings[3] = employee.getEmail();
			strings[4] = employee.getAge().toString();
			strings[5] = employee.getDepartment().getName();
			list.add(strings);// 添加到List
		}
		this.inputStream = employeeService.export(heads, list);
		return "download";
	}
	
	private InputStream inputStream; //输入流
	
	// 乱码:ISO8859-1
	public String getFileName() throws UnsupportedEncodingException {
			return new String("员工列表.xls".getBytes("GBK"), "ISO8859-1");
	}

	public InputStream getInputStream() {
			return inputStream;
	}
	

	// set/get
	public IEmployeeService getEmployeeService() {
		return employeeService;
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

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public IDepartmentService getDepartmentService() {
		return departmentService;
	}

	public void setDepartmentService(IDepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	public IRoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(IRoleService roleService) {
		this.roleService = roleService;
	}

	public Long[] getIds() {
		return ids;
	}

	public void setIds(Long[] ids) {
		this.ids = ids;
	}

}
