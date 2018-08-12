package com.worksap.stm2017.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.worksap.stm2017.dao.DaoFactory;
import com.worksap.stm2017.dao.UserDao;
import com.worksap.stm2017.model.User;

@RequestMapping("/login")
@Controller
public class LoginController {
	private DaoFactory factory;
	
	@Autowired
	public LoginController(DaoFactory facotry){
		this.factory = factory;
	}
	
	@RequestMapping("/admin")
	@ResponseBody
	public String loginAdmin(@RequestBody User user){
		UserDao userDao = factory.getUserDao();
		boolean res = userDao.checkAdmin(user);
		System.out.println("ttt");
		if(res){
			return "ture";
		}else{
			return "false";
		}
	}
}
