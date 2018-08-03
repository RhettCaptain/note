package com.worksap.bootcamp.webeditor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.worksap.bootcamp.webeditor.entity.TagEntity;
import com.worksap.bootcamp.webeditor.service.ServiceFactory;


/**
 * Treat all request for Tag
 * 
 * @author works
 */
@Controller
@RequestMapping(value="/webeditor/tag")
public class TagController {
	private final ServiceFactory factory;


	@Autowired
	public TagController(ServiceFactory factory) {
		this.factory = factory;
	}


	/**
	 * Want to get tag id and name list.
	 * Because client cache this records, we can send articleDetail with tagId only.
	 * 
	 * @return
	 */
	@RequestMapping(value="/load", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<TagEntity> load() {
		// TODO Auto-generated method stub
		return null;
	}
}
