package com.bangba.project730.model.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bangba.project730.model.dao.AlcoholDao;
import com.bangba.project730.model.dao.ArticleDao;
import com.bangba.project730.model.dao.CupDao;
import com.bangba.project730.model.dao.IngredientDao;
import com.bangba.project730.model.dao.TagDao;
import com.bangba.project730.model.dao.UserDao;
import com.bangba.project730.model.dto.AcommentDto;
import com.bangba.project730.model.dto.AlcoholDto;
import com.bangba.project730.model.dto.ArticleDto;
import com.bangba.project730.model.dto.ArticleTotalDto;
import com.bangba.project730.model.dto.Article_alcoholDto;
import com.bangba.project730.model.dto.Article_ingredientDto;
import com.bangba.project730.model.dto.Article_tagDto;
import com.bangba.project730.model.dto.AtoA;
import com.bangba.project730.model.dto.AtoI;
import com.bangba.project730.model.dto.FAcommentDto;
import com.bangba.project730.model.dto.IngredientDto;
import com.bangba.project730.model.dto.RecipeDto;
import com.bangba.project730.model.dto.TagDto;
import com.bangba.project730.model.dto.TaglistDto;

@Service
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	ArticleDao dao;

	@Autowired
	AlcoholDao adao;

	@Autowired
	CupDao cdao;

	@Autowired
	IngredientDao idao;

	@Autowired
	TagDao tdao;

	@Autowired
	UserDao udao;

	@Override
	public void createArticle(Map<String, String> map) throws Exception {
		// TODO Auto-generated method stub

		ArticleDto dto = new ArticleDto();

		// 아티클 생성
		dto.setUser_no(Integer.parseInt(map.get("user_no")));
		dto.setTitle_kor(map.get("title_kor"));
		dto.setTitle_eng(map.get("title_eng"));
		dto.setLike_cnt(0);
		dto.setBookmark_cnt(0);
		dto.setHits(0);
		dto.setCreated_at(map.get("created_at"));
		dto.setUpdated_at(map.get("created_at"));
		dto.setLike_weekly(0);
		dto.setContent(map.get("content"));
		dto.setImg_path(map.get("img_path"));
		if (map.get("category").equals("official"))
			dto.setCategory(true);
		else
			dto.setCategory(false);
		dto.setAbv(Integer.parseInt(map.get("abv")));
		dto.setCup_no(Integer.parseInt(map.get("cup_no")));

		dao.createArticle(dto);
		// 생성된 아티클의 pk를 유저번호, 제목, 시간으로 찾고 그중에 가장 나중에 만들어진 pk를 가져옴
		int pk = dao.searchArticlePK(dto);

		String s = map.get("alcohol");
		String[] ss = s.split("<br>");
		for (String a : ss) {
			if (!a.equals("")) {
				String[] sss = a.split("/");
				int apk = adao.searchAlcoholPK(sss[0]);
				Article_alcoholDto aadto = new Article_alcoholDto();
				aadto.setArticle_no(pk);
				aadto.setAlcohol_no(apk);
				aadto.setQuantity(sss[1]);
				aadto.setUnit(sss[2]);
				dao.addArticleAlcohol(aadto);
				
				int stpk = tdao.searchTagPKCount(sss[0]);
				if(stpk==0)
				{
					TagDto tdto = new TagDto();
					tdto.setContent_kor(sss[0]);
					tdto.setType(1);
					tdao.addTag(tdto);
				}
				int tpk = tdao.searchTagPK(sss[0]);
				dao.addArticleTag(pk, tpk);
			}
		}

		s = map.get("ingredient");
		ss = s.split("<br>");
		for (String a : ss) {
			if (!a.equals("")) {
				String[] sss = a.split("/");
				int sipk = idao.searchIngredientPKCount(sss[1]);
				if(sipk==0)
				{
					IngredientDto idto = new IngredientDto();
					idto.setName_kor(sss[1]);
					idao.addIngredient(idto);
				}
				int ipk = idao.searchIngredientPK(sss[1]);
				Article_ingredientDto aidto = new Article_ingredientDto();
				aidto.setArticle_no(pk);
				aidto.setIngredient_no(ipk);
				if (sss[0].equals("재료"))
					aidto.setType(true);
				else
					aidto.setType(false);
				aidto.setQuantity(sss[2]);
				aidto.setUnit(sss[3]);
				dao.addArticleIngredient(aidto);
				
				int stpk = tdao.searchTagPKCount(sss[1]);
				if(stpk==0)
				{
					TagDto tdto = new TagDto();
					tdto.setContent_kor(sss[1]);
					tdto.setType(2);
					tdao.addTag(tdto);
				}
				int tpk = tdao.searchTagPK(sss[1]);
				dao.addArticleTag(pk, tpk);
			}
		}

		s = map.get("tag");
		ss = s.split("<br>");
		for (String a : ss) {
			if (!a.equals("")) {
				int stpk = tdao.searchTagPKCount(a);
				if(stpk==0)
				{
					TagDto tdto = new TagDto();
					tdto.setContent_kor(a);
					tdto.setType(3);
					tdao.addTag(tdto);
				}
				int tpk = tdao.searchTagPK(a);
				dao.addArticleTag(pk, tpk);
			}
		}

		s = map.get("recipe");
		ss = s.split("<br>");
		int r = 1;
		for (String a : ss) {
			RecipeDto rdto = new RecipeDto();
			rdto.setArticle_no(pk);
			rdto.setContent(a);
			rdto.setNum(r);
			dao.addRecipe(rdto);
			r++;
		}
	}

	@Override
	public List<ArticleDto> searchArticle(Map<String, String> map) throws Exception {
		// TODO Auto-generated method stub
		TaglistDto tldto = new TaglistDto();
		tldto.setSearchtxt(map.get("searchtxt"));
		String s = map.get("tag");
		String[] ss = s.split("<br>");
		int i = 0;
		for (String a : ss) {
			System.out.println(a);
			if (a != "") {
				switch (i) {
				case 0:
					tldto.setTag1(a);
					break;
				case 1:
					tldto.setTag2(a);
					break;
				case 2:
					tldto.setTag3(a);
					break;
				case 3:
					tldto.setTag4(a);
					break;
				case 4:
					tldto.setTag5(a);
					break;
				case 5:
					tldto.setTag6(a);
					break;
				case 6:
					tldto.setTag7(a);
					break;
				case 7:
					tldto.setTag8(a);
					break;
				case 8:
					tldto.setTag9(a);
					break;
				case 9:
					tldto.setTag10(a);
					break;
				default:
					break;
				}
				i++;
			}
		}
		return dao.searchArticle(tldto);
	}

	@Override
	public void updateArticle(Map<String, String> map) throws Exception {
		// TODO Auto-generated method stub

		ArticleDto dto = new ArticleDto();
		// 아티클 생성
		dto.setPk_article(Integer.parseInt(map.get("pk_article")));
		dto.setTitle_kor(map.get("title_kor"));
		dto.setTitle_eng(map.get("title_eng"));
		dto.setUpdated_at(map.get("updated_at"));
		dto.setContent(map.get("content"));
		dto.setImg_path(map.get("img_path"));
		dto.setAbv(Integer.parseInt(map.get("abv")));
		dto.setCup_no(Integer.parseInt(map.get("cup_no")));

		dao.updateArticle(dto);

		int pk = Integer.parseInt(map.get("pk_article"));

		dao.deleteArticleAlcohol(pk);
		dao.deleteArticleIngredient(pk);
		dao.deleteArticleTag(pk);
		dao.deleteRecipe(pk);

		String s = map.get("alcohol");
		String[] ss = s.split("<br>");
		for (String a : ss) {
			if (!a.equals("")) {
				String[] sss = a.split("/");
				int apk = adao.searchAlcoholPK(sss[0]);
				Article_alcoholDto aadto = new Article_alcoholDto();
				aadto.setArticle_no(pk);
				aadto.setAlcohol_no(apk);
				aadto.setQuantity(sss[1]);
				aadto.setUnit(sss[2]);
				dao.addArticleAlcohol(aadto);
				
				int stpk = tdao.searchTagPKCount(sss[0]);
				if(stpk==0)
				{
					TagDto tdto = new TagDto();
					tdto.setContent_kor(sss[0]);
					tdto.setType(1);
					tdao.addTag(tdto);
				}
				int tpk = tdao.searchTagPK(sss[0]);
				dao.addArticleTag(pk, tpk);
			}
		}

		s = map.get("ingredient");
		ss = s.split("<br>");
		for (String a : ss) {
			if (!a.equals("")) {
				String[] sss = a.split("/");
				int sipk = idao.searchIngredientPKCount(sss[1]);
				if(sipk==0)
				{
					IngredientDto idto = new IngredientDto();
					idto.setName_kor(sss[1]);
					idao.addIngredient(idto);
				}
				int ipk = idao.searchIngredientPK(sss[1]);
				Article_ingredientDto aidto = new Article_ingredientDto();
				aidto.setArticle_no(pk);
				aidto.setIngredient_no(ipk);
				if (sss[0].equals("재료"))
					aidto.setType(true);
				else
					aidto.setType(false);
				aidto.setQuantity(sss[2]);
				aidto.setUnit(sss[3]);
				dao.addArticleIngredient(aidto);
				
				int stpk = tdao.searchTagPKCount(sss[1]);
				if(stpk==0)
				{
					TagDto tdto = new TagDto();
					tdto.setContent_kor(sss[1]);
					tdto.setType(2);
					tdao.addTag(tdto);
				}
				int tpk = tdao.searchTagPK(sss[1]);
				dao.addArticleTag(pk, tpk);
			}
		}

		s = map.get("tag");
		ss = s.split("<br>");
		for (String a : ss) {
			if (!a.equals("")) {
				int stpk = tdao.searchTagPKCount(a);
				if(stpk==0)
				{
					TagDto tdto = new TagDto();
					tdto.setContent_kor(a);
					tdto.setType(3);
					tdao.addTag(tdto);
				}
				int tpk = tdao.searchTagPK(a);
				dao.addArticleTag(pk, tpk);
			}
		}

		s = map.get("recipe");
		ss = s.split("<br>");
		int r = 1;
		for (String a : ss) {
			RecipeDto rdto = new RecipeDto();
			rdto.setArticle_no(pk);
			rdto.setContent(a);
			rdto.setNum(r);
			dao.addRecipe(rdto);
			r++;
		}
	}

	@Override
	public void deleteArticle(int pk_article) throws Exception {
		// TODO Auto-generated method stub
		dao.deleteArticle(pk_article);
	}

	@Override
	public Map<String, String> detailArticle(int pk_article) throws Exception {
		// TODO Auto-generated method stub
		dao.updateHit(pk_article);
		ArticleDto dto = dao.detailArticle(pk_article);
		Map<String, String> map = new HashMap<String, String>();
		map.put("pk_article", Integer.toString(dto.getPk_article()));
		map.put("user_no", Integer.toString(dto.getUser_no()));
		map.put("title_kor", dto.getTitle_kor());
		map.put("title_eng", dto.getTitle_eng());
		map.put("like_cnt", Integer.toString(dto.getLike_cnt()));
		map.put("bookmark_cnt", Integer.toString(dto.getBookmark_cnt()));
		map.put("hits", Integer.toString(dto.getHits()));
		map.put("created_at", dto.getCreated_at());
		map.put("updated_at", dto.getUpdated_at());
		map.put("like_weekly", Integer.toString(dto.getLike_weekly()));
		map.put("content", dto.getContent());
		map.put("img_path", dto.getImg_path());
		map.put("category", Boolean.toString(dto.isCategory()));
		map.put("abv", Integer.toString(dto.getAbv()));
		map.put("cup_no", Integer.toString(dto.getCup_no()));
		String a = "";
		List<Article_alcoholDto> laa = dao.searchArticleAlcohol(pk_article);
		int c = 1;
		for (Article_alcoholDto aa : laa) {
			AlcoholDto adto = adao.searchAlcoholbyPK(aa.getAlcohol_no());
			a += adto.getName_kor();
			a += "/";
			a += aa.getQuantity();
			a += "/";
			a += aa.getUnit();
			if (c < laa.size())
				a += "<br>";
			c++;
		}
		map.put("alcohol", a);
		String i = "";
		List<Article_ingredientDto> lai = dao.searchArticleIngredient(pk_article);
		c = 1;
		for (Article_ingredientDto ai : lai) {
			IngredientDto idto = idao.searchIngredientbyPK(ai.getIngredient_no());
			i += idto.getName_kor();
			i += "/";
			i += ai.getQuantity();
			i += "/";
			i += ai.getUnit();
			if (c < lai.size())
				i += "<br>";
			c++;
		}
		map.put("ingredient", i);
		String t = "";
		List<Article_tagDto> lat = dao.searchArticleTag(pk_article);
		c = 1;
		for (Article_tagDto at : lat) {
			TagDto tdto = tdao.searchTagbyPK(at.getTag_no());
			t += tdto.getContent_kor();
			if (c < lat.size())
				t += "<br>";
			c++;
		}
		map.put("tag", t);
		String r = "";
		List<RecipeDto> lr = dao.searchRecipe(pk_article);
		c = 1;
		for (RecipeDto rdto : lr) {
			r += rdto.getContent();
			if (c < lr.size())
				r += "<br>";
			c++;
		}
		map.put("recipe", r);
		return map;
	}

	@Override
	public List<AlcoholDto> searchAlcohol(String searchtxt) throws Exception {
		// TODO Auto-generated method stub
		return adao.searchAlcohol(searchtxt);
	}

	@Override
	public void createIngredient(String name) throws Exception {
		// TODO Auto-generated method stub
		IngredientDto idto = new IngredientDto();
		idto.setName_kor(name);
		idto.setName_eng("");
		idto.setImg_path("");
		idto.setContent("새로추가된 재료");
		idao.addIngredient(idto);
	}

	@Override
	public List<IngredientDto> searchIngredient(String searchtxt) throws Exception {
		// TODO Auto-generated method stub
		return idao.searchIngredient(searchtxt);
	}

	@Override
	public void createTag(String content, int type) throws Exception {
		// TODO Auto-generated method stub
		TagDto tdto = new TagDto();
		tdto.setContent_kor(content);
		tdto.setContent_eng("");
		tdto.setType(type);
		tdao.addTag(tdto);
	}

	@Override
	public List<TagDto> searchTag(String searchtxt) throws Exception {
		// TODO Auto-generated method stub
		return tdao.searchTag(searchtxt);
	}

	// 추가로 구현된 함수

	@Override
	public List<AtoA> getAlcohol(int pk_article) throws Exception {
		return dao.getAlcohol(pk_article);
	}

	@Override
	public List<AtoI> getIngredient(int pk_article) throws Exception {
		return dao.getIngredient(pk_article);
	}

	@Override
	public List<RecipeDto> getRecipe(int pk_article) throws Exception {
		return dao.getRecipe(pk_article);
	}

	@Override
	public List<TagDto> getTag(int pk_article) throws Exception {
		return dao.getTag(pk_article);
	}

	public String createComment(Map<String, String> map) throws Exception {
		// TODO Auto-generated method stub
		AcommentDto acdto = new AcommentDto();
		acdto.setArticle_no(Integer.parseInt(map.get("pk_article")));
		acdto.setUser_no(Integer.parseInt(map.get("user_no")));
		acdto.setContent(map.get("content"));
		dao.createComment(acdto);
		return null;
	}

	@Override
	public List<FAcommentDto> searchComment(int pk_article) throws Exception {
		// TODO Auto-generated method stub
		List<AcommentDto> lacdto = dao.searchComment(pk_article);
		List<FAcommentDto> lfacdto = new ArrayList<FAcommentDto>();
		for (AcommentDto acdto : lacdto) {
			FAcommentDto facdto = new FAcommentDto();
			facdto.setPk_acomment(acdto.getPk_acomment());
			facdto.setArticle_no(acdto.getArticle_no());
			facdto.setUser_name(udao.getUserName(acdto.getUser_no()));
			facdto.setImg_path(udao.getImgPath(acdto.getUser_no()));
			facdto.setContent(acdto.getContent());
			lfacdto.add(facdto);
		}
		return lfacdto;
	}

	@Override
	public String updateComment(Map<String, String> map) throws Exception {
		// TODO Auto-generated method stub
		AcommentDto acdto = new AcommentDto();
		acdto.setPk_acomment(Integer.parseInt(map.get("pk_acomment")));
		acdto.setContent(map.get("content"));
		dao.updateComment(acdto);
		return null;
	}

	@Override
	public String deleteComment(int pk_acomment) throws Exception {
		// TODO Auto-generated method stub
		dao.deleteComment(pk_acomment);
		return null;
	}

	@Override
	public void insertLike(int user_no, int article_no) throws Exception {
		// TODO Auto-generated method stub
		dao.insertLike(user_no, article_no);
	}

	@Override
	public void deleteLike(int user_no, int article_no) throws Exception {
		// TODO Auto-generated method stub
		dao.deleteLike(user_no, article_no);
	}

	@Override
	public void insertBmark(int user_no, int article_no) throws Exception {
		// TODO Auto-generated method stub
		dao.insertBmark(user_no, article_no);
	}

	@Override
	public void deleteBmark(int user_no, int article_no) throws Exception {
		// TODO Auto-generated method stub
		dao.deleteBmark(user_no, article_no);
	}

	@Override
	public void upLike(int user_no, int article_no) throws Exception {
		dao.upLike(user_no, article_no);
	}

	@Override
	public void downLike(int user_no, int article_no) throws Exception {
		dao.downLike(user_no, article_no);
	}

	@Override
	public void upBmark(int user_no, int article_no) throws Exception {
		dao.upBmark(user_no, article_no);
	}

	@Override
	public void downBmark(int user_no, int article_no) throws Exception {
		dao.downBmark(user_no, article_no);
	}

	@Override
	public int isLike(int user_no, int article_no) throws Exception {

		return dao.isLike(user_no, article_no);
	}

	@Override
	public int isBmark(int user_no, int article_no) throws Exception {
		return dao.isBmark(user_no, article_no);
	}

	@Override
	public List<AlcoholDto> searchBaseAlcohol() throws Exception {
		return adao.searchBaseAlcohol();
	}

	@Override
	public List<ArticleTotalDto> searchNewArticle() throws Exception {
		// TODO Auto-generated method stub
		List<ArticleDto> ladto = dao.searchNewArticle();
		List<ArticleTotalDto> latdto = new ArrayList<ArticleTotalDto>();
		for (ArticleDto adto : ladto) {
			ArticleTotalDto atdto = new ArticleTotalDto();
			atdto.setPk_article(adto.getPk_article());
			atdto.setUser_name(udao.getUserName(adto.getUser_no()));
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
			String t = "";
			List<TagDto> ltdto = dao.getTag(adto.getPk_article());
			int i = 1;
			for (TagDto tdto : ltdto) {
				t += tdto.getContent_kor();
				if (i < ltdto.size())
					t += "<br>";
				i++;
			}
			atdto.setTag(t);
			latdto.add(atdto);
		}
		return latdto;
	}

	@Override
	public List<ArticleDto> recommend(Map<String, String> map) throws Exception {
		return dao.recommend(map);
	}

}
