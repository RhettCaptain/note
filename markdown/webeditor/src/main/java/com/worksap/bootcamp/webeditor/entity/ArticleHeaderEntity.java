package com.worksap.bootcamp.webeditor.entity;

import java.util.List;

public class ArticleHeaderEntity {
	private String id;
	private String title;
	private List<String> tags;


	public ArticleHeaderEntity(String id, String title, List<String> tags) {
		super();
		this.id = id;
		this.title = title;
		this.tags = tags;
	}


	public String getId() {
		return id;
	}


	public String getTitle() {
		return title;
	}


	public List<String> getTags() {
		return tags;
	}


	public void setId(String id) {
		this.id = id;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public void setTag(List<String> tags) {
		this.tags = tags;
	}
}
