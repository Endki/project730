package com.bangba.project730.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.bangba.project730.model.dto.IngredientDto;
import com.bangba.project730.model.dto.TagDto;

@Mapper
public interface TagDao {

	public void addTag(TagDto tagDto) throws Exception;
	public List<TagDto> searchTag(String searchtxt) throws Exception;
	public List<TagDto> searchArticleTag(int pk_article) throws Exception;
	public TagDto searchTagbyPK(int pk_tag) throws Exception;
	public int searchTagPK(String content) throws Exception;
	public int searchTagPKCount(String content) throws Exception;
	
}
