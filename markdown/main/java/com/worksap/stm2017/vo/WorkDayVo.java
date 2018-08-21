package com.worksap.stm2017.vo;

import lombok.Data;

@Data
public class WorkDayVo {
	private Double mon;
	private Double tue;
	private Double wed;
	private Double thu;
	private Double fri;
	private Double sat;
	private Double sun;
	
	public WorkDayVo(){}
	
	public WorkDayVo(Double mon,Double tue,Double wed,
			Double thu,Double fri,Double sat,Double sun){
		this.mon = Math.round(mon*100)/100.0;
		this.tue = Math.round(tue*100)/100.0;
		this.wed = Math.round(wed*100)/100.0;
		this.thu = Math.round(thu*100)/100.0;
		this.fri = Math.round(fri*100)/100.0;
		this.sat = Math.round(sat*100)/100.0;
		this.sun = Math.round(sun*100)/100.0;
	}
	
	public void setMon(Double mon){
		this.mon = Math.round(mon*100)/100.0;
	}
	public void setTue(Double tue){
		this.tue = Math.round(tue*100)/100.0;
	}
	public void setWed(Double wed){
		this.wed = Math.round(wed*100)/100.0;
	}
	public void setThu(Double thu){
		this.thu = Math.round(thu*100)/100.0;
	}
	public void setFri(Double fri){
		this.fri = Math.round(fri*100)/100.0;
	}
	public void setSat(Double sat){
		this.sat = Math.round(sat*100)/100.0;
	}
	public void setSun(Double sun){
		this.sun = Math.round(sun*100)/100.0;
	}
}
