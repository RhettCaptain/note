package com.worksap.bootcamp.webeditor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.worksap.bootcamp.webeditor.dao.DaoFactory;
import com.worksap.bootcamp.webeditor.dao.TagDao;
import com.worksap.bootcamp.webeditor.vo.TagVo;

class TagServiceImpl implements TagService {
	private TagDao tagDao;


	/*
	 * Use constructor injection make test simple
	 */
	@Autowired
	public TagServiceImpl(@Qualifier("selectedImpl") DaoFactory factory) {
		this.tagDao = factory.getTagDao();
	}


	@Override
	public List<TagVo> load() {
		// TODO Auto-generated method stub
		return null;
	}
}
