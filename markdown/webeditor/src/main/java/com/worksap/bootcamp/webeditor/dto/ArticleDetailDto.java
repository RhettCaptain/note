package com.worksap.bootcamp.webeditor.dto;


public final class ArticleDetailDto {
	private String id;
	private String content;


	public ArticleDetailDto() {}


	public ArticleDetailDto(String id, String content) {
		this.id = id;
		this.content = content;
	}


	public String getId() {
		return id;
	}


	public String getContent() {
		return content;
	}


	public void setId(String id) {
		this.id = id;
	}


	public void setContent(String content) {
		this.content = content;
	}
}
