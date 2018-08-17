package com.worksap.stm2017.model;

import lombok.Data;

@Data
public class ShiftScore {
	private Integer shiftId;
	private Integer userId;
	private Integer lv1;
	private Integer lv2;
	private Integer lv3;
	private Integer lv4;
	private Integer lv5;
	private Double score;
	
	public ShiftScore(){}
	
	public ShiftScore(Integer shiftId,Integer userId,Integer lv1,
			Integer lv2,Integer lv3,Integer lv4,Integer lv5,Double score){
		this.shiftId = shiftId;
		this.userId = userId;
		this.lv1 = lv1;
		this.lv2 = lv2;
		this.lv3 = lv3;
		this.lv4 = lv4;
		this.lv5 = lv5;
		this.score = score;
	}
}
