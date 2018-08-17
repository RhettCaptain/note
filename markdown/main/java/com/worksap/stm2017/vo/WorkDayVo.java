package com.worksap.stm2017.vo;

import lombok.Data;

@Data
public class WorkDayVo {
	private Double mon;
	private Double tus;
	private Double wed;
	private Double thu;
	private Double fri;
	private Double sat;
	private Double sun;
	
	public WorkDayVo(){}
	
	public WorkDayVo(Double mon,Double tus,Double wed,
			Double thu,Double fri,Double sat,Double sun){
		this.mon = mon;
		this.tus = tus;
		this.wed = wed;
		this.thu = thu;
		this.fri = fri;
		this.sat = sat;
		this.sun = sun;
	}
	
}
