package com.worksap.stm2017.vo;

import java.sql.Time;

import lombok.Data;

@Data
public class ShiftTypeVo {
	private Integer id;
	private String name;
	private Time beginTime;
	private Time endTime;
	private Double total;
	private Boolean used;
	private Double score;
	
	public ShiftTypeVo(){}
	
	public ShiftTypeVo(Integer id,String name,Time beginTime,Time endTime,
			Double total,Boolean used,Double score){
		this.id = id;
		this.name = name;
		this.beginTime = beginTime;
		this.endTime = endTime;
		this.total = Math.round(total*100)/100.0;
		this.used = used;
		this.score = Math.round(score*100)/100.0;
	}
	
	public void setTotal(Double total){
		this.total = Math.round(total*100)/100.0;
	}
	public void setScore(Double score){
		this.score = Math.round(score*100)/100.0;
	}
}
