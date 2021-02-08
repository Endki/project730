package com.bangba.project730.model.service;

import java.util.List;
import java.util.Map;

import com.bangba.project730.model.dto.AlcoholDto;
import com.bangba.project730.model.dto.ArticleDto;
import com.bangba.project730.model.dto.ArticleTotalDto;
import com.bangba.project730.model.dto.AtoA;
import com.bangba.project730.model.dto.AtoI;
import com.bangba.project730.model.dto.IngredientDto;
import com.bangba.project730.model.dto.RecipeDto;
import com.bangba.project730.model.dto.TagDto;

public interface ArticleService {
	public void createArticle(Map<String, String> map) throws Exception;
	public List<ArticleDto> searchArticle(Map<String, String> map) throws Exception;
	public void updateArticle(Map<String, String> map) throws Exception;
	public void deleteArticle(int pk_article) throws Exception;
	public Map<String,String> detailArticle(int pk_article) throws Exception;

	public List<AlcoholDto> searchAlcohol(String searchtxt) throws Exception;
	public void createIngredient(String name) throws Exception;
	public List<IngredientDto> searchIngredient(String searchtxt) throws Exception;
	public void createTag(String content,int type) throws Exception;
	public List<TagDto> searchTag(String searchtxt) throws Exception;
	
	//추가로 구현된 함수
	public List<AtoA> getAlcohol(int pk_article) throws Exception;
	public List<AtoI> getIngredient(int pk_article) throws Exception;
	public List<RecipeDto> getRecipe(int pk_article) throws Exception;
	public List<TagDto> getTag(int pk_article) throws Exception;
}
