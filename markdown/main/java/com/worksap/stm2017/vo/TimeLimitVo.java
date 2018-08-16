package com.worksap.stm2017.vo;

import lombok.Data;

@Data
public class TimeLimitVo {
	private Double minTime;
	private Double maxTime;
	private Double minTimeRec;
	private Double maxTimeRec;
	
	public TimeLimitVo(){}
	
	public TimeLimitVo(Double minTime,Double maxTime,Double minTimeRec,Double maxTimeRec){
		this.minTime = minTime;
		this.maxTime = maxTime;
		this.minTimeRec = minTimeRec;
		this.maxTimeRec = maxTimeRec;
	}
}
