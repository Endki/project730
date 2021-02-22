package com.bangba.project730.model.dto;

public class CupDto {
	
	private int pk_cup;
	private String type_kor;
	private String type_eng;
	
	private int size;
	
	private String img_path;

	public int getPk_cup() {
		return pk_cup;
	}

	public void setPk_cup(int pk_cup) {
		this.pk_cup = pk_cup;
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

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String getImg_path() {
		return img_path;
	}

	public void setImg_path(String img_path) {
		this.img_path = img_path;
	}

}
