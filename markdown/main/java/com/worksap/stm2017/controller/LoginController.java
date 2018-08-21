package com.worksap.stm2017.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
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
		Integer userId = userDao.checkAdmin(user);
		String json = "";
		if(userId>=0){
			json = JsonUtil.jsonify("loginOk","true",
					"userId",userId.toString());
			User res = userDao.getUser(userId);
			session.setAttribute("loginOk", "true");
			session.setAttribute("userId", res.getUserId());
			session.setAttribute("userName", res.getName());
		}else{
			json = JsonUtil.jsonify("loginOk","false",
					"userId","-1");
			session.setAttribute("loginOk", "false");
		}
		return json;
	}
	
	@RequestMapping("/empl")
	@ResponseBody
	public String loginEmpl(@RequestBody User user,HttpSession session){
		UserDao userDao = factory.getUserDao();
		Integer userId = userDao.checkEmpl(user);
		String json="";
		if(userId>=0){
			json = JsonUtil.jsonify("loginOk","true",
					"userId",userId.toString());
			User res = userDao.getUser(userId);
			session.setAttribute("loginOk", "true");
			session.setAttribute("userId", res.getUserId());
			session.setAttribute("userName", res.getName());
		}else{
			json = JsonUtil.jsonify("loginOk","false",
					"userId","-1");
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
	
	@RequestMapping("/getUserId")
	@ResponseBody
	public String getUserId(HttpSession session){
		if(session.getAttribute("loginOk")==null 
				|| session.getAttribute("userId")==null){
			return JsonUtil.jsonify("loginOk","false","userId","");
		}
		String json = JsonUtil.jsonify(
				"loginOk",session.getAttribute("loginOk").toString(),
				"userId",session.getAttribute("userId").toString());
		return json;
	}
	
	@RequestMapping("/quit")
	@ResponseBody
	public void quit(HttpServletResponse response,HttpSession session) throws IOException{
		session.removeAttribute("loginOk");
		session.removeAttribute("userId");
		session.removeAttribute("userName");
		response.sendRedirect("/");
	}
	
}
