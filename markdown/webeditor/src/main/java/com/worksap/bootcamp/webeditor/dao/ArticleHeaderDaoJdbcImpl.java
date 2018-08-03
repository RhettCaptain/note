package com.worksap.bootcamp.webeditor.dao;

import java.util.List;

import com.worksap.bootcamp.webeditor.dto.ArticleHeaderDto;


final class ArticleHeaderDaoJdbcImpl {
	private static final String DELETE_SQL = "DELETE FROM ARTICLE_HEADER WHERE ID = ?";
	private static final String INSERT_SQL = "INSERT INTO ARTICLE_HEADER(ID, TITLE) VALUES (?, ?)";
	private static final String LIST_SQL = "SELECT ID, TITLE FROM ARTICLE_HEADER ORDER BY ID";
	private static final String UPDATE_SQL = "UPDATE ARTICLE_HEADER SET TITLE = ? WHERE ID = ?";


	void delete(ArticleHeaderDto record) {
		// TODO Implement it!
	}


	void insert(ArticleHeaderDto newRecord) {
		// TODO Implement it!
	}


	List<ArticleHeaderDto> list() {
		// TODO Implement it!
		return null;
	}


	void update(ArticleHeaderDto newRecord) {
		// TODO Implement it!
	}
}
