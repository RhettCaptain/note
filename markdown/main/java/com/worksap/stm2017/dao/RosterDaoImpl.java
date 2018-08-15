package com.worksap.stm2017.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.worksap.stm2017.model.Roster;

@Component
public class RosterDaoImpl implements RosterDao{
	private JdbcTemplate template;
	
	@Autowired
	public RosterDaoImpl(JdbcTemplate template){
		this.template = template;
	}
	
	@Override
	public List<Roster> getRoster(int idx) {
		// TODO Auto-generated method stub
		List<Roster> test = new ArrayList();
		Roster ros1 = new Roster();
		ros1.setFriShift("hhh");
		Roster ros2 = new Roster();
		ros2.setFriShift("hhh2");
		test.add(ros1);
		test.add(ros2);
		return test;
	}

}
