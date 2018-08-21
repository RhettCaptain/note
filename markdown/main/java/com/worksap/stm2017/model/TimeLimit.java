package com.worksap.stm2017.model;

import lombok.Data;

@Data
public class TimeLimit {
	private Double minTime;
	private Double maxTime;
	
	public TimeLimit(){}
	
	public TimeLimit(Double minTime,Double maxTime){
		this.minTime = Math.round(minTime*100)/100.0;
		this.maxTime = Math.round(maxTime*100)/100.0;;
	}
	
	public void setMinTime(Double minTime){
		this.minTime = Math.round(minTime*100)/100.0;
	}
	
	public void setMaxTime(Double maxTime){
		this.maxTime = Math.round(maxTime*100)/100.0;
	}
}
