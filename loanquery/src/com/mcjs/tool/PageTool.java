package com.mcjs.tool;

public class PageTool {

	/**
	 * 总记录数
	 */
	private int totalCount;

	/**
	 * 每页显示的记录条数
	 */
	private int pageSize = 10;

	/**
	 * 总页数
	 */
	private int totalPage;

	/**
	 * 当前页码
	 */
	private int currPage = 1;

	
	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getCurrPage() {
		return currPage;
	}

	public void setCurrPage(int currPage) {
		this.currPage = currPage;
	}

}
