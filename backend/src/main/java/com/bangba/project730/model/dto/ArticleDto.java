package com.bangba.project730.model.dto;

public class ArticleDto {
	
	private int pk_article;
	private int user_no;
	
	private String title_kor;
	private String title_eng;
	
	private int like_cnt;
	private int bookmark_cnt;
	private int hits;
	
	private String created_at;
	private String updated_at;
	
	private int like_weekly;
	
	private String content;
	private String img_path;
	
	private boolean category;
	
	private int abv;
	private int cup_no;
	
	public int getPk_article() {
		return pk_article;
	}
	public void setPk_article(int pk_article) {
		this.pk_article = pk_article;
	}
	public int getUser_no() {
		return user_no;
	}
	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}
	public String getTitle_kor() {
		return title_kor;
	}
	public void setTitle_kor(String title_kor) {
		this.title_kor = title_kor;
	}
	public String getTitle_eng() {
		return title_eng;
	}
	public void setTitle_eng(String title_eng) {
		this.title_eng = title_eng;
	}
	public int getLike_cnt() {
		return like_cnt;
	}
	public void setLike_cnt(int like_cnt) {
		this.like_cnt = like_cnt;
	}
	public int getBookmark_cnt() {
		return bookmark_cnt;
	}
	public void setBookmark_cnt(int bookmark_cnt) {
		this.bookmark_cnt = bookmark_cnt;
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
	public int getLike_weekly() {
		return like_weekly;
	}
	public void setLike_weekly(int like_weekly) {
		this.like_weekly = like_weekly;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getImg_path() {
		return img_path;
	}
	public void setImg_path(String img_path) {
		this.img_path = img_path;
	}
	public boolean isCategory() {
		return category;
	}
	public void setCategory(boolean category) {
		this.category = category;
	}
	public int getAbv() {
		return abv;
	}
	public void setAbv(int abv) {
		this.abv = abv;
	}
	public int getCup_no() {
		return cup_no;
	}
	public void setCup_no(int cup_no) {
		this.cup_no = cup_no;
	}
	
}
