package com.worksap.bootcamp.webeditor.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import com.worksap.bootcamp.webeditor.dao.DaoFactory;
import com.worksap.bootcamp.webeditor.dao.TagDao;
import com.worksap.bootcamp.webeditor.dto.TagDto;
import com.worksap.bootcamp.webeditor.vo.TagVo;


/*
 * Not use @RunWith(SpringJUnit4ClassRunner.class) leads test simple.
 */
public class TagServiceImplTest {
	private static List<TagDto> RESULTS;

	private TagService service;


	@BeforeClass
	public static void setUpClass() {
		RESULTS = new ArrayList<>();
		RESULTS.add(new TagDto("id1", "test"));
		RESULTS.add(new TagDto("id2", "junit"));
	}


	@Before
	public void setUp() {
		DaoFactory mockDaoFactory = Mockito.mock(DaoFactory.class);
		TagDao mockDao = Mockito.mock(TagDao.class);

		Mockito.when(mockDaoFactory.getTagDao()).thenReturn(mockDao);
		Mockito.when(mockDao.list()).thenReturn(RESULTS.iterator());

		service = new TagServiceImpl(mockDaoFactory);
	}


	@Test
	public void testLoad() {
		List<TagVo> actuals = service.load();

		assertEquals(RESULTS.size(), actuals.size());

		for (int ii = 0; ii < actuals.size(); ii++) {
			TagVo actual = actuals.get(ii);
			TagDto expected = RESULTS.get(ii);

			assertEquals(actual.getId(), expected.getId());
			assertEquals(actual.getName(), expected.getName());
		}
	}
}
