package com.bangba.project730.controller;

import java.util.List;
import java.util.Map;

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

import com.bangba.project730.model.dto.FFcommentDto;
import com.bangba.project730.model.dto.ForumDto;
import com.bangba.project730.model.dto.ForumTotalDto;
import com.bangba.project730.model.dto.SearchForumDto;
import com.bangba.project730.model.service.ForumService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@CrossOrigin(origins = { "*" })
@RequestMapping("/forum")
public class ForumController {

    @Autowired
    private ForumService forumService;

    @ApiOperation(value = "자유게시판 생성")
    @PostMapping("/create-forum")
    public String createForum(@RequestBody @ApiParam(value = "자유게시판 하나에 대한 정보", required = true) ForumDto forumDto, Model model) throws Exception {
        try {
            forumService.createForum(forumDto);
            model.addAttribute("msg", "생성 Forum Success");
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("msg", "생성 Forum Internal Server Error!!");
            return "error";
        }
    }

    @ApiOperation(value = "자유게시판 목록 조회")
    @GetMapping("/search-forum-list")
    public List<ForumDto> searchForumList(Model model
            , @RequestParam(required = false, defaultValue = "1") int page_num
            , @RequestParam(required = false, defaultValue = "1") int page_range
            , @RequestParam(required = false, defaultValue = "제목") String search_type
            , @RequestParam(required = false) String keyword
            , @RequestParam(required = false, defaultValue = "15") int forum_cnt_per_page
            ) throws Exception {

        SearchForumDto searchForumDto = new SearchForumDto();
        searchForumDto.setSearch_type(search_type);
        searchForumDto.setKeyword(keyword);

        int forum_total_cnt = forumService.getForumListCnt(searchForumDto);

        searchForumDto.page_info(page_num, page_range, forum_total_cnt, forum_cnt_per_page);
        model.addAttribute("pagination", searchForumDto);
        model.addAttribute("searchForumList", forumService.searchForumList(searchForumDto));
        return forumService.searchForumList(searchForumDto);
    }


    @ApiOperation(value = "자유게시판 상세페이지")
    @GetMapping("/{pk_forum}")
    public ForumDto detailForum(@PathVariable @ApiParam(value = "자유게시판 하나에 대한 상세정보", required = true) int pk_forum) throws Exception {
        forumService.updateHits(pk_forum);
        return forumService.detailForum(pk_forum);
    }


    @ApiOperation(value = "자유게시판 수정")
    @PutMapping("/update-forum")
    public String updateForum(@RequestBody @ApiParam(value = "자유게시판 하나에 대한 수정 정보", required = true) ForumDto forumDto, Model model) throws Exception {
        try {
            forumService.updateForum(forumDto);
            model.addAttribute("msg", "Update Forum Success");
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("msg", "Update Forum Internal Server Error!!");
            return "error";
        }
    }


    @ApiOperation(value = "자유게시판 삭제")
    @DeleteMapping("/delete-forum/{pk_forum}")
    public String deleteForum(@PathVariable @ApiParam(value = "자유게시판 하나 삭제", required = true) int pk_forum) throws Exception {
        try {
            forumService.deleteForum(pk_forum);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }



    @ApiOperation(value = "공지사항 불러오기")
    @GetMapping("/notices")
    public List<ForumDto> searchNotices() throws Exception {
        return forumService.searchNotices();
    }

	@ApiOperation(value = "댓글 작성", response = String.class)
	@PostMapping(value = "/comment/create",  headers = { "Content-type=application/json" })
	public String createComment(@RequestBody Map<String, String> map, Model model) throws Exception {

		try {
			forumService.createComment(map);
			model.addAttribute("msg", "댓글 작성 완료");
			return "main";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "댓글 작성중 문제가 발생했습니다.");
			return "error";
		}
	}

	@ApiOperation(value = "댓글 검색", response = String.class)
    @GetMapping("/comment/keyword/{pk_forum}")
    public List<FFcommentDto> searchComment(@PathVariable int pk_forum, Model model) throws Exception {
        try {
            model.addAttribute("msg", "댓글 검색 완료");
            return forumService.searchComment(pk_forum);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("msg", "댓글 검색중 문제가 발생했습니다.");
        }
        return null;
    }



	@ApiOperation(value = "댓글 수정", response = String.class)
	@PutMapping("/comment/update-comment")
	public String updateComment(@RequestBody Map<String, String> map, Model model) throws Exception {
		try {
			forumService.updateComment(map);
			model.addAttribute("msg", "댓글 수정 완료");
			return "main";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "댓글 수정중 문제가 발생했습니다.");
			return "error";
		}
	}
	@ApiOperation(value = "댓글 삭제", response = String.class)
	@DeleteMapping("/comment/{pk_fcomment}")
	public String deleteComment(@PathVariable @ApiParam(value = "댓글 하나 삭제", required = true) int pk_fcomment, Model model) throws Exception {
		try {
			forumService.deleteComment(pk_fcomment);
			model.addAttribute("msg", "댓글 삭제 완료");
			return "main";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "댓글 삭제중 문제가 발생했습니다.");
			return "error";
		}
	}

    @ApiOperation(value = "자유게시판 전체 게시글 수")
    @GetMapping("/forum_cnt")
    public int getForumListCnt(
    		@RequestParam(required = false, defaultValue = "제목") String search_type
            , @RequestParam(required = false) String keyword,
            SearchForumDto searchForumDto) throws Exception {
    	searchForumDto.setSearch_type(search_type);
        searchForumDto.setKeyword(keyword);
        return forumService.getForumListCnt(searchForumDto);
    }
    
	@ApiOperation(value = "게시판 좋아요", response = String.class)
	@PutMapping("/like")
	public String clickLike(@RequestBody Map<String, String> map) {
		try {
			if(map.get("isclick").equals("off")) {
				forumService.insertLike(Integer.parseInt(map.get("user_no")), Integer.parseInt(map.get("forum_no")));
				forumService.upLike(Integer.parseInt(map.get("user_no")), Integer.parseInt(map.get("forum_no")));
			} else {
				forumService.downLike(Integer.parseInt(map.get("user_no")), Integer.parseInt(map.get("forum_no")));
				forumService.deleteLike(Integer.parseInt(map.get("user_no")), Integer.parseInt(map.get("forum_no")));
				
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
			return forumService.isLike(Integer.parseInt(map.get("user_no")), Integer.parseInt(map.get("forum_no")));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	@ApiOperation(value = "최신 레시피 불러오기")
    @GetMapping("/new")
    public List<ForumTotalDto> searchNewForum() throws Exception {
        return forumService.searchNewForum();
    }
}
