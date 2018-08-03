package com.worksap.bootcamp.webeditor.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


/**
 * At first, you should check your configuration.<br>
 * (This class will help your check...)
 */
@Controller
@RequestMapping(value="/helloworld", produces=MediaType.TEXT_HTML_VALUE)
public class HelloWorldController {
	/**
	 * ** notice **
	 * Don't miss last '/' in URL
	 * 
	 * @return
	 */
	@RequestMapping(value="/", method=RequestMethod.GET)
	public ModelAndView check() {
		return new ModelAndView("/hello.jsp", "it", "hello world!");
	}
}
