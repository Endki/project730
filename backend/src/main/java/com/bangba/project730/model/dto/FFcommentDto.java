package com.bangba.project730.model.dto;

public class FFcommentDto {
	
	private int pk_fcomment;
	private int forum_no;

	private String user_name;
	private String img_path;
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
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getImg_path() {
		return img_path;
	}
	public void setImg_path(String img_path) {
		this.img_path = img_path;
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
