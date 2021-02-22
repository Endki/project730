package com.bangba.project730.model.dto;

public class AcommentDto {
	
	private int pk_acomment;
	private int article_no;
	private int user_no;
	
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

	public int getUser_no() {
		return user_no;
	}

	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
