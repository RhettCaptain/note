package com.worksap.stm2017.model;

import lombok.Data;

@Data
public class ShiftDemand {
	private Integer id;
	private Integer lv1;
	private Integer lv2;
	private Integer lv3;
	private Integer lv4;
	private Integer lv5;
	
	public ShiftDemand(){}
	
	public ShiftDemand(Integer id,Integer lv1,Integer lv2,
			Integer lv3,Integer lv4,Integer lv5){
		this.id = id;
		this.lv1 = lv1;
		this.lv2 = lv2;
		this.lv3 = lv3;
		this.lv4 = lv4;
		this.lv5 = lv5;
	}
}
