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
	private Integer authLevel;
	private Integer workLevel;
	private List<Integer> groupId;
	 
	public User(){
		
	};
	
	public User(Integer userId,String name,String password,
			Integer authLevel,Integer workLevel,List<Integer> groupId){
		this.userId = userId;
		this.name = name;
		this.password = password;
		this.authLevel = authLevel;
		this.workLevel = workLevel;
		this.groupId = groupId;
	}
	
	public User(Integer userId,String name,String password,
			Integer authLevel,Integer workLevel,Array groupId){
		this.userId = userId;
		this.name = name;
		this.password = password;
		this.authLevel = authLevel;
		this.workLevel = workLevel;
		this.groupId = new ArrayList();
		try {
			ResultSet rs = groupId.getResultSet();
			while(rs.next()){
				this.groupId.add(rs.getInt(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
