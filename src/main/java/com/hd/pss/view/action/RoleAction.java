package com.hd.pss.view.action;

import java.io.PrintWriter;
import java.util.Arrays;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.hd.pss.domain.Resource;
import com.hd.pss.domain.Role;
import com.hd.pss.page.RoleQuery;
import com.hd.pss.page.PageResult;
import com.hd.pss.service.IResourceService;
import com.hd.pss.service.IRoleService;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;

public class RoleAction extends CURDAction<Role> {
	private IRoleService roleService;
	private PageResult<Role> pageResult;
	private RoleQuery baseQuery = new RoleQuery();
	private Role role;
	// 注入resources
	private IResourceService resourceService;
	// 接受角色资源列表选项
	private Long[] ids;

	@Override
	protected void list() throws Exception {
		logger.debug("list");
		// 添加一个xx的查询列表
		// putContext("allDepts", departmentService.getAll());
		this.pageResult = roleService.findPageResult(baseQuery);
	}

	@Override
	public String input() throws Exception {
		logger.debug("input");
		// 添加一个资源选择列表
		putContext("allResources", resourceService.getAll());
		return INPUT;
	}

	@Override
	// 保存的时候:如果出现转换异常:把字符串放到了Integer属性里面 ,默认会跳转到input指向的jsp页面
	@InputConfig(methodName = "input")
	public String save() throws Exception {
		logger.debug("save" + Arrays.toString(ids));

		// 添加中间表的数据(涉及资源表)
		for (Long id : ids) {
			role.getResources().add(new Resource(id));
		}
		// 存在id就更新，不存在就保存
		if (id == null) {
			roleService.save(role);
		} else {
			roleService.update(role);
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
				roleService.delete(id);
				out.print("{\"success\":true,\"msg\":\"删除成功\"}");
				// addActionMessage("删除成功");不能,ajax删除是不能通过addActionMessage方法消息
			} else {
				out.print("{\"success\":false,\"msg\":\"不能获取到id的值\"}");
			}
		} catch (Exception e) {
			out.print("{\"success\":false,\"msg\":\"" + e.getMessage() + "\"}");
		}
		return null;
	}

	@Override
	protected void beforeInput() throws Exception {
		logger.debug("beforeInput");
		if (id != null) {
			role = roleService.get(id);// 回显
			// 把role.getResources()变成ids显示
			// 定义数组的长度
			ids = new Long[role.getResources().size()];
			// 赋值
			int index = 0;
			for (Resource resource : role.getResources()) {
				ids[index++] = resource.getId();
			}
		}
	}

	@Override
	protected void beforeSave() throws Exception {
		logger.debug("beforeSave");
		if (id == null) {
			role = new Role();
		} else {
			// role持久状态
			role = roleService.get(id);// 把数据从数据库加载出来,没有在jsp页面出现的属性不会丢失
			role.getResources().clear(); // 解决修改后的保存
		}
	}

	//
	public RoleQuery getBaseQuery() {
		return baseQuery;
	}

	public void setBaseQuery(RoleQuery baseQuery) {
		this.baseQuery = baseQuery;
	}

	public PageResult<Role> getPageResult() {
		return pageResult;
	}

	public void setRoleService(IRoleService roleService) {
		this.roleService = roleService;
	}

	public Role getModel() {
		return role;
	}

	public Long[] getIds() {
		return ids;
	}

	public void setIds(Long[] ids) {
		this.ids = ids;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public IRoleService getRoleService() {
		return roleService;
	}

	public void setPageResult(PageResult<Role> pageResult) {
		this.pageResult = pageResult;
	}

	public void setResourceService(IResourceService resourceService) {
		this.resourceService = resourceService;
	}

}
