package com.hd.pss.view.action;


import org.apache.struts2.ServletActionContext;

import com.hd.pss.domain.Employee;
import com.hd.pss.service.IEmployeeService;

//处理登陆
public class LoginAction extends BaseAction {

	private IEmployeeService employeeService;
	private String name;
	private String password;

	@Override
	public String execute() throws Exception {
		return LOGIN;  //ActionSupport中为静态常量 login
	}
	
	public String check() throws Exception{
		Employee employee =  employeeService.findByLogin(name,password);
		if(employee != null){
			// 放入session,重定向跳转到后台主页(不能加双引号)
			ServletActionContext.getRequest().getSession().setAttribute(LOGIN_IN_SESSION, employee);
			//ServletActionContext.getRequest().getSession().setAttribute("LOGIN_IN_SESSION", employee);
		}
		return MAIN;
	}

	// set/get
	public void setEmployeeService(IEmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
