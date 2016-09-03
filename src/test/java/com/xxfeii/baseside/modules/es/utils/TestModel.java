package com.xxfeii.baseside.modules.es.utils;

import java.util.Date;

import com.xxfeii.baseside.modules.es.entity.EsBaseModel;

public class TestModel extends EsBaseModel {
	private String title;
	private Date posttime;
	private String content;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getPosttime() {
		return posttime;
	}
	public void setPosttime(Date posttime) {
		this.posttime = posttime;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}
