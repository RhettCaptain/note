package com.worksap.stm2017.model;

import lombok.Data;

@Data
public class Roster {
	private Integer userId;
	private Integer monShift;
	private Integer tueShift;
	private Integer wedShift;
	private Integer thuShift;
	private Integer friShift;
	private Integer satShift;
	private Integer sunShift;
	
	public Roster(){}
	
	public Roster(Integer userId,Integer monShift,
			Integer tueShift,Integer wedShift,Integer thuShift,
			Integer friShift,Integer satShift,Integer sunShift){
		this.userId = userId;
		this.monShift = monShift;
		this.tueShift = tueShift;
		this.wedShift = wedShift;
		this.thuShift = thuShift;
		this.friShift = friShift;
		this.satShift = satShift;
		this.sunShift = sunShift;
	}
}
