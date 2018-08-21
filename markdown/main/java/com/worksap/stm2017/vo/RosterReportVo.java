package com.worksap.stm2017.vo;

import java.util.List;

import lombok.Data;

@Data
public class RosterReportVo {
	private Boolean timeLimitOk;
	private Boolean demandOk;
	private Double compScore;
	private Double minShiftPref;
	private Double avgShiftPref;
	private Double maxShiftPref;
	private Double minWorkTime;
	private Double avgWorkTime;
	private Double maxWorkTime;
	private Double minWorkdayPref;
	private Double avgWorkdayPref;
	private Double maxWorkdayPref;
	private List<List<ShiftDemandVo>> shiftDemands;
	
	public RosterReportVo(){};
	public RosterReportVo(Boolean timeLimitOk,Boolean demandOk,Double compScore,Double minShiftPref,Double avgShiftPref,Double maxShiftPref,
			Double minWorkTime,Double avgWorkTime,Double maxWorkTime,Double minWorkdayPref,
			Double avgWorkdayPref,Double maxWorkdayPref,List<List<ShiftDemandVo>> shiftDemands){
		this.timeLimitOk = timeLimitOk;
		this.demandOk = demandOk;
		this.compScore = Math.round(compScore*100)/100.0;
		this.minShiftPref = Math.round(minShiftPref*100)/100.0;
		this.avgShiftPref = Math.round(avgShiftPref*100)/100.0;
		this.maxShiftPref = Math.round(maxShiftPref*100)/100.0;
		this.minWorkTime = Math.round(minWorkTime*100)/100.0;
		this.avgWorkTime = Math.round(avgWorkTime*100)/100.0;
		this.maxWorkTime = Math.round(maxWorkTime*100)/100.0;
		this.minWorkdayPref = Math.round(minWorkdayPref*100)/100.0;
		this.avgWorkdayPref = Math.round(avgWorkdayPref*100)/100.0;
		this.maxWorkdayPref = Math.round(maxWorkdayPref*100)/100.0;
		this.shiftDemands = shiftDemands;	
	}
	
	public void setCompScore(Double compScore){
		this.compScore = Math.round(compScore*100)/100.0;
	}
	
	public void setMinShiftPref(Double minShiftPref){
		this.minShiftPref = Math.round(minShiftPref*100)/100.0;
	}
	public void setAvgShiftPref(Double avgShiftPref){
		this.avgShiftPref = Math.round(avgShiftPref*100)/100.0;
	}
	public void setMaxShiftPref(Double maxShiftPref){
		this.maxShiftPref = Math.round(maxShiftPref*100)/100.0;
	}
	
	public void setMinWorkTime(Double minWorkTime){
		this.minWorkTime = Math.round(minWorkTime*100)/100.0;
	}
	public void setAvgWorkTime(Double avgWorkTime){
		this.avgWorkTime = Math.round(avgWorkTime*100)/100.0;
	}
	public void setMaxWorkTime(Double maxWorkTime){
		this.maxWorkTime = Math.round(maxWorkTime*100)/100.0;
	}
	
	public void setMinWorkdayPref(Double minWorkdayPref){
		this.minWorkdayPref = Math.round(minWorkdayPref*100)/100.0;
	}
	public void setAvgWorkdayPref(Double avgWorkdayPref){
		this.avgWorkdayPref = Math.round(avgWorkdayPref*100)/100.0;
	}
	public void setMaxWorkdayPref(Double maxWorkdayPref){
		this.maxWorkdayPref = Math.round(maxWorkdayPref*100)/100.0;
	}
	
	
}
