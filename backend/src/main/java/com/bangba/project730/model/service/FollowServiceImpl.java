package com.bangba.project730.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bangba.project730.model.dao.FollowDao;
import com.bangba.project730.model.dto.FollowDetailDto;
import com.bangba.project730.model.dto.FollowDto;
import com.bangba.project730.model.dto.UserDto;

@Service
public class FollowServiceImpl implements FollowService{

	@Autowired
	private FollowDao dao;
	
	@Override
	public void follow(FollowDto follow) {
		dao.follow(follow);
	}

	@Override
	public void unfollow(FollowDto follow) {
		dao.unfollow(follow);
	}

	@Override
	public int isFollow(FollowDto follow) {
		return dao.isFollow(follow);
	}

	@Override
	public List<FollowDetailDto> selectFollowerList(int pk_user) {
		return dao.selectFollowerList(pk_user);
	}

	@Override
	public List<FollowDetailDto> selectFollowingList(int pk_user) {
		return dao.selectFollowingList(pk_user);
	}

	@Override
	public void deleteUserFollow(int pk_user) {
		dao.deleteUserFollow(pk_user);
	}
	
}
