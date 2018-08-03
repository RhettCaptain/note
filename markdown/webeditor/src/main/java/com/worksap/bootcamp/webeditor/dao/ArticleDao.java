package com.worksap.bootcamp.webeditor.dao;

import java.util.Iterator;

import com.worksap.bootcamp.webeditor.vo.ArticleVo;
import com.worksap.bootcamp.webeditor.vo.ArticleHeaderVo;

public interface ArticleDao {
	/**
	 * Delete article(Header, Detail and Tag relations) with articleId
	 * 
	 * @param articleId
	 */
	void delete(String articleId);


	/**
	 * Return all article data(Detail with tags and title(String))
	 * 
	 * @param articleId
	 * @return
	 */
	ArticleVo find(String articleId);


	/**
	 * Generate new articleId
	 * 
	 * @return newId
	 */
	String generateNewId();


	/**
	 * Insert new article(Header, Detail and Tag relations)
	 * 
	 * @param newRecord
	 */
	void insert(ArticleVo newRecord);


	/**
	 * Return all header(title) list
	 * 
	 * @return
	 */
	Iterator<ArticleHeaderVo> list();


	/**
	 * Update article(Header, Detail and Tag relations)
	 * 
	 * @param articleId
	 */
	void update(ArticleVo newRecord);
}
