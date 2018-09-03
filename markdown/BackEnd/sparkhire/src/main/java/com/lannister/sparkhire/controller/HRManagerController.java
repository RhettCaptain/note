package com.lannister.sparkhire.controller;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.lannister.sparkhire.entity.Staff;
import com.lannister.sparkhire.service.ServiceFactory;
import com.lannister.sparkhire.service.StaffService;
import com.lannister.sparkhire.vo.StaffVo;

@Controller
@RequestMapping("/hrm")			//check url
public class HRManagerController {
	private StaffService staffService;
	
	@Autowired
	public HRManagerController(ServiceFactory serviceFactory){
		this.staffService = serviceFactory.getStaffService();
	}
	
	@RequestMapping("/")
	@ResponseBody
	public ModelAndView index(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("views/hr/hrmanager");		
		return modelAndView;
	}
	
	
	@RequestMapping("/setrecruittime")
	@ResponseBody
	public ModelAndView setRecruitTime(@RequestParam("start-time")String startDate,
			@RequestParam("stop-time")String stopDate){
		System.out.println("Start Date:" + startDate);
		System.out.println("Stop Date:" + stopDate);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("views/hr/demandsreview");		
		return modelAndView;
	}
	
	@RequestMapping("/loaddepartments")
	@ResponseBody
	public List<String> loadDepartments(){
		List<String> departments = new ArrayList<String>();
		departments.add("IT");
		departments.add("HR");
		return departments;
	}
	
	@RequestMapping("/loaddemandsbydepartment")
	@ResponseBody
	public List<String> loadDemandsByDepartment(@RequestParam("department") String dep){
		List<String> demands = new ArrayList<String>();
		demands.add(dep+"Front");
		demands.add(dep+"Back");
		return demands;
	}
}
