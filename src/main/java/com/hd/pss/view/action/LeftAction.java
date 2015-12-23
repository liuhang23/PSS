package com.hd.pss.view.action;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.hd.pss.domain.Employee;
import com.hd.pss.domain.Menu;
import com.hd.pss.service.IMenuService;


public class LeftAction extends BaseAction{
	private IMenuService menuService;
	//获取前段json传入的id
	private Long id;
	
	public void setMenuService(IMenuService menuService) {
		this.menuService = menuService;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	//ajax返回一个json数据 ： 返回的字符串null或者NONE
	@Override
	public String execute() throws Exception{
//		HttpServletResponse response = ServletActionContext.getResponse();
//		response.setContentType("text/json;charset=UTF-8");
//		PrintWriter out = response.getWriter();
//		String json = "{\"id\":200,\"name\":\"系统模块\",\"isParent\":\"true\"}";
//		out.print(json);
		Employee employee = (Employee)ServletActionContext.getRequest().getSession().getAttribute(LOGIN_IN_SESSION);
		List<Menu> menus = null;
		//一级菜单
		if(id == null){
			menus = menuService.findParentMenusByLogin(employee);
		}else{
			//二级菜单
			menus = menuService.findChildrenMenusByLogin(employee, id);
		}
		//把menus转换成json输出
		putContext("menus", menus);
		return JSON;
	}
	
//  测试使用	
//	public String getName(){
//		return "系统模块";
//	}
	
}
