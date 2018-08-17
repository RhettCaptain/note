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
		this.minTimeRec = minTimeRec;
		this.maxTimeRec = maxTimeRec;
	}
}
