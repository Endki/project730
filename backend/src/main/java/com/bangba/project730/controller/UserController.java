package com.bangba.project730.controller;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bangba.project730.model.dto.ArticleDto;
import com.bangba.project730.model.dto.ArticleTotalDto;
import com.bangba.project730.model.dto.AtoA;
import com.bangba.project730.model.dto.AtoI;
import com.bangba.project730.model.dto.FollowDetailDto;
import com.bangba.project730.model.dto.FollowDto;
import com.bangba.project730.model.dto.ForumDto;
import com.bangba.project730.model.dto.RecipeDto;
import com.bangba.project730.model.dto.TagDto;
import com.bangba.project730.model.dto.UserDto;
import com.bangba.project730.model.service.ArticleService;
import com.bangba.project730.model.service.FollowService;
import com.bangba.project730.model.service.UserService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@CrossOrigin(origins = { "*" })
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;

	@Autowired
	private FollowService followService;

	@Autowired
	private ArticleService articleService;

	@ApiOperation(value = "회원가입 실행", response = String.class)
	@PostMapping("/join")
	public UserDto createUser(@RequestBody @ApiParam(value = "회원 한 명의 정보를 담는 객체", required = true) UserDto userDto,
			Model model) throws Exception {
		if (userService.isDuplicatedEmail(userDto.getEmail()) != 0
				|| userService.isDuplicatedName(userDto.getUser_name()) != 0) {
			return userService.getMyPage(-1);
		} else {
			try {
				userService.createUser(userDto);
				model.addAttribute("msg", "회원가입 완료");
				return userService.getMyPage(userDto.getPk_user());
			} catch (Exception e) {
				e.printStackTrace();
				model.addAttribute("msg", "회원가입중 문제가 발생했습니다.");

			}
		}
		return userService.getMyPage(-1);
	}

	@ApiOperation(value = "로그인", response = String.class)
	@PostMapping(value = "/login", headers = { "Content-type=application/json" })
	public UserDto login(@RequestBody @ApiParam(value = "로그인 정보를 담는 객체", required = true) Map<String, String> map,
			Model model) {
		try {
			UserDto user = userService.login(map);
			if (user != null) {
				return userService.getMyPage(user.getPk_user());
			} else {
				model.addAttribute("msg", "아이디 또는 비밀번호를 확인 후 로그인해 주세요.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "로그인 중 문제가 발생했습니다.");
		}
		return userService.getMyPage(-1);
	}

	@ApiOperation(value = "로그아웃", response = String.class)
	@GetMapping(value = "/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "SUCCESS";
	}

	@ApiOperation(value = "이메일 중복 확인 및 인증", response = String.class)
	@PostMapping(value = "/join/mail/{toAddress}")
	public String messageSend(@PathVariable @ApiParam(value = "회원가입에 필요한 이메일", required = true) String toAddress,
			Model model) {
		int result = userService.isDuplicatedEmail(toAddress);
		if (result != 0) { // 이미 이메일이 존재할 때
			model.addAttribute("msg", "증복되는 이메일입니다.");
		} else { // 이메일이 존재하지 않을 때
			try {
				String code = makeCode();
				String codeMsg = "인증코드는 [" + code + "] 입니다.";
				userService.sendEmail(toAddress, "방바! 이메일 인증 발송 코드입니다.", codeMsg);
				model.addAttribute("msg", "인증번호가 발송되었습니다.");
				return code;
			} catch (Exception e) {
				e.printStackTrace();
				model.addAttribute("msg", "이메일 인증에 실패했습니다.");
			}
		}
		return "FAIL";
	}

	@ApiOperation(value = "전화번호 중복 확인", response = String.class)
	@PostMapping(value = "/join/phone/{phone_number}")
	public int phoneSend(@PathVariable @ApiParam(value = "회원가입에 필요한 전화번호", required = true) String phone_number) {
		return userService.isDuplicatedPhoneNumber(phone_number);
	}

	@ApiOperation(value = "닉네임 중복 확인", response = String.class)
	@PostMapping(value = "/join/{user_name}")
	public String confirmName(@PathVariable @ApiParam(value = "회원가입에 필요한 닉네임", required = true) String user_name,
			Model model) {
		int result = userService.isDuplicatedName(user_name);

		if (result != 0) { // 이미 사용중인 닉네임일 경우
			model.addAttribute("msg", "이미 사용중인 닉네임입니다.");
			return "FAIL";
		} else {
			model.addAttribute("msg", "사용가능한 닉네임입니다.");
			return "SUCCESS";
		}
	}
	
	@ApiOperation(value = "마이페이지 수정 - 패스워드 일치 확인", response = String.class)
	@PostMapping(value = "/mypage/confirm-pw")
	public String confirmPW(@RequestBody @ApiParam(value="확인할 비밀번호") UserDto userDto) {
		try {
			if(userDto.getPassword().equals(userService.getPW(userDto))) {
				return "SUCCESS";
			} else {
				return "FAIL";
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "FAIL";
	}
	
	@ApiOperation(value = "마이페이지 수정 - 패스워드만 가능", response = String.class)
	@PutMapping(value = "/mypage/pw")
	public UserDto updateMyPage(@RequestBody @ApiParam(value = "회원 한 명의 정보를 담는 객체", required = true) UserDto userDto) {
		userService.updateMyPage(userDto);
		return userService.getMyPage(userDto.getPk_user());
	}
	
	@ApiOperation(value = "마이페이지 수정 - 이미지, 네임 가능", response = String.class)
	@PutMapping(value = "/mypage/name-img")
	public UserDto updateNameImg(@RequestBody @ApiParam(value = "회원 한 명의 정보를 담는 객체", required = true) UserDto userDto) {
		userService.updateNameImg(userDto);
		return userService.getMyPage(userDto.getPk_user());
	}

	@ApiOperation(value = "마이페이지 북마크 한 글", response = String.class)
	@GetMapping(value = "/mypage/bookmark")
	public List<ArticleTotalDto> bookmarkMyPage(@RequestParam(required = false) int pk_user) throws Exception {
		List<ArticleTotalDto> articleTotalDto = userService.bookmarkMyPage(pk_user);
		for (ArticleTotalDto a: articleTotalDto) {
			String tag_tmp = "";
			List<TagDto> tags = articleService.getTag(a.getPk_article());
			for(TagDto t:tags) {
				tag_tmp += t.getContent_kor();
				tag_tmp +="<br>";
			}
			if (tag_tmp.length() > 0)
				tag_tmp = tag_tmp.substring(0, tag_tmp.length() - 4);
			a.setUser_name(userService.getMyPage(a.getUser_no()).getUser_name());
			a.setUser_img(userService.getMyPage(a.getUser_no()).getImg_path());
			a.setTag(tag_tmp);
			
		}
		return articleTotalDto;
	}
	
	@ApiOperation(value = "마이페이지 좋아요 한 글", response = String.class)
	@GetMapping(value = "/mypage/like")
	public List<ArticleTotalDto> likeMyPage(@RequestParam(required = false) int pk_user) throws Exception {
		List<ArticleTotalDto> articleTotalDto = userService.likeMyPage(pk_user);
		for (ArticleTotalDto a: articleTotalDto) {
			String tag_tmp = "";
			List<TagDto> tags = articleService.getTag(a.getPk_article());
			for(TagDto t:tags) {
				tag_tmp += t.getContent_kor();
				tag_tmp +="<br>";
			}
			if (tag_tmp.length() > 0)
				tag_tmp = tag_tmp.substring(0, tag_tmp.length() - 4);
			a.setTag(tag_tmp);
			
		}
		return articleTotalDto;
	}

	@ApiOperation(value = "마이페이지 내가 쓴 레시피", response = String.class)
	@GetMapping(value = "/mypage/article/{pk_user}")
	public List<ArticleTotalDto> articleMyPage(
			@PathVariable @ApiParam(value = "회원 한 명의 정보를 담는 객체", required = true) int pk_user) {
		List<ArticleTotalDto> tdtos = new ArrayList<ArticleTotalDto>();
		try {
			List<ArticleDto> adto = userService.articleMyPage(pk_user);

			for (ArticleDto a : adto) {
				ArticleTotalDto tdto = new ArticleTotalDto();
				// 기존 정보
				tdto.setPk_article(a.getPk_article());
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

				// 추가 정보
				String temp = "";
				StringBuilder sb = new StringBuilder();
				List<RecipeDto> recipes = articleService.getRecipe(a.getPk_article());
				for (RecipeDto recipe : recipes) {
					temp += recipe.getContent();
					temp += "<br>";
					sb.append(recipe.getContent()).append("<br>");
				}
				if (temp.length() > 0)
					temp = temp.substring(0, temp.length() - 4);
				tdto.setRecipe(temp);
				List<TagDto> tags = articleService.getTag(a.getPk_article());
				temp = "";
				for (TagDto tag : tags) {
					temp += tag.getContent_kor();
					temp += "<br>";
				}
				if (temp.length() > 0)
					temp = temp.substring(0, temp.length() - 4);
				tdto.setTag(temp);
				// 어려운거
				temp = "";
				List<AtoA> alcohols = articleService.getAlcohol(a.getPk_article());
				for (AtoA alcohol : alcohols) {
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
				for (AtoI ingredient : ingredients) {
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
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tdtos;
	}

	@ApiOperation(value = "마이페이지 내가 쓴 게시글", response = String.class)
	@GetMapping(value = "/mypage/forum/{pk_user}")
	public List<ForumDto> forumMyPage(
			@PathVariable @ApiParam(value = "회원의 pk", required = true) int pk_user) {

		try {
			return userService.forumMyPage(pk_user);

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@ApiOperation(value = "회원 탈퇴", response = String.class)
	@DeleteMapping(value = "/mypage/{pk_user}")
	public String deleteMyPage(@PathVariable @ApiParam(value = "유저의 pk", required = true) int pk_user, Model model) {
		userService.deleteMyPage(pk_user);
		return "SUCCESS";
	}

	@ApiOperation(value = "비밀번호찾기", response = String.class)
	@PostMapping(value = "/help/{toAddress}")
	public String findPassword(@PathVariable @ApiParam(value = "비밀번호를 찾을 해당 계정의 이메일", required = true) String toAddress,
			Model model) {
		int result = userService.isDuplicatedEmail(toAddress);
		if (result == 0) { // 이메일이 존재하지 않을 때
			model.addAttribute("msg", "회원가입이 되어있지 않은 이메일입니다.");
		} else { // 회원가입된 이메일일 경우
			try {
				String code = makeCode();
				String codeMsg = "인증코드는 [" + code + "] 입니다.";
				userService.sendEmail(toAddress, "방바! 이메일 인증 발송 코드입니다.", codeMsg);
				model.addAttribute("authCode", code);
				model.addAttribute("msg", "인증번호가 발송되었습니다.");
				return code;
			} catch (Exception e) {
				e.printStackTrace();
				model.addAttribute("msg", "이메일 인증에 실패했습니다.");
			}
		}
		return "FAIL";
	}

	@ApiOperation(value = "유저 프로필 저장 - 해당 파일이 저장된 위치를 반환", response = String.class)
	@PostMapping("/mypage/photo")
	public String upload(@RequestParam("file") MultipartFile file) {

		System.out.println("파일 이름 : " + file.getOriginalFilename());
		System.out.println("파일 크기 : " + file.getSize());

		String fileRoot = "/home/ubuntu/imgs/user/" + file.getOriginalFilename();

		try (
				// 윈도우일 경우
				FileOutputStream fos = new FileOutputStream(fileRoot);
				// 파일 저장할 경로 + 파일명을 파라미터로 넣고 fileOutputStream 객체 생성하고
				InputStream is = file.getInputStream();) {
			// file로 부터 inputStream을 가져온다.

			System.out.println("/home/ubuntu/imgs/user/" + file.getOriginalFilename());
			int readCount = 0;
			byte[] buffer = new byte[1024];
			// 파일을 읽을 크기 만큼의 buffer를 생성하고
			// ( 보통 1024, 2048, 4096, 8192 와 같이 배수 형식으로 버퍼의 크기를 잡는 것이 일반적이다.)

			while ((readCount = is.read(buffer)) != -1) {
				// 파일에서 가져온 fileInputStream을 설정한 크기 (1024byte) 만큼 읽고

				fos.write(buffer, 0, readCount);
				// 위에서 생성한 fileOutputStream 객체에 출력하기를 반복한다
			}
			return fileRoot;
		} catch (Exception e) {
			throw new RuntimeException("file Save Error");
		}
	}

//  인증 코드 생성 
	public String makeCode() {
		String temp = "";
		Random rnd = new Random();
		for (int i = 0; i < 12; i++) {
			int rIndex = rnd.nextInt(3);
			switch (rIndex) {
			case 0:
				temp += ((char) ((int) (rnd.nextInt(26)) + 97));
				break;
			case 1:
				temp += ((char) ((int) (rnd.nextInt(26)) + 65));
				break;
			case 2:
				temp += ((rnd.nextInt(10)));
				break;
			}
		}
		return temp;
	}

	@ApiOperation(value = "팔로우 하기", response = String.class)
	@PostMapping(value = "/follow")
	public List<FollowDetailDto> follow(
			@RequestBody @ApiParam(value = "팔로우할 계정의 pk와 본인의 pk를 담은 객체", required = true) FollowDto followDto,
			Model model) {
		try {
			followService.follow(followDto);
			model.addAttribute("msg", "팔로우에 성공했습니다.");
			return followService.selectFollowerList(followDto.getTarget_no());
		} catch (Exception e) {
			model.addAttribute("msg", "팔로우에 실패했습니다.");
		}
		return null;
	}

	@ApiOperation(value = "언팔로우 하기", response = String.class)
	@PostMapping(value = "/unfollow")
	public String unfollow(
			@RequestBody @ApiParam(value = "팔로우할 계정의 pk와 본인의 pk를 담은 객체", required = true) FollowDto followDto,
			Model model) {
		try {
			followService.unfollow(followDto);
			model.addAttribute("msg", "팔로우에 성공했습니다.");
			return "SUCCESS";
		} catch (Exception e) {
			model.addAttribute("msg", "팔로우에 실패했습니다.");
		}
		return "FAIL";
	}

	@ApiOperation(value = "팔로우 여부 확인하기", response = String.class)
	@PostMapping(value = "/isfollow")
	public int isFollow(
			@RequestBody @ApiParam(value = "팔로우할 계정의 pk와 본인의 pk를 담은 객체", required = true) FollowDto followDto,
			Model model) {
		return followService.isFollow(followDto);
	}

	@ApiOperation(value = "팔로워 확인하기", response = String.class)
	@GetMapping(value = "/follow/{user_no}/ec")
	public List<FollowDetailDto> selectFollowerList(
			@PathVariable @ApiParam(value = "본인의 pk", required = true) int user_no, Model model) {
		return followService.selectFollowerList(user_no);
	}

	@ApiOperation(value = "팔로잉 확인하기", response = String.class)
	@GetMapping(value = "/follow/{user_no}/ic")
	public List<FollowDetailDto> selectFollowingList(
			@PathVariable @ApiParam(value = "본인의 pk", required = true) int user_no, Model model) {
		return followService.selectFollowingList(user_no);
	}
	
	@ApiOperation(value = "타인의 유저 정보 가져오기", response = String.class)
	@GetMapping(value = "/mypage")
	public List<UserDto> getUserInfo(@RequestParam(required = false) int pk_user) throws Exception {
		return userService.getUserInfo(pk_user);
	}
	
	@ApiOperation(value = "이름으로 pk찾기", response = String.class)
	@PostMapping(value = "/find-name")
	public int getUserPk(@RequestParam(required = true) String user_name) throws Exception {
		return userService.getUserPk(user_name);
	}
}