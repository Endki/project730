package com.bangba.project730.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.bangba.project730.model.dto.ArticleDto;
import com.bangba.project730.model.dto.ArticleTotalDto;
import com.bangba.project730.model.dto.ForumDto;
import com.bangba.project730.model.dto.UserDto;

@Mapper
public interface UserDao {
	public void createUser(UserDto user) throws Exception;
	public void sendEmail(String toAddress, String subject, String body);
	public int isDuplicatedEmail(String toAddress);
	public int isDuplicatedName(String name);
	public int isDuplicatedPhoneNumber(String phone_number);
	
	public UserDto login(Map<String, String> map) throws Exception;
	
	public UserDto getMyPage(int pk_user);
	public void updateMyPage(UserDto userDto);
	public void updateNameImg(UserDto userDto);
	public void deleteMyPage(int pk_user);
	public List<ArticleTotalDto> bookmarkMyPage(int pk_user);
	public List<ArticleTotalDto> likeMyPage(int pk_user);
	public List<ArticleDto> articleMyPage(int pk_user);

	public String getUserName(int user_no) throws Exception;
	public String getImgPath(int user_no) throws Exception;
	public String getPW(UserDto userDto) throws Exception;
	
	public List<ForumDto> forumMyPage(int pk_user);
	public List<UserDto> getUserInfo(int pk_user) throws Exception;
	public int getUserPk(String user_name) throws Exception;
}
