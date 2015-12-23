package com.hd.pss.page;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * 封装查询的所有结果 PageResult<BR>
 * 创建人:航大 <BR>
 * 时间：2015年10月21日-下午1:38:55 <BR>
 * 
 * @version 1.0.0
 *
 */
public class PageResult<T> {

	// 封装公共: 当前页码、一页条数
	private int currentPage;
	private int pageSize;
	// 总记录数
	private int totalCount;
	// 总的页数（计算）
	private int totalPage;
	// 当前页码的数据
	private List<T> rows = new ArrayList<T>(); // 直接初始化，在toString方法中调用rows.size()就不会出现null指针

	public PageResult() {  }

	// 构造函数（当前页码，每页容量，总记录数）
	public PageResult(int currentPage, int pageSize, int totalCount) {
		// 1.做错误处理  以下的代码必须加上this
		this.currentPage = currentPage < 1 ? 1 : currentPage;
		this.pageSize = pageSize < 1 ? 10 : pageSize;
		this.totalCount = totalCount;
		// 2.计算总页数  
//		if(totalCount%pageSize == 0){
//			this.currentPage = this.totalCount/this.pageSize;
//		}else{
//			this.currentPage = this.totalCount/this.pageSize + 1;
//		}
		//110  10  11    110 + 10 -1 = 119/10 = 11(除不尽，余数去掉)
		//101  10  11    101 + 10 -1 = 110/10 = 11
		this.totalPage = (this.totalCount + this.pageSize -1) / this.pageSize;  // 分页页数
		// 3.当前页码大于总的页数必须在最后面处理
		this.currentPage = this.currentPage >= this.totalPage ? this.totalPage : this.currentPage;
	}

	// get/set
	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	@Override
	public String toString() {
		return "PageResult [currentPage=" + currentPage + ", pageSize="
				+ pageSize + ", totalCount=" + totalCount + ", totalPage="
				+ totalPage + ", rows.size()=" + rows.size() + "]";
	}

}
