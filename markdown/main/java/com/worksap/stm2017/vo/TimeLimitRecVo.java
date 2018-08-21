package com.worksap.stm2017.vo;

import lombok.Data;

@Data
public class TimeLimitRecVo {
	private Double minTimeRec;
	private Double maxTimeRec;
	
	public TimeLimitRecVo(){}
	
	public TimeLimitRecVo(Double minTimeRec,Double maxTimeRec){
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
