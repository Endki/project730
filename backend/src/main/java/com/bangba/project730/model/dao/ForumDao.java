package com.bangba.project730.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.bangba.project730.model.dto.FcommentDto;
import com.bangba.project730.model.dto.ForumDto;
import com.bangba.project730.model.dto.ForumTotalDto;
import com.bangba.project730.model.dto.SearchForumDto;


@Mapper
public interface ForumDao {
    public int createForum(ForumDto forumdto) throws Exception;
    public List<ForumDto> searchForumList(SearchForumDto searchForumDto) throws Exception;
    public ForumDto detailForum(int pk_forum) throws Exception;
    public void updateForum(ForumDto forumDto) throws Exception;
    public void deleteForum(int pk_forum) throws Exception;
    public void updateHits(int hits) throws Exception;
    public List<ForumDto> searchNotices() throws Exception;
    public int getForumListCnt(SearchForumDto searchForumDto) throws Exception;
    
    public void createComment(FcommentDto fcommentDto) throws Exception;
	public List<FcommentDto> searchComment(int pk_forum) throws Exception;
	public void updateComment(FcommentDto fcommentDto) throws Exception;
	public void deleteComment(int pk_acomment) throws Exception;
	
	
	//좋아요
	public void insertLike(int user_no, int forum_no) throws Exception;
	public void deleteLike(int user_no, int forum_no) throws Exception;
	public void upLike(int user_no, int forum_no) throws Exception;
	public void downLike(int user_no, int forum_no) throws Exception;
	public int isLike(int user_no, int forum_no) throws Exception;
	public  List<ForumTotalDto>  searchNewForum() throws Exception;
}