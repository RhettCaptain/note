package com.worksap.bootcamp.webeditor.dao;

public interface DaoFactory {
	ArticleDao getArticleDao();
	TagDao getTagDao();
}
