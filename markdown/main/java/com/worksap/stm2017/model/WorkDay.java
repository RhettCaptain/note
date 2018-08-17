package com.worksap.stm2017.model;

import lombok.Data;

@Data
public class WorkDay {
	private Integer userId;
	private Double mon;
	private Double tus;
	private Double wed;
	private Double thu;
	private Double fri;
	private Double sat;
	private Double sun;
	
	public WorkDay(){}
	
	public WorkDay(Integer userId,Double mon,Double tus,Double wed,
			Double thu,Double fri,Double sat,Double sun){
		this.userId = userId;
		this.mon = mon;
		this.tus = tus;
		this.wed = wed;
		this.thu = thu;
		this.fri = fri;
		this.sat = sat;
		this.sun = sun;
	}
	
}
