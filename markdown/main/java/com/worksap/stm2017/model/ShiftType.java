package com.worksap.stm2017.model;


import java.sql.Time;

import lombok.Data;

@Data
public class ShiftType {
	private String name;
	private Time beginTime;
	private Time endTime;
	private Boolean used;
	
	public ShiftType(){}
	
	public ShiftType(String name,Time beginTime,
			Time endTime,Boolean used){
		this.name = name;
		this.beginTime = beginTime;
		this.endTime = endTime;
		this.used = used;
	}
}
