package com.worksap.stm2017.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.worksap.stm2017.dao.DaoFactory;
import com.worksap.stm2017.dao.UserDao;
import com.worksap.stm2017.model.Login;
import com.worksap.stm2017.model.User;
import com.worksap.stm2017.util.JsonUtil;

@RequestMapping("/login")
@Controller
public class LoginController {
	private DaoFactory factory;
	
	@Autowired
	public LoginController(DaoFactory factory){
		this.factory = factory;
	}
	
	@RequestMapping("/admin")
	public ModelAndView loginAdmin(@RequestBody User user){
		ModelAndView mv = new ModelAndView();
		UserDao userDao = factory.getUserDao();
		boolean res = userDao.checkAdmin(user);
		
		if(res){
			mv.addObject("userId", user.getUserId());
			mv.setViewName("index.html");
		}else{
			mv.setViewName("login.html");
		}
		return mv;
	}
	
	@RequestMapping("/empl")
	@ResponseBody
	public String loginEmpl(@RequestBody User user,HttpSession session){
		UserDao userDao = factory.getUserDao();
		Boolean res = userDao.checkEmpl(user);
		String json = JsonUtil.jsonify("loginOk",res.toString(),
				"userId",user.getUserId().toString());
		if(res){
			session.setAttribute("loginOk", "true");
		}else{
			session.setAttribute("loginOk", "false");
		}
		return json;
	}
	
	@RequestMapping("/checkSession")
	@ResponseBody
	public String checkSession(HttpSession session){
		Object obj = session.getAttribute("loginOk");
		if(obj==null){
			System.out.println("no session");
			obj = "false";
		}
		String json = JsonUtil.jsonify(
				"loginOk",session.getAttribute("loginOk").toString());
		return json;
	}
}
