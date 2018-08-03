package com.worksap.bootcamp.webeditor.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;


@Qualifier
class DaoFactoryJdbcImpl implements DaoFactory {
	private final JdbcTemplate template;


	/*
	 * JdbcTemplate is thread safe, so it will be Singleton
	 */
	@Autowired
	public DaoFactoryJdbcImpl(JdbcTemplate template) {
		this.template = template;
	}


	@Override
	public ArticleDao getArticleDao() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public TagDao getTagDao() {
		/*
		 * TagDaoJdbcImpl also thread safe.
		 * But it is DAO every developer make and modify it.
		 * So, I take safe way(create each instance).
		 */
		return new TagDaoJdbcImpl(template);
	}
}
