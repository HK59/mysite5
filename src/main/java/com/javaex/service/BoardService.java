package com.javaex.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;

@Service
public class BoardService {

	@Autowired
	private BoardDao boardDao;
	@Autowired
	private BoardService boardService;
	@Autowired
	private SqlSession sqlSession;
	
	// http://localhost:8088/mysite5/board/list
	// List
	public String getlist(Model model) {
		System.out.println("boardList");
		List<BoardVo> boardList = boardDao.getList();
		model.addAttribute(boardList);
		return "list";
	}

	// http://localhost:8088/mysite5/board/post
	// 글읽기
	public String read(Model model) {
		System.out.println("boardread");
		int no;
		model.addAttribute("post", boardService.read(model));

		return "board/read";
	}

	//http:localhost:8088/mysite5/board/savePost
	// 글 저장
	public BoardVo insert(BoardVo boardVo) {
		System.out.println("boardService: save post");

		int no = 0;
		BoardVo insert = boardDao.selectPost(no);

		return insert;
	}

	//http:localhost:8088/mysite5/board/editForm
	// 글 수정 폼
	public BoardVo editForm(int no) {
		System.out.println("boardService : editForm");

		return boardDao.selectPost(no);
	}

	//http:localhost:8088/mysite5/board/edit
	// 글 수정
	public int updatePost(BoardVo boardVo) {
		System.out.println("boardService : edit the post");

		return boardDao.updateHit(boardVo);
	}

	//http:localhost:8088/mysite5/board/remove
	// 글 삭제
	public int remove(int no) {
		System.out.println("boardService : delete");

		return boardDao.remove(no);
	}
	}