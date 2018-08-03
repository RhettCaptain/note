package com.worksap.bootcamp.webeditor.dao;

import java.util.Iterator;

import com.worksap.bootcamp.webeditor.dto.TagDto;


/**
 * Treat Tag
 * 
 * @author works
 */
public interface TagDao {
	/**
	 * Return all tag list.
	 * It will have id and name.
	 * (Today, client cache it)
	 * 
	 * @return
	 */
	Iterator<TagDto> list();
}
