package com.worksap.bootcamp.webeditor.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


/**
 * Accept first request for webeditor.
 * Only this class return jsp page.
 * 
 * @author works
 */
@RequestMapping(value="/webeditor", produces=MediaType.TEXT_HTML_VALUE)
public class WebEditorController {
	/**
	 * Request page.
	 * 
	 * @return
	 */
	@RequestMapping(value="/", method = RequestMethod.GET)
	public ModelAndView index() {
		// TODO Auto-generated method stub
		return null;
	}
}
