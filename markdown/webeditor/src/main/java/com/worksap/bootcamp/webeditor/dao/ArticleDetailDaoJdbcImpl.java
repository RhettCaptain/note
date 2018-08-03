package com.worksap.bootcamp.webeditor.dao;

import com.worksap.bootcamp.webeditor.dto.ArticleDetailDto;


final class ArticleDetailDaoJdbcImpl {
	private static final String DELETE_SQL = "DELETE FROM ARTICLE_DETAIL WHERE ID = ?";
	private static final String INSERT_SQL = "INSERT INTO ARTICLE_DETAIL(ID, CONTENT) VALUES (?, ?)";
	private static final String UPDATE_SQL = "UPDATE ARTICLE_DETAIL SET CONTENT = ? WHERE ID = ?";


	void delete(ArticleDetailDto record) {
		// TODO Implement it!
	}


	void insert(ArticleDetailDto newRecord) {
		// TODO Implement it!
	}


	void update(ArticleDetailDto newRecord) {
		// TODO Implement it!
	}
}
