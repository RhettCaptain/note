package com.worksap.stm2017.dao;

import java.util.List;

import com.worksap.stm2017.model.Roster;
import com.worksap.stm2017.model.ShiftType;
import com.worksap.stm2017.vo.RosterVo;
import com.worksap.stm2017.vo.ShiftDemandVo;
import com.worksap.stm2017.vo.ShiftTypeVo;

public interface RosterDao {
	public List<RosterVo> getRosterVo(int idx);
	
	public List<ShiftTypeVo> getShiftTypeVo();
	
	public ShiftTypeVo getShiftTypeById(Integer id);
	
	public void addShiftType(ShiftTypeVo stv);
	
	public void updateShiftType(ShiftTypeVo stv);
	
	public void deleteShiftType(Integer id);
	
	public List<ShiftDemandVo> getShiftDemandVo();
	
	public void updateShiftDemand(ShiftDemandVo sdv);
}
