package com.bangba.project730.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.bangba.project730.model.dto.AlcoholDto;
import com.bangba.project730.model.dto.AcommentDto;
import com.bangba.project730.model.dto.ArticleDto;
import com.bangba.project730.model.dto.ArticleTotalDto;
import com.bangba.project730.model.dto.Article_alcoholDto;
import com.bangba.project730.model.dto.Article_cupDto;
import com.bangba.project730.model.dto.Article_ingredientDto;
import com.bangba.project730.model.dto.Article_tagDto;
import com.bangba.project730.model.dto.AtoA;
import com.bangba.project730.model.dto.AtoI;
import com.bangba.project730.model.dto.IngredientDto;
import com.bangba.project730.model.dto.RecipeDto;
import com.bangba.project730.model.dto.TagDto;
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
	
	
	//추가로 구현된 함수
	public List<AtoA> getAlcohol(int pk_article) throws Exception;
	public List<AtoI> getIngredient(int pk_article) throws Exception;
	public List<RecipeDto> getRecipe(int pk_article) throws Exception;
	public List<TagDto> getTag(int pk_article) throws Exception;
	public void createComment(AcommentDto acommentDto) throws Exception;
	public List<AcommentDto> searchComment(int pk_article) throws Exception;
	public void updateComment(AcommentDto acommentDto) throws Exception;
	public void deleteComment(int pk_acomment) throws Exception;

	
	// 좋아요, 북마크 
	public void insertLike(int user_no, int article_no) throws Exception;
	public void deleteLike(int user_no, int article_no) throws Exception;
	public void insertBmark(int user_no, int article_no) throws Exception;
	public void deleteBmark(int user_no, int article_no) throws Exception;
	
	public void upLike(int user_no, int article_no) throws Exception;
	public void downLike(int user_no, int article_no) throws Exception;
	public void upBmark(int user_no, int article_no) throws Exception;
	public void downBmark(int user_no, int article_no) throws Exception;

	public int isLike(int user_no, int article_no) throws Exception;
	public int isBmark(int user_no, int article_no) throws Exception;
	public List<ArticleDto> searchNewArticle() throws Exception;
	
	public List<ArticleDto> recommend(Map<String, String> map) throws Exception; 
}
