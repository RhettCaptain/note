package com.worksap.bootcamp.webeditor.service;

import java.util.List;

import com.worksap.bootcamp.webeditor.vo.ArticleVo;
import com.worksap.bootcamp.webeditor.vo.ArticleHeaderVo;

public interface ArticleService {
	/*
	 * It is not HUE then there is these functions.
	 * But HUE required higher speed, then we need divide function to smaller parts,
	 * execute it when use still inputing and cache result.
	 * (It is simple explain for S-cube)
	 * 
	 * It is HUE S-cube sample(For full cache pattern) to 'create' and 'update' function.
	public ArticleDetailCompleteResult complete(ArticleDetailContext detail);
	public ArticleDetailContext prepare(ArticleDetailVo detail);
	public ArticleDetailCompleteResult preStore(ArticleDetailCompleteResult detail);
	public ArticleDetailStoreResult store(ArticleDetailCompleteResult detail);
	*/


	/**
	 * Create new article
	 * @param detail This detail does not have articleId
	 * @return This detail has articleId
	 */
	ArticleVo create(ArticleVo newRecord);
	void delete(String articleId);
	ArticleVo find(String articleId);


	/**
	 * Return article header list.
	 * 
	 * @return
	 */
	List<ArticleHeaderVo> load();
	void update(ArticleVo newRecord);
}
