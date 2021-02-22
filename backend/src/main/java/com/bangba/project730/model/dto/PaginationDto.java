package com.bangba.project730.model.dto;

public class PaginationDto {
	
	private int page_num; // 현재 페이지
	
	private int forum_cnt_per_page;// 한 페이지당 보여질 게시글 수

	private int page_range; // 현재 페이지 범위 정보
	private int forum_total_cnt; // 게시글 전체 수
	private int range_total_cnt; // 페이지 전체 수
	
	private int forum_start; // 각 페이지에서 시작되는 번호

	
	public int getPage_num() {
		return page_num;
	}

	public void setPage_num(int page_num) {
		this.page_num = page_num;
	}

	public int getForum_cnt_per_page() {
		return forum_cnt_per_page;
	}

	public void setForum_cnt_per_page(int forum_cnt_per_page) {
		this.forum_cnt_per_page = forum_cnt_per_page;
	}

	public int getPage_range() {
		return page_range;
	}

	public void setPage_range(int page_range) {
		this.page_range = page_range;
	}

	public int getForum_total_cnt() {
		return forum_total_cnt;
	}

	public void setForum_total_cnt(int forum_total_cnt) {
		this.forum_total_cnt = forum_total_cnt;
	}

	public int getRange_total_cnt() {
		return range_total_cnt;
	}

	public void setRange_total_cnt(int range_total_cnt) {
		this.range_total_cnt = range_total_cnt;
	}

	public int getForum_start() {
		return forum_start;
	}

	public void setForum_start(int forum_start) {
		this.forum_start = forum_start;
	}

	public void page_info(int page_num, int page_range, int forum_total_cnt, int forum_cnt_per_page) {
		this.page_num = page_num;
		this.page_range = page_range;
		this.forum_total_cnt = forum_total_cnt;
		this.forum_cnt_per_page = forum_cnt_per_page;
		this.forum_start = (page_num - 1) * forum_cnt_per_page;

//		}
	}
}
