package com.worksap.bootcamp.webeditor.dto;

public class ArticleTagRelationDto {
	private String articleId;
	private String tagId;


	public ArticleTagRelationDto() {}


	public ArticleTagRelationDto(String articleId, String tagId) {
		this.articleId = articleId;
		this.tagId = tagId;
	}


	public String getArticleId() {
		return articleId;
	}


	public String getTagId() {
		return tagId;
	}


	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}


	public void setTagId(String tagId) {
		this.tagId = tagId;
	}
}
