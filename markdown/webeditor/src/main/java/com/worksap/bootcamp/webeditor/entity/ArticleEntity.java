package com.worksap.bootcamp.webeditor.entity;

import java.util.List;


/**
 * ArticleDetail has all data in article form.
 * So, it has title as string.
 * Because 'Entity' can has setter(in HUE), it should be used little.
 * Only controller need to use it because of JSON serialization(and deserialization).
 * 
 * @author works
 */
public class ArticleEntity {
	private String id;
	private String content;
	private String title;
	private List<String> tags;


	public ArticleEntity(String id, String content, String title, List<String> tags) {
		super();
		this.id = id;
		this.content = content;
		this.title = title;
		this.tags = tags;
	}


	public String getId() {
		return id;
	}


	public String getContent() {
		return content;
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


	public void setContent(String content) {
		this.content = content;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public void setTags(List<String> tags) {
		this.tags = tags;
	}
}
