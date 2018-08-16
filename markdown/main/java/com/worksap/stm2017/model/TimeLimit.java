package com.worksap.stm2017.model;

import lombok.Data;

@Data
public class TimeLimit {
	private Double minTime;
	private Double maxTime;
	
	public TimeLimit(){}
	
	public TimeLimit(Double minTime,Double maxTime){
		this.minTime = minTime;
		this.maxTime = maxTime;
	}
}
