package com.worksap.stm2017.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.worksap.stm2017.dao.DaoFactory;

@Controller
public class MainController {
	private DaoFactory factory;
	
	@Autowired
	public MainController(DaoFactory facotry){
		this.factory = factory;
	}
	
	@RequestMapping("/")
	public ModelAndView index(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("login.html");
		return mv;
	}
	
}
