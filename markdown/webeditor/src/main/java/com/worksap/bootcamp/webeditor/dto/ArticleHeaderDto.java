package com.worksap.bootcamp.webeditor.dto;

public final class ArticleHeaderDto {
	private String user;
	private String id;
	private String title;


	public ArticleHeaderDto() {
		this.user = "dummy";
	}


	public ArticleHeaderDto(String id, String title) {
		this.user = "dummy";
		this.id = id;
		this.title = title;
	}


	public String getId() {
		return id;
	}


	public String getTitle() {
		return title;
	}


	public void setId(String id) {
		this.id = id;
	}


	public void setTitle(String title) {
		this.title = title;
	}
}
