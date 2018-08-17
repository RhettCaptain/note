package com.worksap.stm2017.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.worksap.stm2017.dao.DaoFactory;
import com.worksap.stm2017.dao.RosterDao;
import com.worksap.stm2017.model.Roster;
import com.worksap.stm2017.vo.RosterVo;

@Controller
public class MainController {
	private DaoFactory factory;
	
	@Autowired
	public MainController(DaoFactory factory){
		this.factory = factory;
	}
	
	@RequestMapping("/")
	public ModelAndView index(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("index.html");
		return mv;
	}
	
	@RequestMapping("/getRoster/{week}")
	@ResponseBody
	public List<RosterVo> getRoster(@PathVariable int week){

		RosterDao rs = factory.getRosterDao();
		
		List<RosterVo> t = rs.getRosterVo(week);
		
		return t;
	}
	
}
