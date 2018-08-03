package com.worksap.bootcamp.webeditor.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/*
 * Point of factory test is
 *   1) Actually read context.xml
 *   2) Return expected implements
 */
@ContextConfiguration("file:src/main/webapp/WEB-INF/applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class ServiceFactoryImplTest {
	@Autowired
	private ServiceFactory factory;

	@Test
	public void testGetArticleService() {
		assertEquals(ArticleServiceImpl.class.getName(), factory.getArticleService().getClass().getName());
	}


	@Test
	public void testGetTagService() {
		assertEquals(TagServiceImpl.class.getName(), factory.getTagService().getClass().getName());
	}
}
