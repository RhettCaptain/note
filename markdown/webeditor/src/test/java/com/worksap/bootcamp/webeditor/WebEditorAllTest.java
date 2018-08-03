package com.worksap.bootcamp.webeditor;

import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.worksap.bootcamp.webeditor.controller.ArticleControllerTest;
import com.worksap.bootcamp.webeditor.controller.TagControllerTest;
import com.worksap.bootcamp.webeditor.dao.ArticleDaoJdbcImplTest;
import com.worksap.bootcamp.webeditor.dao.DaoFactoryJdbcImplTest;
import com.worksap.bootcamp.webeditor.service.ArticleServiceImplTest;
import com.worksap.bootcamp.webeditor.service.TagServiceImplTest;


/*
 * If you make a test, please activate line comment out lines
 * And TagControllerTest and TagServiceImplTest is a sample for test
 */
@RunWith(Suite.class)
@SuiteClasses({
//	ArticleControllerTest.class,
//	TagControllerTest.class,
//	ArticleDaoJdbcImplTest.class, DaoFactoryJdbcImplTest.class,
	/* All DAOs (it access to DB) tests skip now. */
//	ArticleServiceImplTest.class,
	/* To set up JNDI is difficult, so today it is OK.
	ServiceFactoryImplTest.class,
	 */
//	TagServiceImplTest.class,
})
public class WebEditorAllTest {
	public static void main(String[] args) {
		JUnitCore.main(WebEditorAllTest.class.getName());
	}
}
