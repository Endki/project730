package com.bangba.project730.model.dto;

public class TagDto {
	
	private int pk_tag;
	
	private String content_kor;
	private String content_eng;
	
	private int type;

	public int getPk_tag() {
		return pk_tag;
	}

	public void setPk_tag(int pk_tag) {
		this.pk_tag = pk_tag;
	}

	public String getContent_kor() {
		return content_kor;
	}

	public void setContent_kor(String content_kor) {
		this.content_kor = content_kor;
	}

	public String getContent_eng() {
		return content_eng;
	}

	public void setContent_eng(String content_eng) {
		this.content_eng = content_eng;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

}
