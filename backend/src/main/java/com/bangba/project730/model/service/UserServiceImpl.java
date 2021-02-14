package com.bangba.project730.model.service;

import java.util.List;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.bangba.project730.model.dao.UserDao;
import com.bangba.project730.model.dto.ArticleDto;
import com.bangba.project730.model.dto.ArticleTotalDto;
import com.bangba.project730.model.dto.ForumDto;
import com.bangba.project730.model.dto.TagDto;
import com.bangba.project730.model.dto.UserDto;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private  UserDao dao;
	
	@Autowired
	private JavaMailSender sender;
	
	@Override
	public void createUser(UserDto userDto) throws Exception {
		dao.createUser(userDto);
	}

	@Override
	public UserDto login(Map<String, String> map) throws Exception {
		if(map.get("email") == null || map.get("password") == null) {
			return null;
		}
		return dao.login(map);
	}

	@Override
	public void sendEmail(String toAddress, String subject, String body) {
		
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		
		try {
			helper.setTo(toAddress);
			helper.setSubject(subject);
			helper.setText(body);
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		sender.send(message);
	}

	@Override
	public int isDuplicatedEmail(String toAddress) {
		return dao.isDuplicatedEmail(toAddress);
	}

	@Override
	public int isDuplicatedName(String name) {
		return dao.isDuplicatedName(name);
	}

	@Override
	public UserDto getMyPage(int pk_user) {
		return dao.getMyPage(pk_user);
	}

	@Override
	public void updateMyPage(UserDto userDto) {
		dao.updateMyPage(userDto);
	}

	@Override
	public void deleteMyPage(int pk_user) {
		dao.deleteMyPage(pk_user);
	}

	@Override
	public List<ArticleTotalDto> bookmarkMyPage(int pk_user) {
		return dao.bookmarkMyPage(pk_user);
	}

	@Override
	public List<ArticleDto> articleMyPage(int pk_user) {
		// TODO Auto-generated method stub
		return dao.articleMyPage(pk_user);
	}

	@Override
	public int isDuplicatedPhoneNumber(String phone_number) {
		return dao.isDuplicatedPhoneNumber(phone_number);
	}
	
	@Override
	public List<TagDto> getTagMyPage(int pk_article) throws Exception {
		return dao.getTagMyPage(pk_article);
	}

	@Override
	public List<ForumDto> forumMyPage(int pk_user) throws Exception {
		// TODO Auto-generated method stub
		return dao.forumMyPage(pk_user);
	}
}
