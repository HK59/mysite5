package com.javaex.service;

import java.util.List;

import javax.servlet.http.HttpSession;

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
	@Autowired //no 값 불러오기 위해 
	private HttpSession session; 
	
	
	// http://localhost:8088/mysite5/board/postedlist
	// 글 리스트
	@SuppressWarnings("unchecked")
	public List<BoardVo> getList(){
		System.out.println("boardService getList");
		
		return boardDao.selectList();
		
	}

	// http://localhost:8088/mysite5/board/read?no=2
	// 글읽기//count 올라가야함
	
	public BoardVo read(int no) {
		System.out.println("Service_boardread");
		BoardVo count = boardDao.selectPost(no);
		boardDao.updateHit(no);
		
		
		
		return count;
		
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

		return boardDao.editPost(boardVo);
	}

	//http:localhost:8088/mysite5/board/remove
	// 글 삭제
	public int remove(int no) {
		System.out.println("boardService : delete");

		return boardDao.remove(no);
	}


	}