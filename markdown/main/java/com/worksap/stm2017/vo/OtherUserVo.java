package com.worksap.stm2017.vo;

import lombok.Data;

@Data
public class OtherUserVo {
	private Integer id;
	private String name;
	private Integer workLevel;
	private Boolean isManager;
	private String nickName;
	private Boolean deleted;
	
	public OtherUserVo(){}
	
	public OtherUserVo(Integer id,String name,Integer workLevel,Boolean isManager,String nickName,Boolean deleted){
		this.id = id;
		this.name = name;
		this.workLevel = workLevel;
		this.isManager = isManager;
		this.nickName = nickName;
		this.deleted = deleted;
	}
}
