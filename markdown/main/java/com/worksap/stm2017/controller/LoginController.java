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
	@ResponseBody
	public String loginAdmin(@RequestBody User user,HttpSession session){
		UserDao userDao = factory.getUserDao();
		Boolean checked = userDao.checkAdmin(user);
		String json = JsonUtil.jsonify("loginOk",checked.toString(),
				"userId",user.getUserId().toString());
		if(checked){
			User res = userDao.getUser(user.getUserId());
			session.setAttribute("loginOk", "true");
			session.setAttribute("userName", res.getName());
		}else{
			session.setAttribute("loginOk", "false");
		}
		return json;
	}
	
	@RequestMapping("/empl")
	@ResponseBody
	public String loginEmpl(@RequestBody User user,HttpSession session){
		UserDao userDao = factory.getUserDao();
		Boolean checked = userDao.checkEmpl(user);
		String json = JsonUtil.jsonify("loginOk",checked.toString(),
				"userId",user.getUserId().toString());
		if(checked){
			User res = userDao.getUser(user.getUserId());
			session.setAttribute("loginOk", "true");
			session.setAttribute("userName", res.getName());
		}else{
			session.setAttribute("loginOk", "false");
		}
		return json;
	}
	
	@RequestMapping("/checkSession")
	@ResponseBody
	public String checkSession(HttpSession session){
		if(session.getAttribute("loginOk")==null 
				|| session.getAttribute("userName")==null){
			return JsonUtil.jsonify("loginOk","false","userName","");
		}
		String json = JsonUtil.jsonify(
				"loginOk",session.getAttribute("loginOk").toString(),
				"userName",session.getAttribute("userName").toString());
		return json;
	}
	
}
