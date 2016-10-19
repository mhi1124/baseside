package com.xxfeii.baseside.modules.es.utils;

public class PageUtil {
	
	/**
     * 页码，从1开始
     */
    private int pageNum;
    /**
     * 页面大小
     */
    private int pageSize;
    /**
     * 排序字段
     */
    private String orderByColumn;
	/**
	 * 排序方式
	 */
    private String orderByType;
    
	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getOrderByColumn() {
		return orderByColumn;
	}

	public void setOrderByColumn(String orderByColumn) {
		this.orderByColumn = orderByColumn;
	}

	public String getOrderByType() {
		return orderByType;
	}

	public void setOrderByType(String orderByType) {
		this.orderByType = orderByType;
	}

	/**
	 * 获取总页数
	 * 
	 * @param totalCount
	 * @param pageSize
	 * @return
	 */
	public static int getTotalPage(int totalCount, int pageSize) {
		return (int) Math.ceil(Double.valueOf(totalCount) / pageSize);
	}

	/**
	 * 包含，起始索引为0
	 * 
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public static int getFromIndex(int currentPage, int pageSize) {
		return (currentPage - 1) * pageSize;
	}
}
