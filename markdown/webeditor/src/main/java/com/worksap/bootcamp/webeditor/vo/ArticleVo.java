package com.worksap.bootcamp.webeditor.vo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class ArticleVo {
	private final String id;
	private final String content;
	private final String title;
	private final List<String> tags;


	public static class Builder {
		private String id;
		private String content;
		private String title;
		private List<String> tags = new ArrayList<>();


		public Builder id(String id) {
			this.id = id;
			return this;
		}


		public Builder content(String content) {
			this.content = content;
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


		public Builder title(String title) {
			this.title = title;
			return this;
		}


		public ArticleVo build() {
			return new ArticleVo(this);
		}
	}


	private ArticleVo(Builder builder) {
		this.id = builder.id;
		this.content = builder.content;
		this.title = builder.title;
		this.tags = Collections.unmodifiableList(new ArrayList<>(builder.tags));
	}


	ArticleVo(String id, String content, String title, List<String> tags) {
		this.id = id;
		this.content = content;
		this.title = title;
		this.tags = tags;
	}


	public String getId() {
		return id;
	}


	public String getContent() {
		return content;
	}


	public String getTitle() {
		return title;
	}


	public List<String> getTags() {
		return tags;
	}
}
