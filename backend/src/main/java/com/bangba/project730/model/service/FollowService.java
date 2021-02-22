package com.bangba.project730.model.service;

import java.util.List;

import com.bangba.project730.model.dto.FollowDetailDto;
import com.bangba.project730.model.dto.FollowDto;

public interface FollowService {
	public void follow(FollowDto follow); // 팔로우
	public void unfollow(FollowDto follow); // 언팔로우 
	public int isFollow(FollowDto follow); // 팔로우 유무
	
	public List<FollowDetailDto> selectFollowerList(int pk_user); // 팔로우 리스트
	public List<FollowDetailDto> selectFollowingList(int pk_user); // 팔로잉 리스트
	
	public void deleteUserFollow(int pk_user); // 유저 삭제시 팔로우 리스트에서 삭제
}
