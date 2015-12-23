package com.hd.pss.view.action;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.hd.pss.domain.Supplier;
import com.hd.pss.page.SupplierQuery;
import com.hd.pss.page.PageResult;
import com.hd.pss.service.ISupplierService;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;

public class SupplierAction extends CURDAction<Supplier> {
	private ISupplierService supplierService;
	private PageResult<Supplier> pageResult;
	private SupplierQuery baseQuery = new SupplierQuery();
	private Supplier supplier;

	public SupplierQuery getBaseQuery() {
		return baseQuery;
	}

	public void setBaseQuery(SupplierQuery baseQuery) {
		this.baseQuery = baseQuery;
	}

	public PageResult<Supplier> getPageResult() {
		return pageResult;
	}

	public void setSupplierService(ISupplierService supplierService) {
		this.supplierService = supplierService;
	}

	public Supplier getModel() {
		return supplier;
	}

	@Override
	protected void list() throws Exception {
		logger.debug("list");
		// 添加一个xx的查询列表
//		putContext("allDepts", departmentService.getAll());
		this.pageResult = supplierService.findPageResult(baseQuery);
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
			supplierService.save(supplier);
		} else {
			supplierService.update(supplier);
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
				supplierService.delete(id);
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
			supplier = supplierService.get(id);// 回显
		}
	}

	@Override
	protected void beforeSave() throws Exception {
		logger.debug("beforeSave");
		if (id == null) {
			supplier = new Supplier();
		} else {
			// supplier持久状态
			supplier = supplierService.get(id);// 把数据从数据库加载出来,没有在jsp页面出现的属性不会丢失
		}
	}	

}
