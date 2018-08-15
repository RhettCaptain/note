package com.worksap.stm2017.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DaoFactoryImpl implements DaoFactory {
	private UserDao userDao;
	private RosterDao rosterDao;
	
	@Autowired
	public DaoFactoryImpl(UserDao userDao,RosterDao rosterDao){
		this.rosterDao = rosterDao;
		this.userDao = userDao;
	}
	
	
	public UserDao getUserDao() {
		return userDao;
	}


	
	public RosterDao getRosterDao() {
		return rosterDao;
	}


}
