package com.worksap.stm2017.dao;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.worksap.stm2017.model.Roster;
import com.worksap.stm2017.model.ShiftType;
import com.worksap.stm2017.vo.RosterVo;
import com.worksap.stm2017.vo.ShiftTypeVo;

@Component
public class RosterDaoImpl implements RosterDao{
	private JdbcTemplate template;
	
	@Autowired
	public RosterDaoImpl(JdbcTemplate template){
		this.template = template;
	}
	
	@Override
	public List<RosterVo> getRosterVo(int idx) {
		// TODO Auto-generated method stub
		List<RosterVo> test = new ArrayList();
		RosterVo ros1 = new RosterVo();
		ros1.setFriShift("hhh");
		RosterVo ros2 = new RosterVo();
		ros2.setFriShift("hhh2");
		test.add(ros1);
		test.add(ros2);
		return test;
	}

	@Override
	public List<ShiftTypeVo> getShiftTypeVo() {
		// TODO Auto-generated method stub
		List<ShiftTypeVo> shiftType = new ArrayList();
		//getShiftType
		ShiftTypeVo stv = new ShiftTypeVo("A","18:00-20:00",
				"2H","IN USE","3");
		shiftType.add(stv);
		shiftType.add(stv);
		return shiftType;
		
		
	}

	@Override
	public ShiftType getShiftTypeByName(String name) {
		// TODO Auto-generated method stub
		ShiftType st = new ShiftType("A",Time.valueOf("08:00:00"),
				Time.valueOf("16:00:00"),true);
		return st;
	}

}
