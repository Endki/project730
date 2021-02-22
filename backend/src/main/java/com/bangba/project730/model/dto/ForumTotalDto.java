package com.bangba.project730.model.dto;

public class ForumTotalDto {
	
	private int pk_forum;
	private int user_no;
	private String user_name;
	
	private String title;
	private String category;
	
	private int like_cnt;
	private int hits;
	
	private String created_at;
	private String updated_at;
	private String content;
	private String img_path;
	
	public String getImg_path() {
		return img_path;
	}

	public void setImg_path(String img_path) {
		this.img_path = img_path;
	}

	private int comment_cnt;

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

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
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

	public int getHits() {
		return hits;
	}

	public void setHits(int hits) {
		this.hits = hits;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public String getUpdated_at() {
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

	public int getComment_cnt() {
		return comment_cnt;
	}

	public void setComment_cnt(int comment_cnt) {
		this.comment_cnt = comment_cnt;
	}
	
	
}
