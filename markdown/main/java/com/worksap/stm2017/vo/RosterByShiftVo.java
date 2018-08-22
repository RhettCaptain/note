package com.worksap.stm2017.vo;

import java.util.List;

import lombok.Data;

@Data
public class RosterByShiftVo{
	private Integer shiftId;
	private String shiftName;
	private List<String> monStaff;	
	private List<String> tueStaff;	
	private List<String> wedStaff;	
	private List<String> thuStaff;	
	private List<String> friStaff;	
	private List<String> satStaff;	
	private List<String> sunStaff;	
	
	public RosterByShiftVo(){}
	
	public RosterByShiftVo(Integer shiftId,String shiftName,List<String> monStaff,
			List<String> tueStaff,List<String> wedStaff,List<String> thuStaff,
			List<String> friStaff,List<String> satStaff,List<String> sunStaff){
		this.shiftId = shiftId;
		this.shiftName = shiftName;
		this.monStaff = monStaff;
		this.tueStaff = tueStaff;
		this.wedStaff = wedStaff;
		this.thuStaff = thuStaff;
		this.friStaff = friStaff;
		this.satStaff = satStaff;
		this.sunStaff = sunStaff;
	}

}
