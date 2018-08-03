package com.worksap.bootcamp.webeditor.service;

public interface ServiceFactory {
	ArticleService getArticleService();
	TagService getTagService();
}
