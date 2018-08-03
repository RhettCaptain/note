package com.worksap.bootcamp.webeditor.dao;

import java.util.Iterator;

import org.springframework.jdbc.core.JdbcTemplate;

import com.worksap.bootcamp.webeditor.vo.ArticleHeaderVo;
import com.worksap.bootcamp.webeditor.vo.ArticleVo;


final class ArticleDaoJdbcImpl implements ArticleDao {
	private static final String SELECT_SQL =
			   "SELECT AH.TITLE, AD.CONTENT"
			+ " FROM ARTICLE_HEADER AH INNER JOIN ARTICLE_DETAIL AD ON AH.ID = AD.ID"
			+ " WHERE AH.ID = ?";

	private JdbcTemplate template;


	@Override
	public void delete(String articleId) {
		// TODO Auto-generated method stub
	}


	@Override
	public ArticleVo find(String articleId) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String generateNewId() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void insert(ArticleVo newRecord) {
		// TODO Auto-generated method stub
	}


	@Override
	public Iterator<ArticleHeaderVo> list() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void update(ArticleVo newRecord) {
		// TODO Auto-generated method stub
	}
}
