package com.worksap.stm2017.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DaoFactoryImpl implements DaoFactory {
	private UserDao userDao;
	
	@Autowired
	public DaoFactoryImpl(UserDao userDao){
		this.userDao = userDao;
	}
	
	
	public UserDao getUserDao() {
		return userDao;
	}


}
