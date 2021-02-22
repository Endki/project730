package com.bangba.project730.model.dto;

public class AlcoholDto {
	
	private int pk_alcohol;
	
	private String type_kor;
	private String type_eng;
	private String name_kor;
	private String name_eng;
	
	private int abv;
	
	private String img_path;
	private String content;
	public int getPk_alcohol() {
		return pk_alcohol;
	}
	public void setPk_alcohol(int pk_alcohol) {
		this.pk_alcohol = pk_alcohol;
	}
	public String getType_kor() {
		return type_kor;
	}
	public void setType_kor(String type_kor) {
		this.type_kor = type_kor;
	}
	public String getType_eng() {
		return type_eng;
	}
	public void setType_eng(String type_eng) {
		this.type_eng = type_eng;
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
	public int getAbv() {
		return abv;
	}
	public void setAbv(int abv) {
		this.abv = abv;
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
