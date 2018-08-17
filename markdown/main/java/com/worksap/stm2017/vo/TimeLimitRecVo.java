package com.worksap.stm2017.vo;

import lombok.Data;

@Data
public class TimeLimitRecVo {
	private Double minTimeRec;
	private Double maxTimeRec;
	
	public TimeLimitRecVo(){}
	
	public TimeLimitRecVo(Double minTimeRec,Double maxTimeRec){
		this.minTimeRec = minTimeRec;
		this.maxTimeRec = maxTimeRec;
	}
}
