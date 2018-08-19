package com.worksap.stm2017.vo;

import java.util.List;

import lombok.Data;

@Data
public class RosterVo implements Comparable<RosterVo>{
	private Integer userId;
	private String userName;
	private String monShift;	
	private String tusShift;
	private String wedShift;
	private String thuShift;
	private String friShift;
	private String satShift;
	private String sunShift;
	private Double totalTime;
	private Integer monId;
	private Integer tusId;
	private Integer wedId;
	private Integer thuId;
	private Integer friId;
	private Integer satId;
	private Integer sunId;
	
	public RosterVo(){}
	
	public RosterVo(Integer userId,String userName,String monShift,
			String tusShift,String wedShift,String thuShift,
			String friShift,String satShift,String sunShift,Double totalTime,
			Integer monId,Integer tusId,Integer wedId,Integer thuId,
			Integer friId,Integer satId,Integer sunId){
		this.userId = userId;
		this.userName = userName;
		this.monShift = monShift;
		this.tusShift = tusShift;
		this.wedShift = wedShift;
		this.thuShift = thuShift;
		this.friShift = friShift;
		this.satShift = satShift;
		this.sunShift = sunShift;
		this.totalTime = totalTime;
		this.monId = monId;
		this.tusId = tusId;
		this.wedId = wedId;
		this.thuId = thuId;
		this.friId = friId;
		this.satId = satId;
		this.sunId = sunId;
	}

	@Override
	public int compareTo(RosterVo o) {
		return this.totalTime.compareTo(o.totalTime);
	}
}
