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
import com.worksap.stm2017.vo.OtherUserVo;
import com.worksap.stm2017.vo.ShiftDemandVo;
import com.worksap.stm2017.vo.ShiftScoreVo;
import com.worksap.stm2017.vo.ShiftTypeVo;
import com.worksap.stm2017.vo.TimeLimitRecVo;
import com.worksap.stm2017.vo.TimeLimitVo;
import com.worksap.stm2017.vo.UserVo;
import com.worksap.stm2017.vo.WorkDayVo;

@RequestMapping("/empl")
@Controller
public class EmplController {
	private DaoFactory factory;
	
	@Autowired
	public EmplController(DaoFactory factory){
		this.factory = factory;
	}
	

	@RequestMapping("/loadShiftScore")
	@ResponseBody
	public List<ShiftScoreVo> loadShiftScore(@RequestParam("userId") Integer userId){
		RosterDao rosterDao = factory.getRosterDao();
		return rosterDao.getShiftScoreVoByUser(userId);
	}
	
	@RequestMapping("/updateShiftScore")
	@ResponseBody
	public String updateShiftScore(@RequestBody ShiftScoreVo ssv,@RequestParam("userId") Integer userId){
		RosterDao rosterDao = factory.getRosterDao();
		rosterDao.updateShiftScoreVo(userId, ssv);
		return JsonUtil.jsonify("state","ok");
	}
	
	@RequestMapping("/loadTimeLimitRec")
	@ResponseBody
	public TimeLimitRecVo loadTimeLimitRec(@RequestParam("userId") Integer userId){
		RosterDao rosterDao = factory.getRosterDao();
		return rosterDao.getTimeLimitRecVoByUser(userId);
	}
	
	@RequestMapping("/updateTimeLimitRec")
	@ResponseBody
	public String updateTimeLimitRec(@RequestBody TimeLimitRecVo tlrv,@RequestParam("userId") Integer userId){
		RosterDao rosterDao = factory.getRosterDao();
		rosterDao.updateTimeLimitRec(userId, tlrv);
		return JsonUtil.jsonify("state","ok");
	}
	
	@RequestMapping("/loadWorkDay")
	@ResponseBody
	public WorkDayVo loadWorkDay(@RequestParam("userId") Integer userId){
		RosterDao rosterDao = factory.getRosterDao();
		return rosterDao.getWorkDayVoByUser(userId);
	}
	
	@RequestMapping("/updateWorkDay")
	@ResponseBody
	public String updateWorkDay(@RequestBody WorkDayVo wdv,@RequestParam("userId") Integer userId){
		RosterDao rosterDao = factory.getRosterDao();
		rosterDao.updateWorkDay(userId, wdv);
		return JsonUtil.jsonify("state","ok");
	}
	
	@RequestMapping("/getUserById")
	@ResponseBody
	public UserVo getUserById(@RequestParam("id") Integer id){
		UserDao userDao = factory.getUserDao();
		return userDao.getUserById(id);
	}
	
	@RequestMapping("/changePassword")
	@ResponseBody
	public String changePassword(@RequestBody UserVo user){
		UserDao userDao = factory.getUserDao();
		userDao.updatePassword(user.getId(), user.getPassword());
		return JsonUtil.jsonify("state","ok");
	}
}
