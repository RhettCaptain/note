package com.worksap.stm2017.dao;

import java.util.List;

import com.worksap.stm2017.model.Roster;
import com.worksap.stm2017.vo.RosterVo;

public interface RosterDao {
	public List<RosterVo> getRosterVo(int idx);
}
