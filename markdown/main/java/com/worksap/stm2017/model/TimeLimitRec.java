package com.worksap.stm2017.model;

import lombok.Data;

@Data
public class TimeLimitRec {
	private Integer userId;
	private Double minTimeRec;
	private Double maxTimeRec;
	
	public TimeLimitRec(){}
	
	public TimeLimitRec(Integer userId,Double minTimeRec,Double maxTimeRec){
		this.userId = userId;
		this.minTimeRec = Math.round(minTimeRec*100)/100.0;
		this.maxTimeRec = Math.round(maxTimeRec*100)/100.0;
	}
	
	public void setMinTimeRec(Double minTimeRec){
		this.minTimeRec = Math.round(minTimeRec*100)/100.0;
	}
	
	public void setMaxTimeRec(Double maxTimeRec){
		this.maxTimeRec = Math.round(maxTimeRec*100)/100.0;
	}
}
