package com.worksap.bootcamp.webeditor.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.worksap.bootcamp.webeditor.entity.ArticleEntity;
import com.worksap.bootcamp.webeditor.entity.ArticleHeaderEntity;


/**
 * Treat all request for articles.
 * 
 * @author works
 */
@RequestMapping(value="/webeditor/article")
public class ArticleController {
	/**
	 * Add new article.
	 * (But, client send add request when add button is clicked. 'detail' has many empty fields)
	 * 
	 * @param detail This detail does not have articleId
	 * @return This detail has articleId
	 */
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public ArticleEntity add(ArticleEntity detail) {
		// TODO Auto-generated method stub
		return null;
	}


	/**
	 * Want to get article title list.
	 * (Paging is not required.
	 *  Be carefully! client send async request.
	 *  If you use H2 embedded mode, H2 will return error.
	 *  Please use H2 server mode)
	 * 
	 * @return
	 */
	@RequestMapping(value="/load", method = RequestMethod.GET)
	public List<ArticleHeaderEntity> load() {
		// TODO Auto-generated method stub
		return null;
	}


	/**
	 * Update article to new one.
	 * Search old one with detail.articleId.
	 * 
	 * @param detail
	 */
	@RequestMapping(value="/put", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public void put(ArticleEntity detail) {
		// TODO Auto-generated method stub
	}


	/**
	 * Delete article with articleId
	 * 
	 * @param articleId
	 */
	@RequestMapping(value="/delete/{articleId}", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public void delete(String articleId) {
		// TODO Auto-generated method stub
	}


	/**
	 * Get all data with articleId
	 * 
	 * @param articleId
	 * @return
	 */
	@RequestMapping(value="/detail/{articleId}", method = RequestMethod.GET)
	public ArticleEntity getDetail(String articleId) {
		// TODO Auto-generated method stub
		return null;
	}
}
