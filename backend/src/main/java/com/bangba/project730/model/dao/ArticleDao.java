package com.bangba.project730.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.bangba.project730.model.dto.ArticleDto;
import com.bangba.project730.model.dto.Article_alcoholDto;
import com.bangba.project730.model.dto.Article_cupDto;
import com.bangba.project730.model.dto.Article_ingredientDto;
import com.bangba.project730.model.dto.Article_tagDto;
import com.bangba.project730.model.dto.RecipeDto;
import com.bangba.project730.model.dto.TaglistDto;

@Mapper
public interface ArticleDao {
	public void createArticle(ArticleDto articledto) throws Exception;
	public List<ArticleDto> searchArticle(TaglistDto taglistdto) throws Exception;
	public int  searchArticlePK(ArticleDto articledto) throws Exception;
	public void updateArticle(ArticleDto articledto) throws Exception;
	public int  searchUpdateArticlePK(ArticleDto articledto) throws Exception;
	public void deleteArticle(int pk_article) throws Exception;
	public ArticleDto detailArticle(int pk_article) throws Exception;
	public void updateHit(int pk_article) throws Exception;

	public void addArticleAlcohol(Article_alcoholDto article_alcoholdto) throws Exception;
	public void addArticleCup(Article_cupDto article_cupdto) throws Exception;
	public void addArticleIngredient(Article_ingredientDto article_ingredientdto) throws Exception;
	public void addArticleTag(int pk_article, int pk_tag) throws Exception;
	public void addRecipe(RecipeDto recipedto) throws Exception;

	public List<Article_alcoholDto> searchArticleAlcohol(int pk_article) throws Exception;
	public List<Article_ingredientDto> searchArticleIngredient(int pk_article) throws Exception;
	public List<Article_tagDto> searchArticleTag(int pk_article) throws Exception;
	public List<RecipeDto> searchRecipe(int pk_article) throws Exception;
	
	public void deleteArticleAlcohol(int pk_article) throws Exception;
	public void deleteArticleCup(int pk_article, int pk_cup) throws Exception;
	public void deleteArticleIngredient(int pk_article) throws Exception;
	public void deleteArticleTag(int pk_article) throws Exception;
	public void deleteRecipe(int pk_article) throws Exception;
}
