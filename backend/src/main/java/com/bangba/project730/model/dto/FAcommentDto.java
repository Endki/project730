package com.bangba.project730.model.dto;

public class FAcommentDto {

	private int pk_acomment;
	private int article_no;

	private String user_name;
	private String img_path;
	private String content;
	
	public int getPk_acomment() {
		return pk_acomment;
	}
	public void setPk_acomment(int pk_acomment) {
		this.pk_acomment = pk_acomment;
	}
	public int getArticle_no() {
		return article_no;
	}
	public void setArticle_no(int article_no) {
		this.article_no = article_no;
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
