package com.xxfeii.baseside.modules.sys.utils;

import com.xxfeii.baseside.common.entity.BaseEntity;

/**
 * 分页工具类
 * @ClassName PagerUtil
 * @Description
 * @author davi
 * @date 2016年8月21日
 *
 */
public class PagerUtil extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 记录总数
	 */
	private int recordCount;
	
	/**
	 * 总页数
	 */
	private int pageCount;
	/**
	 * 页面显示的数量
	 */
	private int pageSize;
	/**
	 * 开始的行数
	 */
	private int startRow;
	/**
	 * 当前页数
	 */
	private int nowPage;
	
	public PagerUtil() {
		super();
	}
	public PagerUtil(int recordCount, int pageSize,int nowPage) {
		super();
		this.recordCount = recordCount;
		this.pageSize = pageSize;
		this.nowPage = nowPage;
	}
	public int getRecordCount() {
		return recordCount;
	}
	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}
	public int getPageCount() {
		pageCount = (int)Math.ceil((double)recordCount/pageSize);
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getStartRow() {
		startRow = pageSize*nowPage;
		return startRow;
	}
	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}
	public int getNowPage() {
		return nowPage;
	}
	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}
	
	
}
