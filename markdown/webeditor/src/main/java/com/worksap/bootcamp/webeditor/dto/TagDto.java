package com.worksap.bootcamp.webeditor.dto;


public final class TagDto {
	private String id;
	private String name;


	public TagDto() {}


	public TagDto(String id, String name) {
		this.id = id;
		this.name = name;
	}


	public String getId() {
		return id;
	}


	public String getName() {
		return name;
	}


	public void setId(String id) {
		this.id = id;
	}


	public void setName(String name) {
		this.name = name;
	}
}
