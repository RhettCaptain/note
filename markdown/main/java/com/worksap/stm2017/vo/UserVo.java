package com.worksap.stm2017.vo;

import lombok.Data;

@Data
public class UserVo {
	private Integer id;
	private String name;
	private String password;
	private Integer workLevel;
	private Boolean isManager;
	
	public UserVo(){}
	
	public UserVo(Integer id,String name,String password,Integer workLevel,Boolean isManager){
		this.id = id;
		this.name = name;
		this.password = password;
		this.workLevel = workLevel;
		this.isManager = isManager;
	}
}
