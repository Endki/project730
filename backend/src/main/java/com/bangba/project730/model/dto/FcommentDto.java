package com.bangba.project730.model.dto;

public class FcommentDto {
	
	private int pk_fcomment;
	private int forum_no;
	private int user_no;

	private String created_at;
	private String content;
	
	public int getPk_fcomment() {
		return pk_fcomment;
	}
	public void setPk_fcomment(int pk_fcomment) {
		this.pk_fcomment = pk_fcomment;
	}
	public int getForum_no() {
		return forum_no;
	}
	public void setForum_no(int forum_no) {
		this.forum_no = forum_no;
	}
	public int getUser_no() {
		return user_no;
	}
	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}
	public String getCreated_at() {
		return created_at;
	}
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

}
