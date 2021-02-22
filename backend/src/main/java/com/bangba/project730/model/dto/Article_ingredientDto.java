package com.bangba.project730.model.dto;

public class Article_ingredientDto {
	
	private int pk_article_ingredient;
	private int article_no;
	private int ingredient_no;
	
	private boolean type;
	
	private String quantity;
	private String unit;
	
	public int getPk_article_ingredient() {
		return pk_article_ingredient;
	}
	public void setPk_article_ingredient(int pk_article_ingredient) {
		this.pk_article_ingredient = pk_article_ingredient;
	}
	public int getArticle_no() {
		return article_no;
	}
	public void setArticle_no(int article_no) {
		this.article_no = article_no;
	}
	public int getIngredient_no() {
		return ingredient_no;
	}
	public void setIngredient_no(int ingredient_no) {
		this.ingredient_no = ingredient_no;
	}
	public boolean isType() {
		return type;
	}
	public void setType(boolean type) {
		this.type = type;
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
