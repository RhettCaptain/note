package com.worksap.bootcamp.webeditor.dao;

import java.util.Iterator;

import org.springframework.jdbc.core.JdbcTemplate;

import com.worksap.bootcamp.webeditor.dto.TagDto;


final class TagDaoJdbcImpl implements TagDao {
	private static final String LIST_SQL = "SELECT ID, NAME FROM ARTICLE_TAG ORDER BY ID";


	private final JdbcTemplate template;


	TagDaoJdbcImpl(JdbcTemplate template) {
		this.template = template;
	}


	@Override
	public Iterator<TagDto> list() {
		return template.query(LIST_SQL,
				(rs, rowNum) -> new TagDto(rs.getString(1), rs.getString(2))
				).iterator();
	}
}
