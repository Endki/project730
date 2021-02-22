package com.bangba.project730.model.service;

import java.util.List;
import com.bangba.project730.model.dto.ArticleTotalDto;
import com.bangba.project730.model.dto.UserDto;
import com.bangba.project730.model.dto.UserRankDto;

public interface AdminService {
	public List<UserDto> searchByName(String keyword);
	public List<UserDto> searchAll();
	public void deleteUser(int user_no);
	public void changeToAdmin(UserDto userDto);
	public void banUser(UserDto userDto);
	public void createAdmin(int user_no);
	public List<UserRankDto> userRankWeekly();
	public List<ArticleTotalDto> articleRankWeekly() throws Exception;
}
