package com.worksap.stm2017.vo;

import java.util.List;

import lombok.Data;

@Data
public class RosterVo implements Comparable<RosterVo>{
	private Integer userId;
	private String userName;
	private String monShift;	
	private String tueShift;
	private String wedShift;
	private String thuShift;
	private String friShift;
	private String satShift;
	private String sunShift;
	private Double totalTime;
	private Integer monId;
	private Integer tueId;
	private Integer wedId;
	private Integer thuId;
	private Integer friId;
	private Integer satId;
	private Integer sunId;
	private String nickName;
	
	public RosterVo(){}
	
	public RosterVo(Integer userId,String userName,String monShift,
			String tueShift,String wedShift,String thuShift,
			String friShift,String satShift,String sunShift,Double totalTime,
			Integer monId,Integer tueId,Integer wedId,Integer thuId,
			Integer friId,Integer satId,Integer sunId,String nickName){
		this.userId = userId;
		this.userName = userName;
		this.monShift = monShift;
		this.tueShift = tueShift;
		this.wedShift = wedShift;
		this.thuShift = thuShift;
		this.friShift = friShift;
		this.satShift = satShift;
		this.sunShift = sunShift;
		this.totalTime = Math.round(totalTime*100)/100.0;
		this.monId = monId;
		this.tueId = tueId;
		this.wedId = wedId;
		this.thuId = thuId;
		this.friId = friId;
		this.satId = satId;
		this.sunId = sunId;
		this.nickName = nickName;
	}

	public void setTotalTime(Double totalTime){
		this.totalTime = Math.round(totalTime*100)/100.0;
	}
	
	@Override
	public int compareTo(RosterVo o) {
		return this.totalTime.compareTo(o.totalTime);
	}
}
