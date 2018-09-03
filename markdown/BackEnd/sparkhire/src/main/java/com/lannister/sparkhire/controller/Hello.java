package com.lannister.sparkhire.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Hello {
	@RequestMapping("/hello")
	@ResponseBody
	public String hello(){
		return "hello world";
	}
	
}

