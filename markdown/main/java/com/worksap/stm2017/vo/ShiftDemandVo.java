package com.worksap.stm2017.vo;

import lombok.Data;

@Data
public class ShiftDemandVo {
	private Integer id;
	private String name;
	private Integer lv1;
	private Integer lv2;
	private Integer lv3;
	private Integer lv4;
	private Integer lv5;
	private Integer lv1rec;
	private Integer lv2rec;
	private Integer lv3rec;
	private Integer lv4rec;
	private Integer lv5rec;
	
	public ShiftDemandVo(){}
	
	public ShiftDemandVo(Integer id,String name,Integer lv1,Integer lv2,
			Integer lv3,Integer lv4,Integer lv5,Integer lv1rec,Integer lv2rec,
			Integer lv3rec,Integer lv4rec,Integer lv5rec){
		this.id = id;
		this.name = name;
		this.lv1 = lv1;
		this.lv2 = lv2;
		this.lv3 = lv3;
		this.lv4 = lv4;
		this.lv5 = lv5;
		this.lv1rec = lv1rec;
		this.lv2rec = lv2rec;
		this.lv3rec = lv3rec;
		this.lv4rec = lv4rec;
		this.lv5rec = lv5rec;
	}
}
