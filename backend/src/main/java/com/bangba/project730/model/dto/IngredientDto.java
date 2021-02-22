package com.bangba.project730.model.dto;

public class IngredientDto {
	
	private int pk_ingredient;
	
	private String name_kor;
	private String name_eng;
	private String img_path;
	private String content;
	
	public int getPk_ingredient() {
		return pk_ingredient;
	}
	public void setPk_ingredient(int pk_ingredient) {
		this.pk_ingredient = pk_ingredient;
	}
	public String getName_kor() {
		return name_kor;
	}
	public void setName_kor(String name_kor) {
		this.name_kor = name_kor;
	}
	public String getName_eng() {
		return name_eng;
	}
	public void setName_eng(String name_eng) {
		this.name_eng = name_eng;
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
