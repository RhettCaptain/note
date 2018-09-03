package com.lannister.sparkhire.controller;



import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.lannister.sparkhire.entity.Staff;
import com.lannister.sparkhire.service.ServiceFactory;
import com.lannister.sparkhire.service.StaffService;
import com.lannister.sparkhire.vo.StaffVo;

@Controller
@RequestMapping("/hr")			//check url
public class StaffController {
	private StaffService staffService;
	
	@Autowired
	public StaffController(ServiceFactory serviceFactory){
		this.staffService = serviceFactory.getStaffService();
	}
	
	@RequestMapping("/")
	@ResponseBody
	public ModelAndView index(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("views/hr/login");		//check url
		return modelAndView;
	}
	
	@RequestMapping("/login")
	@ResponseBody
	public ModelAndView login(String username,String password,HttpSession session){
		staffService.initRole();  //init roletable()  check! 
		session.setAttribute("username", username);
		ModelAndView modelAndView = new ModelAndView();
		StaffVo staffVo = staffService.findStaffByUsernameAndPassword(username, password);
		if(staffVo == null){
			modelAndView.addObject("msg", "Login failed");
			modelAndView.setViewName("views/hr/login");
		}else{
			modelAndView.addObject("state", "ok");
			modelAndView.setViewName("views/hr/index");
			session.setAttribute("user", staffVo);
		}
		return modelAndView;
	}
	
	@RequestMapping("/add")
	@ResponseBody
	public String addStaff(StaffVo staffVo){
		staffService.addStaff(staffVo);
		return "success";
	}
	
	@RequestMapping("/query")
	@ResponseBody
	public StaffVo queryStaffById(Integer userid){
		StaffVo staffVo = staffService.queryStaffById(userid);
		return staffVo;
	}
	
	@RequestMapping("/loadhr")
	@ResponseBody
	public List<StaffVo> loadHr(){
		List<StaffVo> hrs = staffService.loadStaffByRole(1);
		return hrs;
	}
	@RequestMapping("/loadinterviewor")
	@ResponseBody
	public List<StaffVo> loadInterviewor(){
		List<StaffVo> interviewors = staffService.loadStaffByRole(3);
		return interviewors;
	}
	@RequestMapping("/loadall")
	@ResponseBody
	public List<StaffVo> loadAll(){
		List<StaffVo> all = staffService.load();
		return all;
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public String deleteStaff(Integer staffid){
		staffService.deleteStaff(staffid);
		return "success";
	}

	@RequestMapping("/update")
	@ResponseBody
	public String updateStaff(StaffVo staffVo){
		staffService.updateStaff(staffVo);
		return "success";
	}
	
	@RequestMapping("/employeemanager")
	@ResponseBody
	public ModelAndView getEmployeeManager(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("views/hr/employeemanager");
		return modelAndView;
	}
	
}
