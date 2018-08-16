package com.worksap.stm2017.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.worksap.stm2017.dao.DaoFactory;
import com.worksap.stm2017.dao.RosterDao;
import com.worksap.stm2017.dao.UserDao;
import com.worksap.stm2017.model.ShiftType;
import com.worksap.stm2017.model.User;
import com.worksap.stm2017.util.JsonUtil;
import com.worksap.stm2017.vo.ShiftDemandVo;
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
	
	@RequestMapping("/getShiftTypeById")
	@ResponseBody
	public ShiftTypeVo getShiftTypeById(@RequestBody Integer id){
		RosterDao rosterDao = factory.getRosterDao();
		return rosterDao.getShiftTypeById(id);
	}
	
	@RequestMapping("/confirmShiftType")
	@ResponseBody
	public String confirmShiftType(@RequestBody ShiftTypeVo stv,@RequestParam("id") Integer id){
		RosterDao rosterDao = factory.getRosterDao();
	/*	if(id==-1){
	//		rosterDao.addShiftType(stv);
		}else{
	//		rosterDao.updateShiftType(stv);
		}*/
		return JsonUtil.jsonify("state","ok");
	}
	
	@RequestMapping("/deleteShiftType")
	public void deleteShiftType(@RequestParam("id") Integer id,HttpServletResponse response) throws IOException{
		RosterDao rosterDao = factory.getRosterDao();
		rosterDao.deleteShiftType(id);
		response.sendRedirect("/admin/strategy.html");
		
	}
	
	@RequestMapping("/loadShiftDemand")
	@ResponseBody
	public List<ShiftDemandVo> loadShiftDemand(){
		RosterDao rosterDao = factory.getRosterDao();
		return rosterDao.getShiftDemandVo();
	}
	
	@RequestMapping("/updateShiftDemand")
	@ResponseBody
	public String updateShiftDemand(@RequestBody ShiftDemandVo sdv){
		RosterDao rosterDao = factory.getRosterDao();
		rosterDao.updateShiftDemand(sdv);
		return JsonUtil.jsonify("state","ok");
	}
	
}
