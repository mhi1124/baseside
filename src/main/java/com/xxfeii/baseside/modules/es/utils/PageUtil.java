package com.xxfeii.baseside.modules.es.utils;

public class PageUtil {
	
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
