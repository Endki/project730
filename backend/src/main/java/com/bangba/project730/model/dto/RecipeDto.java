package com.bangba.project730.model.dto;

public class RecipeDto {

	private int pk_recipe;
	private int article_no;
	
	private String content;
	
	private int num;

	public int getPk_recipe() {
		return pk_recipe;
	}

	public void setPk_recipe(int pk_recipe) {
		this.pk_recipe = pk_recipe;
	}

	public int getArticle_no() {
		return article_no;
	}

	public void setArticle_no(int article_no) {
		this.article_no = article_no;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}
	
}
