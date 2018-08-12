package com.worksap.stm2017.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.worksap.stm2017.model.User;

@Component
public class UserDaoImpl implements UserDao{
	private JdbcTemplate template;
	
	@Autowired
	public UserDaoImpl(JdbcTemplate template){
		this.template = template;
	}
	
	
	public boolean checkAdmin(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	
	public boolean checkEmpl(User user) {
		// TODO Auto-generated method stub
		return false;
	}

}
