package com.worksap.stm2017.vo;

import lombok.Data;

@Data
public class OtherUserVo {
	private String name;
	private Integer workLevel;
	private Boolean isManager;
	
	public OtherUserVo(){}
	
	public OtherUserVo(String name,Integer workLevel,Boolean isManager){
		this.name = name;
		this.workLevel = workLevel;
		this.isManager = isManager;
	}
}
