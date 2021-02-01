package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.dao.BoardDao;
import com.javaex.service.BoardService;
import com.javaex.vo.BoardVo;

@Controller
@RequestMapping(value = "/board")
public class BoardController {


	
	@Autowired
	private BoardDao boardDao;

	
	@Autowired
	private BoardService boardService;

	// http://localhost:8088/mysite5/board/getList
	// 글 리스트
	@RequestMapping(value = "/getList", method = { RequestMethod.GET, RequestMethod.POST})
	public String getlist(Model model) {
		System.out.println("get_board_List");
		
		//데이터 보내기
		model.addAttribute("boardList", boardService.getList());
		

		return "board/getList";
	}

	// http://localhost:8088/mysite5/board/post
	//글 읽기
	@RequestMapping(value="/read", method= {RequestMethod.GET, RequestMethod.POST})
	public String read(@RequestParam("no") int no, Model model) {
		System.out.println("board_read");
		
		
		return "board/read";
	}

	//http:localhost:8088/mysite5/board/postForm
	//글 쓰기 폼
		@RequestMapping(value="/postForm", method= {RequestMethod.GET, RequestMethod.POST})
		public String postForm() {
			System.out.println("postForm");
			
			return "board/postForm";
		}	
		
	//http:localhost:8088/mysite5/board/savePost
	//글 저장 
		@RequestMapping(value="/savePost", method= {RequestMethod.GET, RequestMethod.POST} )
		public String insert(@ModelAttribute BoardVo boardVo) {
			System.out.println("savePost");
			
			boardService.insert(boardVo);
			
			return "redirect:/board/savePost";
		}
		
	//http:localhost:8088/mysite5/board/editForm
	//글 수정 폼
		@RequestMapping(value="/editForm", method= {RequestMethod.GET, RequestMethod.POST} )
		public String modifyForm(@RequestParam("no") int no, Model model) {
			System.out.println("editForm");
			
			model.addAttribute("post", boardService.editForm(no));
			
			return "board/editForm";
		}
		
	//http:localhost:8088/mysite5/board/edit
	//글 수정
		@RequestMapping(value="edit", method= {RequestMethod.GET, RequestMethod.POST} )
		public String edit(@ModelAttribute BoardVo boardVo) {
			System.out.println("edit");
			
			boardService.updatePost(boardVo);
			
			return "redirect:/board/list";
		}
		
	//http:localhost:8088/mysite5/board/remove
	//글 삭제
		@RequestMapping(value="/remove", method= {RequestMethod.GET, RequestMethod.POST} )
		public String remove(int no) {
			System.out.println("remove");
			
			boardService.remove(no);
			
			return "redirect:/board/postedList";
		}
	}


