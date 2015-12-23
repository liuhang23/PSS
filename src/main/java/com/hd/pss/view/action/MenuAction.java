package com.hd.pss.view.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.hd.pss.domain.Menu;
import com.hd.pss.page.MenuQuery;
import com.hd.pss.page.PageResult;
import com.hd.pss.service.IMenuService;

import com.opensymphony.xwork2.interceptor.annotations.InputConfig;

public class MenuAction extends CURDAction<Menu> {
	private IMenuService menuService;
	private PageResult<Menu> pageResult;
	private MenuQuery baseQuery = new MenuQuery();
	private Menu menu;

	public MenuQuery getBaseQuery() {
		return baseQuery;
	}

	public void setBaseQuery(MenuQuery baseQuery) {
		this.baseQuery = baseQuery;
	}

	public PageResult<Menu> getPageResult() {
		return pageResult;
	}

	public void setMenuService(IMenuService menuService) {
		this.menuService = menuService;
	}

	public Menu getModel() {
		return menu;
	}

	@Override
	protected void list() throws Exception {
		logger.debug("list");
		// 添加一个xx的查询列表
//		putContext("allDepts", departmentService.getAll());
		this.pageResult = menuService.findPageResult(baseQuery);
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
			menuService.save(menu);
		} else {
			menuService.update(menu);
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
				menuService.delete(id);
				
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
			menu = menuService.get(id);// 回显
		}
	}

	@Override
	protected void beforeSave() throws Exception {
		logger.debug("beforeSave");
		if (id == null) {
			menu = new Menu();
		} else {
			// menu持久状态
			menu = menuService.get(id);// 把数据从数据库加载出来,没有在jsp页面出现的属性不会丢失
		}
	}	

}
