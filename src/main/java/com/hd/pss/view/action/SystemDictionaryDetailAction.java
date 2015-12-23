package com.hd.pss.view.action;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.hd.pss.domain.SystemDictionaryDetail;
import com.hd.pss.page.SystemDictionaryDetailQuery;
import com.hd.pss.page.PageResult;
import com.hd.pss.service.ISystemDictionaryDetailService;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;

public class SystemDictionaryDetailAction extends CURDAction<SystemDictionaryDetail> {
	private ISystemDictionaryDetailService systemDictionaryDetailService;
	private PageResult<SystemDictionaryDetail> pageResult;
	private SystemDictionaryDetailQuery baseQuery = new SystemDictionaryDetailQuery();
	private SystemDictionaryDetail systemDictionaryDetail;

	public SystemDictionaryDetailQuery getBaseQuery() {
		return baseQuery;
	}

	public void setBaseQuery(SystemDictionaryDetailQuery baseQuery) {
		this.baseQuery = baseQuery;
	}

	public PageResult<SystemDictionaryDetail> getPageResult() {
		return pageResult;
	}

	public void setSystemDictionaryDetailService(ISystemDictionaryDetailService systemDictionaryDetailService) {
		this.systemDictionaryDetailService = systemDictionaryDetailService;
	}

	public SystemDictionaryDetail getModel() {
		return systemDictionaryDetail;
	}

	@Override
	protected void list() throws Exception {
		logger.debug("list");
		// 添加一个xx的查询列表
//		putContext("allDepts", departmentService.getAll());
		this.pageResult = systemDictionaryDetailService.findPageResult(baseQuery);
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
			systemDictionaryDetailService.save(systemDictionaryDetail);
		} else {
			systemDictionaryDetailService.update(systemDictionaryDetail);
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
				systemDictionaryDetailService.delete(id);
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
			systemDictionaryDetail = systemDictionaryDetailService.get(id);// 回显
		}
	}

	@Override
	protected void beforeSave() throws Exception {
		logger.debug("beforeSave");
		if (id == null) {
			systemDictionaryDetail = new SystemDictionaryDetail();
		} else {
			// systemDictionaryDetail持久状态
			systemDictionaryDetail = systemDictionaryDetailService.get(id);// 把数据从数据库加载出来,没有在jsp页面出现的属性不会丢失
		}
	}	

}
