package com.bangba.project730.model.service;

import java.util.List;
import java.util.Map;

import com.bangba.project730.model.dto.ArticleDto;
import com.bangba.project730.model.dto.ArticleTotalDto;
import com.bangba.project730.model.dto.ForumDto;
import com.bangba.project730.model.dto.UserDto;

public interface UserService {
	// 회원가입 관련 함수
	public void createUser(UserDto userDto) throws Exception;
	public void sendEmail(String toAddress, String subject, String body);
	public int isDuplicatedEmail(String toAddress);
	public int isDuplicatedName(String name);
	public int isDuplicatedPhoneNumber(String phone_number);
	
	// 로그인 관련 함수 
	public UserDto login(Map<String, String> map) throws Exception;
	
	// 로그인 한 후
	public UserDto getMyPage(int pk_user);
	public void updateMyPage(UserDto userDto);
	public void updateNameImg(UserDto userDto);
	public void deleteMyPage(int pk_user);
	public List<ArticleTotalDto> bookmarkMyPage(int pk_user);
	public List<ArticleTotalDto> likeMyPage(int pk_user);
	public List<ArticleDto> articleMyPage(int pk_user);
	
	//마이페이지
	public List<ForumDto> forumMyPage(int pk_user)throws Exception;
	public String getPW(UserDto userDto) throws Exception;
	public List<UserDto> getUserInfo(int pk_user) throws Exception;
	
	public int getUserPk(String user_name) throws Exception;
}
