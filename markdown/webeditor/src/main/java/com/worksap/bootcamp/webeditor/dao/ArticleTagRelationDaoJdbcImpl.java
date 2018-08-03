package com.worksap.bootcamp.webeditor.dao;

import java.util.List;

import com.worksap.bootcamp.webeditor.dto.ArticleTagRelationDto;


final class ArticleTagRelationDaoJdbcImpl {
	private static final String DELETE_SQL = "DELETE ARTICLE_TAG_RELATION WHERE ARTICLE_ID = ?";
	private static final String INSERT_SQL = "INSERT INTO ARTICLE_TAG_RELATION(ARTICLE_ID, TAG_ID) VALUES (?, ?)";
	private static final String LIST_SQL = "SELECT ARTICLE_ID, TAG_ID FROM ARTICLE_TAG_RELATION ORDER BY ARTICLE_ID";
	private static final String LIST_FOR_ARTICLE_SQL = "SELECT TAG_ID FROM ARTICLE_TAG_RELATION WHERE ARTICLE_ID = ?";


	void addTags(String articleId, List<String> tags) {
		// TODO Auto-generated method stub
	}


	void deleteTags(String articleId) {
		// TODO Auto-generated method stub
	}


	List<ArticleTagRelationDto> list() {
		// TODO Auto-generated method stub
		return null;
	}


	List<String> listForArticle(String articleId) {
		// TODO Auto-generated method stub
		return null;
	}
}
