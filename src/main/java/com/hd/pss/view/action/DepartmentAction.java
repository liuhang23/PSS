package com.hd.pss.view.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.hd.pss.domain.Department;
import com.hd.pss.page.DepartmentQuery;
import com.hd.pss.page.PageResult;
import com.hd.pss.service.IDepartmentService;

import com.opensymphony.xwork2.interceptor.annotations.InputConfig;

public class DepartmentAction extends CURDAction<Department> {
	private IDepartmentService departmentService;
	private PageResult<Department> pageResult;
	private DepartmentQuery baseQuery = new DepartmentQuery();
	private Department department;


	@Override
	protected void list() throws Exception {
		logger.debug("list");
		// 添加一个xx的查询列表
//		putContext("allDepts", departmentService.getAll());
		this.pageResult = departmentService.findPageResult(baseQuery);
	}

	@Override
	public String input() throws Exception {
		logger.debug("input");
		// 添加一个xx的选择列表
//		putContext("allDepts", departmentService.getAll());
		return INPUT;
	}

	@Override
	// 保存的时候:如果出现转换异常:把字符串放到了Integer属性里面 ,默认会跳转到input指向的jsp页面
	@InputConfig(methodName = "input")
	public String save() throws Exception {
		logger.debug("save");
		
		if (id == null) {
			departmentService.save(department);
		} else {
			departmentService.update(department);
		}
		addActionMessage("保存成功");
		return RELOAD;
	}

	// ajax删除
	@Override
	public String delete() throws Exception {
		logger.debug("delete");
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		try {
			if (id != null) {
				departmentService.delete(id);
				out.print("{\"success\":true,\"msg\":\"删除成功\"}");
				// addActionMessage("删除成功");不能,ajax删除是不能通过addActionMessage方法消息
			} else {
				out.print("{\"success\":false,\"msg\":\"不能获取到id的值\"}");
			}
		} catch (Exception e) {
			out.print("{\"success\":false,\"msg\":\""+e.getMessage()+"\"}");
		}
		return null;
	}

	@Override
	protected void beforeInput() throws Exception {
		logger.debug("beforeInput");
		if (id != null) {
			department = departmentService.get(id);// 回显
		}
	}

	@Override
	protected void beforeSave() throws Exception {
		logger.debug("beforeSave");
		if (id == null) {
			department = new Department();
		} else {
			// department持久状态
			department = departmentService.get(id);// 把数据从数据库加载出来,没有在jsp页面出现的属性不会丢失
		}
	}	
	
	//set/get
	public void setDepartmentService(IDepartmentService departmentService) {
		this.departmentService = departmentService;
	}
	
	public DepartmentQuery getBaseQuery() {
		return baseQuery;
	}

	public void setBaseQuery(DepartmentQuery baseQuery) {
		this.baseQuery = baseQuery;
	}

	public PageResult<Department> getPageResult() {
		return pageResult;
	}

	

	public Department getModel() {
		return department;
	}

}
