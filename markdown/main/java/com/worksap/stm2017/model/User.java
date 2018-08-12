package com.worksap.stm2017.model;

import java.util.List;

import lombok.Data;

@Data
public class User {
	
	private Integer userId;
	private String name;
	private String password;
	private Integer authLevel;
	private Integer workLevel;
	private List<Integer> groupId;
}
