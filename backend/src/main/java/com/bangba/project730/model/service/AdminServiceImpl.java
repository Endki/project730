package com.bangba.project730.model.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bangba.project730.model.dao.AdminDao;
import com.bangba.project730.model.dao.ArticleDao;
import com.bangba.project730.model.dao.UserDao;
import com.bangba.project730.model.dto.AdminDto;
import com.bangba.project730.model.dto.ArticleDto;
import com.bangba.project730.model.dto.ArticleTotalDto;
import com.bangba.project730.model.dto.TagDto;
import com.bangba.project730.model.dto.UserDto;
import com.bangba.project730.model.dto.UserRankDto;

@Service
public class AdminServiceImpl implements AdminService{
	
	@Autowired
	private AdminDao dao;

	@Autowired
	private UserDao udao;
	
	@Autowired
	private ArticleDao adao;
	@Override
	public List<UserDto> searchAll() {
		return dao.searchAll();
	}
	
	@Override
	public List<UserDto> searchByName(String keyword) {
		return dao.searchByName(keyword);
	}

	@Override
	public void deleteUser(int user_no) {
		dao.deleteUser(user_no);
	}

	@Override
	public void changeToAdmin(UserDto userDto) {
		dao.changeToAdmin(userDto);
	}

	@Override
	public void banUser(UserDto userDto) {
		dao.banUser(userDto);
	}

	@Override
	public void createAdmin(int user_no) {
		dao.createAdmin(user_no);
	}

	@Override
	public List<UserRankDto> userRankWeekly() {
		// TODO Auto-generated method stub
		return dao.userRankWeekly();
	}

	@Override
	public List<ArticleTotalDto> articleRankWeekly() throws Exception {
		// TODO Auto-generated method stub
		List<ArticleDto> ladto = dao.articleRankWeekly();
		List<ArticleTotalDto> latdto = new ArrayList<ArticleTotalDto>();
		for(ArticleDto adto:ladto)
		{
			ArticleTotalDto atdto = new ArticleTotalDto();
			atdto.setPk_article(adto.getPk_article());
			atdto.setUser_name(udao.getUserName(adto.getUser_no()));
			atdto.setUser_img(udao.getMyPage(adto.getUser_no()).getImg_path());
			atdto.setTitle_kor(adto.getTitle_kor());
			atdto.setTitle_eng(atdto.getTitle_eng());
			atdto.setLike_cnt(adto.getLike_cnt());
			atdto.setBookmark_cnt(adto.getBookmark_cnt());
			atdto.setHits(adto.getHits());
			atdto.setCreated_at(adto.getCreated_at());
			atdto.setUpdated_at(adto.getUpdated_at());
			atdto.setLike_weekly(adto.getLike_weekly());
			atdto.setContent(adto.getContent());
			atdto.setImg_path(adto.getImg_path());
			atdto.setCategory(adto.isCategory());
			String t="";
			List<TagDto> ltdto = adao.getTag(adto.getPk_article());
			int i=1;
			for(TagDto tdto:ltdto)
			{
				t+=tdto.getContent_kor();
				if(i<ltdto.size())
					t+="<br>";
				i++;
			}
			atdto.setTag(t);
			latdto.add(atdto);
		}
		return latdto;
	}
}
