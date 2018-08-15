package com.worksap.stm2017.model;

import lombok.Data;

@Data
public class Roster {
	private Integer userId;
	private String userName;
	private String monShift;
	private String tusShift;
	private String wedShift;
	private String thuShift;
	private String friShift;
	private String satShift;
	private String sunShift;
	
	public Roster(){}
	
	public Roster(Integer userId,String userName,String monShift,
			String tusShift,String wedShift,String thuShift,
			String friShift,String satShift,String sunShift){
		this.userId = userId;
		this.userName = userName;
		this.monShift = monShift;
		this.tusShift = tusShift;
		this.wedShift = wedShift;
		this.thuShift = thuShift;
		this.friShift = friShift;
		this.satShift = satShift;
		this.sunShift = sunShift;
	}
}
