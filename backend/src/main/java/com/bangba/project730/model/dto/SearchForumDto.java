package com.bangba.project730.model.dto;

public class SearchForumDto extends PaginationDto {
	
	private String search_type;
	private String keyword;
	
	public String getSearch_type() {
		return search_type;
	}
	public void setSearch_type(String search_type) {
		this.search_type = search_type;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
}
