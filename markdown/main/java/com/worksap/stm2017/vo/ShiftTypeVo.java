package com.worksap.stm2017.vo;

import lombok.Data;

@Data
public class ShiftTypeVo {
	private String name;
	private String time;
	private String total;
	private String state;
	private String score;
	
	public ShiftTypeVo(){}
	
	public ShiftTypeVo(String name,String time,
			String total,String state,String score){
		this.name = name;
		this.time = time;
		this.total = total;
		this.state = state;
		this.score = score;
	}
}
