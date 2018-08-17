package com.worksap.stm2017.dao;

import java.util.List;

import com.worksap.stm2017.model.Roster;
import com.worksap.stm2017.model.ShiftType;
import com.worksap.stm2017.vo.RosterVo;
import com.worksap.stm2017.vo.ShiftDemandVo;
import com.worksap.stm2017.vo.ShiftScoreVo;
import com.worksap.stm2017.vo.ShiftTypeVo;
import com.worksap.stm2017.vo.TimeLimitRecVo;
import com.worksap.stm2017.vo.TimeLimitVo;
import com.worksap.stm2017.vo.WorkDayVo;

public interface RosterDao {
	public List<RosterVo> getRosterVo(int idx);
	
	public List<ShiftTypeVo> getShiftTypeVo();
	
	public ShiftTypeVo getShiftTypeById(Integer id);
	
	public void addShiftType(ShiftTypeVo stv);
	
	public void updateShiftType(ShiftTypeVo stv);
	
	public void deleteShiftType(Integer id);
	
	public List<ShiftDemandVo> getShiftDemandVo();
	
	public void updateShiftDemand(ShiftDemandVo sdv);
	
	public TimeLimitVo getTimeLimitVo();
	
	public void updateTimeLimit(TimeLimitVo tlv);
	
	public List<ShiftScoreVo> getShiftScoreVoByUser(Integer userId);
	
	public void updateShiftScoreVo(Integer userId,ShiftScoreVo ssv);
	
	public TimeLimitRecVo getTimeLimitRecVoByUser(Integer userId);
	
	public void updateTimeLimitRec(Integer userId,TimeLimitRecVo tlrv);

	public WorkDayVo getWorkDayVoByUser(Integer userId);

	public void updateWorkDay(Integer userId, WorkDayVo wdv);
	
	
}
