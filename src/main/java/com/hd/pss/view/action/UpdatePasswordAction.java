package com.hd.pss.view.action;

import org.apache.struts2.ServletActionContext;

import com.hd.pss.domain.Employee;
import com.hd.pss.service.IEmployeeService;

public class UpdatePasswordAction extends BaseAction {

	private IEmployeeService employeeService;

	public void setEmployeeService(IEmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	// 显示修改密码页面
	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}

	// 处理修改密码的请求
	public String update() {
		//先判断旧密码必须输入正确
		Employee employee = (Employee)ServletActionContext.getRequest().getSession().getAttribute(LOGIN_IN_SESSION);
		System.out.println(employee.getPassword().equals(oldPassword));
		if(!employee.getPassword().equals(oldPassword)){
			addActionError("旧密码输入错误");
			return SUCCESS;  //跳转到修改密码页面
		}
		employee.setPassword(newPassword);
		employeeService.update(employee);
		//将session移除
		ServletActionContext.getRequest().getSession().removeAttribute(LOGIN_IN_SESSION);
		return LOGIN; //修改成功则跳转到登陆页面，重新登陆
	}

	private String oldPassword;
	private String newPassword;

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

}
