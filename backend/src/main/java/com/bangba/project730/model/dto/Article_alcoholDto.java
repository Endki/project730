package com.bangba.project730.model.dto;

public class Article_alcoholDto {
	
	private int pk_article_alcohol;
	private int article_no;
	private int alcohol_no;
	
	private String quantity;
	private String unit;
	
	public int getPk_article_alcohol() {
		return pk_article_alcohol;
	}
	public void setPk_article_alcohol(int pk_article_alcohol) {
		this.pk_article_alcohol = pk_article_alcohol;
	}
	public int getArticle_no() {
		return article_no;
	}
	public void setArticle_no(int article_no) {
		this.article_no = article_no;
	}
	public int getAlcohol_no() {
		return alcohol_no;
	}
	public void setAlcohol_no(int alcohol_no) {
		this.alcohol_no = alcohol_no;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	
}
