package com.worksap.stm2017.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.worksap.stm2017.model.User;

@Component
public class UserDaoImpl implements UserDao{
	private JdbcTemplate template;
	
	private final String SEARCH_SQL = "select * from users where \"userId\" = ? and password = ?";
	
	@Autowired
	public UserDaoImpl(JdbcTemplate template){
		this.template = template;
	}
	
	
	public boolean checkAdmin(User user) {
		List<User> res = template.query(SEARCH_SQL, 
				ps -> {
					ps.setInt(1,user.getUserId());
					ps.setString(2, user.getPassword());
				},
				(rs,rowNum) -> new User());
		if(res.isEmpty() || res.get(0).getAuthLevel() == 0){
			return false;
		}else{
			return true;
		}
	}

	
	public boolean checkEmpl(User user) {
		List<User> res = template.query(SEARCH_SQL, 
				ps -> {
					ps.setInt(1,user.getUserId());
					ps.setString(2, user.getPassword());
				},
				(rs,rowNum) -> new User());
		if(res.isEmpty()){
			return false;
		}else{
			return true;
		}
	}

}
