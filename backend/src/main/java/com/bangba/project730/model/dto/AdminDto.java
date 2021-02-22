package com.bangba.project730.model.dto;

public class AdminDto {
	
	private int pk_admin;
	private int user_no;
	
	private boolean user_mgt;
	private boolean article_mgt;
	private boolean forum_mgt;
	
	public int getPk_admin() {
		return pk_admin;
	}
	public void setPk_admin(int pk_admin) {
		this.pk_admin = pk_admin;
	}
	public int getUser_no() {
		return user_no;
	}
	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}
	public boolean isUser_mgt() {
		return user_mgt;
	}
	public void setUser_mgt(boolean user_mgt) {
		this.user_mgt = user_mgt;
	}
	public boolean isArticle_mgt() {
		return article_mgt;
	}
	public void setArticle_mgt(boolean article_mgt) {
		this.article_mgt = article_mgt;
	}
	public boolean isForum_mgt() {
		return forum_mgt;
	}
	public void setForum_mgt(boolean forum_mgt) {
		this.forum_mgt = forum_mgt;
	}
	
}
