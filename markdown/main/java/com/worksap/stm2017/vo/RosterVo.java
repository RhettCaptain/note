package com.worksap.stm2017.vo;

import lombok.Data;

@Data
public class RosterVo {
	private Integer userId;
	private String userName;
	private String monShift;
	private String tusShift;
	private String wedShift;
	private String thuShift;
	private String friShift;
	private String satShift;
	private String sunShift;
	private Integer totalTime;
	
	public RosterVo(){}
	
	public RosterVo(Integer userId,String userName,String monShift,
			String tusShift,String wedShift,String thuShift,
			String friShift,String satShift,String sunShift,Integer totalTime){
		this.userId = userId;
		this.userName = userName;
		this.monShift = monShift;
		this.tusShift = tusShift;
		this.wedShift = wedShift;
		this.thuShift = thuShift;
		this.friShift = friShift;
		this.satShift = satShift;
		this.sunShift = sunShift;
		this.totalTime = totalTime;
	}
}
