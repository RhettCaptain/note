package com.worksap.stm2017.dao;

import java.util.List;

import com.worksap.stm2017.model.Roster;
import com.worksap.stm2017.model.ShiftType;
import com.worksap.stm2017.vo.RosterVo;
import com.worksap.stm2017.vo.ShiftTypeVo;

public interface RosterDao {
	public List<RosterVo> getRosterVo(int idx);
	
	public List<ShiftTypeVo> getShiftTypeVo();
	
	public ShiftType getShiftTypeByName(String name);
}
