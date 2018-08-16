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
import com.worksap.stm2017.vo.ShiftDemandVo;
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
		ShiftTypeVo stv = new ShiftTypeVo(1,"A",Time.valueOf("8:00:00"),Time.valueOf("16:10:00"),
				2.5,true,3.0);
		shiftType.add(stv);
		shiftType.add(stv);
		return shiftType;
		
		
	}

	@Override
	public ShiftTypeVo getShiftTypeById(Integer id) {
		// TODO Auto-generated method stub
		ShiftTypeVo stv = new ShiftTypeVo(1,"A",Time.valueOf("08:00:00"),
				Time.valueOf("16:00:00"),5.5,true,3.3);
		return stv;
	}

	@Override
	public void addShiftType(ShiftTypeVo stv) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateShiftType(ShiftTypeVo stv) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteShiftType(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ShiftDemandVo> getShiftDemandVo() {
		// TODO Auto-generated method stub
		List<ShiftDemandVo> list = new ArrayList();
		ShiftDemandVo sdv = new ShiftDemandVo(2,"N",1,2,3,4,5,2,3,4,5,6);
		list.add(sdv);
		return list;
	}

	@Override
	public void updateShiftDemand(ShiftDemandVo sdv) {
		// TODO Auto-generated method stub
		
	}

}
