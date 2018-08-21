package com.worksap.stm2017.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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
import com.worksap.stm2017.vo.OtherUserVo;
import com.worksap.stm2017.vo.RosterReportVo;
import com.worksap.stm2017.vo.RosterVo;
import com.worksap.stm2017.vo.ShiftDemandVo;
import com.worksap.stm2017.vo.ShiftTypeVo;
import com.worksap.stm2017.vo.TimeLimitVo;

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
		if(id==-1){
			rosterDao.addShiftType(stv);
		}else{
			stv.setId(id);
			rosterDao.updateShiftType(stv);
		}
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
	

	@RequestMapping("/loadTimeLimit")
	@ResponseBody
	public TimeLimitVo loadTimeLimit(){
		RosterDao rosterDao = factory.getRosterDao();
		return rosterDao.getTimeLimitVo();
	}
	
	@RequestMapping("/updateTimeLimit")
	@ResponseBody
	public String updateTimeLimit(@RequestBody TimeLimitVo tlv){
		RosterDao rosterDao = factory.getRosterDao();
		rosterDao.updateTimeLimit(tlv);
		return JsonUtil.jsonify("state","ok");
	}
	
	@RequestMapping("/loadUser")
	@ResponseBody
	public List<OtherUserVo> loadUser(@RequestParam("lv") Integer lv){
		UserDao userDao = factory.getUserDao();
		return userDao.getOtherUserVoByLevel(lv);
	}
	
	@RequestMapping("/getOtherUserById")
	@ResponseBody
	public OtherUserVo getOtherUserById(@RequestBody Integer id){
		UserDao userDao = factory.getUserDao();
		return userDao.getOtherUserById(id);
	}
	
	@RequestMapping("/confirmOtherUser")
	@ResponseBody
	public String confirmOtherUser(@RequestBody OtherUserVo stv,@RequestParam("id") Integer id){
		UserDao userDao = factory.getUserDao();
		if(id==-1){
			userDao.addUser(stv);
		}else{
			stv.setId(id);
			userDao.updateUser(stv);
		}
		return JsonUtil.jsonify("state","ok");
	}
	
	@RequestMapping("/deleteOtherUser")
	public void deleteOtherUser(@RequestParam("id") Integer id,HttpServletResponse response) throws IOException{
		UserDao userDao = factory.getUserDao();
		userDao.deleteUser(id);
		response.sendRedirect("/admin/staff.html");
		
	}
	
	@RequestMapping("/loadRosterReport")
	@ResponseBody
	public List<RosterReportVo> loadRosterReport(){
		RosterDao rosterDao = factory.getRosterDao();
		return rosterDao.getRosterReportVo();
	}
	
	@RequestMapping("/updateRosterById/{which}")
	@ResponseBody
	public String updateRosterById(@PathVariable String which,@RequestBody RosterVo rv){
		RosterDao rosterDao = factory.getRosterDao();
		rosterDao.updateRosterById(which,rv);
		return JsonUtil.jsonify("state","ok");
	}
	
	
	@RequestMapping("/loadNewRosterReport")
	@ResponseBody
	public List<RosterReportVo> loadNewRosterReport(){
		RosterDao rosterDao = factory.getRosterDao();
		return rosterDao.getNewRosterReportVo();
	}
	
	@RequestMapping("/updateNewRosterById/{which}")
	@ResponseBody
	public String updateNewRosterById(@PathVariable String which,@RequestBody RosterVo rv){
		RosterDao rosterDao = factory.getRosterDao();
		rosterDao.updateNewRosterById(which,rv);
		return JsonUtil.jsonify("state","ok");
	}
	
	@RequestMapping("/getNewRoster/{which}")
	@ResponseBody
	public List<RosterVo> getNewRoster(@PathVariable String which){

		RosterDao rs = factory.getRosterDao();
		
		List<RosterVo> t = rs.getNewRosterVo(which);
		
		return t;
	}
	
	@RequestMapping("/generateRosters")
	@ResponseBody
	public String generateRosters(){
		RosterDao rosterDao = factory.getRosterDao();
		rosterDao.generateRosters();
		return JsonUtil.jsonify("state","ok");
	}
	
	@RequestMapping("/chooseRoster/{which}")
	public void chooseRoster(@PathVariable("which") String which,HttpServletResponse response) throws IOException{
		RosterDao rosterDao = factory.getRosterDao();
		rosterDao.chooseRoster(which);
		response.sendRedirect("/admin/roster.html");
	}
	
	@RequestMapping("/shiftRoster")
	@ResponseBody
	public String shiftRoster(){
		RosterDao rosterDao = factory.getRosterDao();
		rosterDao.shiftRoster();
		return JsonUtil.jsonify("state","ok");
	}
	
}
