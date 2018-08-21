package com.worksap.stm2017.model;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lombok.Data;

@Data
public class User {
	
	private Integer userId;
	private String name;
	private String password;
	private Integer workLevel;
	private Boolean isManager;
	private String nickName;
	private Boolean deleted;
	 
	public User(){
	}
	
	public User(Integer userId,String name,String password,
			Integer workLevel,Boolean isManager,String nickName,Boolean deleted){
		this.userId = userId;
		this.name = name;
		this.password = password;
		this.workLevel = workLevel;
		this.isManager = isManager;
		this.nickName = nickName;
		this.deleted = deleted;
	}
}
