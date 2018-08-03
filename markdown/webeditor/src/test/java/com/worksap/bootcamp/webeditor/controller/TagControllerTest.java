package com.worksap.bootcamp.webeditor.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.worksap.bootcamp.webeditor.service.ServiceFactory;
import com.worksap.bootcamp.webeditor.service.TagService;
import com.worksap.bootcamp.webeditor.vo.TagVo;


@ContextConfiguration(loader=AnnotationConfigContextLoader.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class TagControllerTest {
	private MockMvc mockMvc;


	@Before
	public void setUp() {
		TagService mockService = Mockito.mock(TagService.class);
		ServiceFactory mockServiceFactory = Mockito.mock(ServiceFactory.class);
		List<TagVo> result = new ArrayList<>();

		result.add(new TagVo.Builder().id("hoge").name("hoge's name").build());

		Mockito.when(mockServiceFactory.getTagService()).thenReturn(mockService);
		Mockito.when(mockService.load()).thenReturn(result);

		mockMvc = MockMvcBuilders.standaloneSetup(new TagController(mockServiceFactory)).build();
	}


	@Test
	public void testLoad() throws Exception {
		mockMvc
				.perform(MockMvcRequestBuilders.get("/webeditor/tag/load"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string(
						"[{\"id\":\"hoge\",\"name\":\"hoge's name\"}]"));
	}
}
