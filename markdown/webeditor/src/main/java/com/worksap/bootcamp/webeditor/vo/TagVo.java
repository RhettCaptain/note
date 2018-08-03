package com.worksap.bootcamp.webeditor.vo;



public final class TagVo {
	private final String id;
	private final String name;


	public static class Builder {
		private String id;
		private String name;


		public Builder id(String id) {
			this.id = id;
			return this;
		}


		public Builder name(String name) {
			this.name = name;
			return this;
		}


		public TagVo build() {
			return new TagVo(this);
		}
	}


	private TagVo(Builder builder) {
		this.id = builder.id;
		this.name = builder.name;
	}


	TagVo(String id, String name) {
		this.id = id;
		this.name = name;
	}


	public String getId() {
		return id;
	}


	public String getName() {
		return name;
	}
}
