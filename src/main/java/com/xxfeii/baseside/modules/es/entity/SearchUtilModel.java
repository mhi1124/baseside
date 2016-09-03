package com.xxfeii.baseside.modules.es.entity;

import java.util.List;

import org.elasticsearch.client.Client;

public class SearchUtilModel extends EsBaseModel{
	private Client client;
	/**
	 * 索引字段
	 */
	private String[] filds;
	/**
	 * 索引类型
	 */
	private String[] types;
	/**
	 * 高亮
	 */
	private boolean hightShow;
	/**
	 * 高亮字段
	 */
	private String[] hightFilds;
	/**
	 * 索引名称
	 */
	private String indexName;
	/**
	 * 返回开始的条数
	 */
	private int pageNo; 
	/**
	 * 返回数量
	 */
	private int pageSize;
	/**
	 * 搜索到的数量
	 */
	private long totalCount;
	/**
	 * 搜索结果集
	 */
	private List<String> searchResults;
	/**
	 * 搜索内容
	 */
	private String text;
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public String[] getFilds() {
		return filds;
	}
	public void setFilds(String[] filds) {
		this.filds = filds;
	}
	public String[] getTypes() {
		return types;
	}
	public void setTypes(String[] types) {
		this.types = types;
	}
	public boolean isHightShow() {
		return hightShow;
	}
	public void setHightShow(boolean hightShow) {
		this.hightShow = hightShow;
	}
	
	public String[] getHightFilds() {
		return hightFilds;
	}
	public void setHightFilds(String[] hightFilds) {
		this.hightFilds = hightFilds;
	}
	public String getIndexName() {
		return indexName;
	}
	public void setIndexName(String indexName) {
		this.indexName = indexName;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}
	public List<String> getSearchResults() {
		return searchResults;
	}
	public void setSearchResults(List<String> searchResults) {
		this.searchResults = searchResults;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	
}
