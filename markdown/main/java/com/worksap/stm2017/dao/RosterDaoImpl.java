package com.worksap.stm2017.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.worksap.stm2017.model.Roster;
import com.worksap.stm2017.vo.RosterVo;

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

}
