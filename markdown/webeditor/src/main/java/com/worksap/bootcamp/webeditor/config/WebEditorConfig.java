package com.worksap.bootcamp.webeditor.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;

import com.worksap.bootcamp.util.StopWatch;
import com.worksap.bootcamp.webeditor.dao.DaoFactory;

public class WebEditorConfig {
	@Bean
	public StopWatch stopWatch() {
		return new StopWatch();
	}


	@Autowired
	@Bean
	@Qualifier("selectedImpl")
	public DaoFactory selectedDaofactory(@Qualifier("daoFactoryJdbcImpl") DaoFactory target) {
		return target;
	}
}
