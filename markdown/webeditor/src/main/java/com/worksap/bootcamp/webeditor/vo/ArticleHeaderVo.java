package com.worksap.bootcamp.webeditor.vo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class ArticleHeaderVo {
	private final String id;
	private final String title;
	private final List<String> tags;


	public static class Builder {
		private String id;
		private String title;
		private List<String> tags = new ArrayList<>();


		public Builder id(String id) {
			this.id = id;
			return this;
		}


		public Builder title(String title) {
			this.title = title;
			return this;
		}


		public Builder tags(List<String> tags) {
			if (tags == null) {
				this.tags = Collections.emptyList();
			} else {
				this.tags = tags;
			}

			return this;
		}


		public ArticleHeaderVo build() {
			return new ArticleHeaderVo(this);
		}
	}


	private ArticleHeaderVo(Builder builder) {
		this.id = builder.id;
		this.title = builder.title;
		this.tags = Collections.unmodifiableList(new ArrayList<>(builder.tags));
	}


	ArticleHeaderVo(String id, String title, List<String> tags) {
		this.id = id;
		this.title = title;
		this.tags = tags;
	}


	public String getId() {
		return id;
	}


	public String getTitle() {
		return title;
	}


	public List<String> getTags() {
		return tags;
	}
}
