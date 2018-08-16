package com.worksap.stm2017.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.worksap.stm2017.dao.DaoFactory;
import com.worksap.stm2017.dao.RosterDao;
import com.worksap.stm2017.dao.UserDao;
import com.worksap.stm2017.model.ShiftType;
import com.worksap.stm2017.model.User;
import com.worksap.stm2017.util.JsonUtil;
import com.worksap.stm2017.vo.ShiftTypeVo;

@RequestMapping("/admin")
@Controller
public class AdminController {
	private DaoFactory factory;
	
	@Autowired
	public AdminController(DaoFactory factory){
		this.factory = factory;
	}
	
	@RequestMapping("/loadShiftType")
	@ResponseBody
	public List<ShiftTypeVo> loadShiftType(){
		RosterDao rosterDao = factory.getRosterDao();
		return rosterDao.getShiftTypeVo();
	}
	
	@RequestMapping("/getShiftTypeByName")
	@ResponseBody
	public ShiftType getShiftTypeByName(String name){
		RosterDao rosterDao = factory.getRosterDao();
		return rosterDao.getShiftTypeByName(name);
	}
	
}
