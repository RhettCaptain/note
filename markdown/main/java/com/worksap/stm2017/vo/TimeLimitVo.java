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
		this.minTime = Math.round(minTime*100)/100.0;
		this.maxTime = Math.round(maxTime*100)/100.0;
		this.minTimeRec = Math.round(minTimeRec*100)/100.0;
		this.maxTimeRec = Math.round(minTimeRec*100)/100.0;
	}
	public void setMinTimeRec(Double minTimeRec){
		this.minTimeRec = Math.round(minTimeRec*100)/100.0;
	}
	
	public void setMaxTimeRec(Double maxTimeRec){
		this.maxTimeRec = Math.round(maxTimeRec*100)/100.0;
	}
}
