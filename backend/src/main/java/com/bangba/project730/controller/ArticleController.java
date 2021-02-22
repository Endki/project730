package com.bangba.project730.controller;


import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bangba.project730.model.dto.AlcoholDto;
import com.bangba.project730.model.dto.ArticleDto;
import com.bangba.project730.model.dto.ArticleTotalDto;
import com.bangba.project730.model.dto.AtoA;
import com.bangba.project730.model.dto.AtoI;
import com.bangba.project730.model.dto.FAcommentDto;
import com.bangba.project730.model.dto.IngredientDto;
import com.bangba.project730.model.dto.RecipeDto;
import com.bangba.project730.model.dto.TagDto;
import com.bangba.project730.model.service.ArticleService;
import com.bangba.project730.model.service.UserService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/article")
public class ArticleController {
	
	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private UserService userService;
	
	@ApiOperation(value = "레시피 작성", response = String.class)
	@PostMapping(value = "/create",  headers = { "Content-type=application/json" })
	public String createArticle(@RequestBody Map<String, String> map, Model model) throws Exception {
		try {
			articleService.createArticle(map);
			model.addAttribute("msg", "레시피 작성 완료");
			return "main";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "레시피 작성중 문제가 발생했습니다.");
			return "error";
		}
	}

	@ApiOperation(value = "레시피 검색", response = String.class)
	@PostMapping("/keyword")
	public List<ArticleDto> searchArticle(@RequestBody Map<String, String> map , Model model) throws Exception {
		try {
			List<ArticleDto> dto = articleService.searchArticle(map);
			for(ArticleDto a:dto)
				System.out.println(a.getTitle_kor());
			model.addAttribute("msg", "레시피 검색 완료");
			return articleService.searchArticle(map);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "레시피 검색중 문제가 발생했습니다.");
		}
		return null;
	}
	
	@ApiOperation(value = "레시피 수정", response = String.class)
	@PutMapping("/recipe/{pk_article}")
	public String updateArticle(@RequestBody Map<String, String> map, Model model) throws Exception {
		try {
			articleService.updateArticle(map);
			model.addAttribute("msg", "레시피 수정 완료");
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "레시피 수정중 문제가 발생했습니다.");
			return "error";
		}
	}
	@ApiOperation(value = "레시피 삭제", response = String.class)
	@DeleteMapping("/recipe/{pk_article}")
	public String deleteArticle(@PathVariable Integer pk_article, Model model) throws Exception {
		try {
			articleService.deleteArticle(pk_article);
			model.addAttribute("msg", "레시피 삭제 완료");
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "레시피 삭제중 문제가 발생했습니다.");
			return "error";
		}
	}
	@ApiOperation(value = "주류 검색", response = String.class)
	@PostMapping("/alcohol/{searchtxt}")
	public List<AlcoholDto> searchAlcohol(@RequestParam String searchtxt, Model model) throws Exception {
		try {
			model.addAttribute("msg", "주류 검색 완료");
			return articleService.searchAlcohol(searchtxt);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "주류 검색중 문제가 발생했습니다.");
			return null;
		}
	}
	@ApiOperation(value = "재료 추가", response = String.class)
	@PostMapping("/ingredient")
	public String createIngredient(@RequestBody String ingredient, Model model) throws Exception {
		try {
			articleService.createIngredient(ingredient);
			articleService.createTag(ingredient,2);
			model.addAttribute("msg", "재료 추가 완료");
			model.addAttribute("msg", "태그 추가 완료");
			return "main";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "재료 추가중 문제가 발생했습니다.");
			return "error";
		}
	}
	@ApiOperation(value = "재료 검색", response = String.class)
	@GetMapping("/ingredient/{searchtxt}")
	public List<IngredientDto>  searchIngredient(@PathVariable String searchtxt, Model model) throws Exception {
		try {
			model.addAttribute("msg", "재료 검색 완료");
			return articleService.searchIngredient(searchtxt);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "재료 검색중 문제가 발생했습니다.");
			return null;
		}
	}
	@ApiOperation(value = "태그 추가", response = String.class)
	@PostMapping("/submit_tag")
	public String createTag(@RequestBody String tag, Model model) throws Exception {
		try {
			articleService.createTag(tag,3);
			model.addAttribute("msg", "태그 추가 완료");
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "태그 추가중 문제가 발생했습니다.");
			return "error";
		}
	}	
	@ApiOperation(value = "태그 검색", response = String.class)
	@GetMapping("/tag/{searchtxt}")
	public List<TagDto> searchTag(@PathVariable String searchtxt, Model model) throws Exception {
		try {
			model.addAttribute("msg", "태그 검색 완료");
			return articleService.searchTag(searchtxt);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "태그 검색중 문제가 발생했습니다.");
			return null;
		}
	}
	@ApiOperation(value = "레시피 상세", response = String.class)
	@PostMapping("/detail/{pk_article}")
	public Map<String,String> detailArticle(@RequestParam int pk_article, Model model) throws Exception {
		try {
			model.addAttribute("msg", "레시피 상세 검색 완료");
			return articleService.detailArticle(pk_article);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "레시피 상세 검색중 문제가 발생했습니다.");
			return null;
		}
	}

	@ApiOperation(value = "댓글 작성", response = String.class)
	@PostMapping(value = "/comment/create",  headers = { "Content-type=application/json" })
	public String createComment(@RequestBody Map<String, String> map, Model model) throws Exception {
		try {
			articleService.createComment(map);
			model.addAttribute("msg", "댓글 작성 완료");
			return "main";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "댓글 작성중 문제가 발생했습니다.");
			return "error";
		}
	}

	@ApiOperation(value = "댓글 검색", response = String.class)
	@PostMapping("/comment/keyword")
	public List<FAcommentDto> searchComment(@RequestBody int pk_article , Model model) throws Exception {
		try {
			model.addAttribute("msg", "댓글 검색 완료");
			return articleService.searchComment(pk_article);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "댓글 검색중 문제가 발생했습니다.");
		}
		return null;
	}
	
	@ApiOperation(value = "댓글 수정", response = String.class)
	@PutMapping("/comment/{pk_article}")
	public String updateComment(@RequestBody Map<String, String> map, Model model) throws Exception {
		try {
			articleService.updateComment(map);
			model.addAttribute("msg", "레시피 수정 완료");
			return "main";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "레시피 수정중 문제가 발생했습니다.");
			return "error";
		}
	}
	@ApiOperation(value = "댓글 삭제", response = String.class)
	@DeleteMapping("/comment/{pk_article}")
	public String deleteComment(@RequestParam Integer pk_acomment, Model model) throws Exception {
		try {
			articleService.deleteComment(pk_acomment);
			model.addAttribute("msg", "레시피 삭제 완료");
			return "main";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "레시피 삭제중 문제가 발생했습니다.");
			return "error";
		}
	}
	
	@PostMapping("/photo")
    public String upload(@RequestParam("file") MultipartFile file) {
 
        System.out.println("파일 이름 : " + file.getOriginalFilename());
        System.out.println("파일 크기 : " + file.getSize());
        // 데이터 베이스에 정보를 저장하는 건
        // 이렇게 file의 get 메소드를 활용해 필요한 정보들을 가져오고 
        // 그걸 DTO에 담아 insert하면 된다. 
        // 간단한거니 후의 과정은 생략하고 파일로 서버에 저장하는 걸 보자면
 
        try (
                // 맥일 경우
                // FileOutputStream fos = new FileOutputStream("/tmp/" +file.getOriginalFilename());
                // 윈도우일 경우
                FileOutputStream fos = new FileOutputStream("218.154.7.105:0.0/" + file.getOriginalFilename());
                // 파일 저장할 경로 + 파일명을 파라미터로 넣고 fileOutputStream 객체 생성하고
                InputStream is = file.getInputStream();) {
                // file로 부터 inputStream을 가져온다.
        	
	        System.out.println("파일 경로 : C:/Users/Jin/Downloads/USER_SIGNUP, LOGIN, LOGOUT/backend/imgs/" + file.getOriginalFilename());
            int readCount = 0;
            byte[] buffer = new byte[1024];
            // 파일을 읽을 크기 만큼의 buffer를 생성하고
            // ( 보통 1024, 2048, 4096, 8192 와 같이 배수 형식으로 버퍼의 크기를 잡는 것이 일반적이다.)
            
            while ((readCount = is.read(buffer)) != -1) {
                //  파일에서 가져온 fileInputStream을 설정한 크기 (1024byte) 만큼 읽고
                
                fos.write(buffer, 0, readCount);
                // 위에서 생성한 fileOutputStream 객체에 출력하기를 반복한다
            }
        } catch (Exception ex) {
            throw new RuntimeException("file Save Error");
        }
 
        return "uploadok";
    }
	
	@ApiOperation(value = "레시피 검색", response = String.class)
	@PostMapping("/all")
	public List<ArticleTotalDto> searchAll(@RequestBody Map<String, String> map) throws Exception {
		try {
			List<ArticleTotalDto> tdtos = new ArrayList<ArticleTotalDto>();
			List<ArticleDto> adto = articleService.searchArticle(map);
			for(ArticleDto a:adto) {
				ArticleTotalDto tdto = new ArticleTotalDto();
				// 기존 정보
				tdto.setPk_article(a.getPk_article());
				tdto.setUser_no(a.getUser_no());
				tdto.setUser_name(userService.getMyPage(a.getUser_no()).getUser_name());
				tdto.setUser_img(userService.getMyPage(a.getUser_no()).getImg_path());
				tdto.setTitle_kor(a.getTitle_kor());
				tdto.setTitle_eng(a.getTitle_eng());
				tdto.setLike_cnt(a.getLike_cnt());
				tdto.setBookmark_cnt(a.getBookmark_cnt());
				tdto.setHits(a.getHits());
				tdto.setCreated_at(a.getCreated_at());
				tdto.setUpdated_at(a.getUpdated_at());
				tdto.setLike_weekly(a.getLike_weekly());
				tdto.setContent(a.getContent());
				tdto.setImg_path(a.getImg_path());
				tdto.setCategory(a.isCategory());
				tdto.setAbv(a.getAbv());
				tdto.setCup_no(a.getCup_no());
//				System.out.println("img_path: " + a.getImg_path());
				
				// 추가 정보
				String temp = "";
				StringBuilder sb = new StringBuilder();
				List<RecipeDto> recipes = articleService.getRecipe(a.getPk_article());
				for(RecipeDto recipe : recipes) {
					temp += recipe.getContent();
					temp += "<br>";
					sb.append(recipe.getContent()).append("<br>");
				}
				if (temp.length() > 0)
					temp = temp.substring(0, temp.length() - 4);
				tdto.setRecipe(temp);
				List<TagDto> tags = articleService.getTag(a.getPk_article());
				temp = "";
				for(TagDto tag : tags) {
					temp += tag.getContent_kor();
					temp += "<br>";
				}
				if (temp.length() > 0)
				temp = temp.substring(0, temp.length() - 4);
				tdto.setTag(temp);
				// 어려운거 
				temp = "";
				List<AtoA> alcohols = articleService.getAlcohol(a.getPk_article());
				for(AtoA alcohol : alcohols) {
					temp += alcohol.getName_kor();
					temp += "/";
					temp += alcohol.getQuantity();
					temp += "/";
					temp += alcohol.getUnit();
					temp += "<br>";
				}
				if (temp.length() > 0)
				temp = temp.substring(0, temp.length() - 4);
				tdto.setAlcohol(temp);
				temp = "";
				List<AtoI> ingredients = articleService.getIngredient(a.getPk_article());
				for(AtoI ingredient: ingredients) {
					if(ingredient.isType()) { //주재료일때 
						temp += "재료/";
					} else {
						temp += "가니쉬/";
					}
					temp += ingredient.getName_kor();
					temp += "/";
					temp += ingredient.getQuantity();
					temp += "/";
					temp += ingredient.getUnit();
					temp += "<br>";
				}
				if (temp.length() > 0)
				temp = temp.substring(0, temp.length() - 4);
				tdto.setIngredient(temp);
				
				tdtos.add(tdto);
			}
			return tdtos;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@ApiOperation(value = "레시피 좋아요", response = String.class)
	@PutMapping("/like")
	public String clickLike(@RequestBody Map<String, String> map) {
		try {
			if(map.get("isclick").equals("off")) {
				articleService.insertLike(Integer.parseInt(map.get("user_no")), Integer.parseInt(map.get("article_no")));
				articleService.upLike(Integer.parseInt(map.get("user_no")), Integer.parseInt(map.get("article_no")));
			} else {
				articleService.downLike(Integer.parseInt(map.get("user_no")), Integer.parseInt(map.get("article_no")));
				articleService.deleteLike(Integer.parseInt(map.get("user_no")), Integer.parseInt(map.get("article_no")));
			}
			return "success";
		} catch(Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
	
	@ApiOperation(value = "레시피 북마크", response = String.class)
	@PutMapping("/bookmark")
	public String clickBookmark(@RequestBody Map<String, String> map) {
		try {
			if(map.get("isclick").equals("off")) {
				articleService.insertBmark(Integer.parseInt(map.get("user_no")), Integer.parseInt(map.get("article_no")));
				articleService.upBmark(Integer.parseInt(map.get("user_no")), Integer.parseInt(map.get("article_no")));
			} else {
				articleService.downBmark(Integer.parseInt(map.get("user_no")), Integer.parseInt(map.get("article_no")));
				articleService.deleteBmark(Integer.parseInt(map.get("user_no")), Integer.parseInt(map.get("article_no")));
			}
			return "success";
		} catch(Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
	
	@ApiOperation(value = "좋아요 여부")
	@PostMapping("/is-like")
	public int isLike(@RequestBody Map<String, String> map) {
		try {
			return articleService.isLike(Integer.parseInt(map.get("user_no")), Integer.parseInt(map.get("article_no")));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	@ApiOperation(value = "북마크 여부")
	@PostMapping("/is-bmark")
	public int isBmark(@RequestBody Map<String, String> map) {
		try {
			return articleService.isBmark(Integer.parseInt(map.get("user_no")), Integer.parseInt(map.get("article_no")));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	@ApiOperation(value = "베이스 술 정보 불러오기")
    @GetMapping("/base-alcohol")
    public List<AlcoholDto> searchBaseAlcohol() throws Exception {
        return articleService.searchBaseAlcohol();
    }
	@ApiOperation(value = "최신 레시피 불러오기")
    @GetMapping("/new")
    public List<ArticleTotalDto> searchNewArticle() throws Exception {
        return articleService.searchNewArticle();
    }
}