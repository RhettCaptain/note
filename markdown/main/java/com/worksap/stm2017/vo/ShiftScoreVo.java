package com.worksap.stm2017.vo;

import lombok.Data;

@Data
public class ShiftScoreVo {
	private Integer id;
	private String name;
	private Integer lv1;
	private Integer lv2;
	private Integer lv3;
	private Integer lv4;
	private Integer lv5;
	private Double score;
	
	public ShiftScoreVo(){}
	
	public ShiftScoreVo(Integer id,String name,Integer lv1,
			Integer lv2,Integer lv3,Integer lv4,Integer lv5,Double score){
		this.id = id;
		this.name = name;
		this.lv1 = lv1;
		this.lv2 = lv2;
		this.lv3 = lv3;
		this.lv4 = lv4;
		this.lv5 = lv5;
		this.score = Math.round(score*100)/100.0;
	}
	
	public void setScore(Double score){
		this.score = score;
	}
}
