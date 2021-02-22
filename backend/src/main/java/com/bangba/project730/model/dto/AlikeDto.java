package com.bangba.project730.model.dto;

public class AlikeDto {
	
	private int pk_alike;
	private int article_no;
	private int user_no;
	
	private boolean bookmark;

	public int getPk_alike() {
		return pk_alike;
	}

	public void setPk_alike(int pk_alike) {
		this.pk_alike = pk_alike;
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

	public boolean isBookmark() {
		return bookmark;
	}

	public void setBookmark(boolean bookmark) {
		this.bookmark = bookmark;
	}

}
