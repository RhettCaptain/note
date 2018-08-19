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
		this.compScore = compScore;
		this.minShiftPref = minShiftPref;
		this.avgShiftPref = avgShiftPref;
		this.maxShiftPref = maxShiftPref;
		this.minWorkTime = minWorkTime;
		this.avgWorkTime = avgWorkTime;
		this.maxWorkTime = maxWorkTime;
		this.minWorkdayPref = minWorkdayPref;
		this.avgWorkdayPref = avgWorkdayPref;
		this.maxWorkdayPref = maxWorkdayPref;
		this.shiftDemands = shiftDemands;
		
	}
}
