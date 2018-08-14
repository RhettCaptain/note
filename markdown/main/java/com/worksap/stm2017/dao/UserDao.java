package com.worksap.stm2017.dao;

import com.worksap.stm2017.model.User;

public interface UserDao {
	public boolean checkAdmin(User user);
	
	public boolean checkEmpl(User user);
	
	public User getUser(Integer userId);
}
