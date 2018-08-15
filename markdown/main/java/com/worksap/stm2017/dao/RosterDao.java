package com.worksap.stm2017.dao;

import java.util.List;

import com.worksap.stm2017.model.Roster;

public interface RosterDao {
	public List<Roster> getRoster(int idx);
}
