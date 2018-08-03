package com.worksap.bootcamp.webeditor.entity;

public class TagEntity {
	private String id;
	private String name;


	public TagEntity() {}


	public TagEntity(String id, String name) {
		super();
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
