package com.hd.pss.view.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import java.util.HashMap;
import java.util.Map;
import com.hd.pss.domain.${domain};
import com.hd.pss.page.${domain}Query;
import com.hd.pss.page.PageResult;
import com.hd.pss.service.I${domain}Service;

import com.opensymphony.xwork2.interceptor.annotations.InputConfig;

public class ${domain}Action extends CURDAction<${domain}> {
	private I${domain}Service ${lowerDomain}Service;
	private PageResult<${domain}> pageResult;
	private ${domain}Query baseQuery = new ${domain}Query();
	private ${domain} ${lowerDomain};

	public ${domain}Query getBaseQuery() {
		return baseQuery;
	}

	public void setBaseQuery(${domain}Query baseQuery) {
		this.baseQuery = baseQuery;
	}

	public PageResult<${domain}> getPageResult() {
		return pageResult;
	}

	public void set${domain}Service(I${domain}Service ${lowerDomain}Service) {
		this.${lowerDomain}Service = ${lowerDomain}Service;
	}

	public ${domain} getModel() {
		return ${lowerDomain};
	}

	@Override
	protected void list() throws Exception {
		logger.debug("list");
		// 添加一个xx的查询列表
//		putContext("allDepts", departmentService.getAll());
		this.pageResult = ${lowerDomain}Service.findPageResult(baseQuery);
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
			${lowerDomain}Service.save(${lowerDomain});
		} else {
			${lowerDomain}Service.update(${lowerDomain});
		}
		addActionMessage("保存成功");
		return RELOAD;
	}

	// ajax删除
	@Override
	public String delete() throws Exception {
		logger.debug("delete");
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if (id != null) {
				${lowerDomain}Service.delete(id);
				map.put("success", true);
				map.put("msg", "删除成功");
			} else {
				map.put("success", false);
				map.put("msg", "不能获取到id的值");
			}
		} catch (Exception e) {
			map.put("success", false);
			map.put("msg", e.getMessage());
		}
		putContext("map", map);
		return JSON;
	}

	@Override
	protected void beforeInput() throws Exception {
		logger.debug("beforeInput");
		if (id != null) {
			${lowerDomain} = ${lowerDomain}Service.get(id);// 回显
		}
	}

	@Override
	protected void beforeSave() throws Exception {
		logger.debug("beforeSave");
		if (id == null) {
			${lowerDomain} = new ${domain}();
		} else {
			// ${lowerDomain}持久状态
			${lowerDomain} = ${lowerDomain}Service.get(id);// 把数据从数据库加载出来,没有在jsp页面出现的属性不会丢失
		}
	}	

}
