package com.bangba.project730.model.dto;

public class ForumDto {
	
	private int pk_forum;
	private int user_no;
	
	private String title;
	private String category;
	
	private int like_cnt;
	private int htis;
	
	private String created_at;
	private String updated_at;
	private String content;

	public int getPk_forum() {
		return pk_forum;
	}

	public void setPk_forum(int pk_forum) {
		this.pk_forum = pk_forum;
	}

	public int getUser_no() {
		return user_no;
	}

	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getLike_cnt() {
		return like_cnt;
	}

	public void setLike_cnt(int like_cnt) {
		this.like_cnt = like_cnt;
	}

	public int getHtis() {
		return htis;
	}

	public void setHtis(int htis) {
		this.htis = htis;
	}

	public String isCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public String isUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(String updated_at) {
		this.updated_at = updated_at;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	
}