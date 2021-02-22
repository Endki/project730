package com.bangba.project730.model.dto;

public class FollowDetailDto {
	private int pk_user;
	private String user_name;
	private String img_path;
	private int follow_cnt;
	
	public int getPk_user() {
		return pk_user;
	}
	public void setPk_user(int pk_user) {
		this.pk_user = pk_user;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public int getFollow_cnt() {
		return follow_cnt;
	}
	public void setFollow_cnt(int follow_cnt) {
		this.follow_cnt = follow_cnt;
	}
	public String getImg_path() {
		return img_path;
	}
	public void setImg_path(String img_path) {
		this.img_path = img_path;
	}
}
