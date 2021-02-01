package com.javaex.dao;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;

import com.javaex.vo.BoardVo;

@Repository
public class BoardDao {

	@Autowired
	private SqlSession sqlSession;

	@Autowired
	private HttpSession session;

	// http://localhost:8088/mysite5/board/getList
	// 글 리스트
	public List<BoardVo> selectList() {
		System.out.println("boardDao: getList");

		return sqlSession.selectList("board.selectList");
	}

	// http://localhost:8088/mysite5/board/post
	// 글 읽기 --> 1개의 글 읽기 
	public BoardVo selectPost(int no) {
		System.out.println("board dao : selectPost");

		return sqlSession.selectOne("board.selectPost", no);
	}

	// 조회수 +1
	public int updateHit(int no) {
		System.out.println("boardDao : count hit");

		return sqlSession.update("board.updateHit", no);
	}

	//http:localhost:8088/mysite5/board/savePost
	//글 저장
		public int insert(int no) {
			System.out.println("boardDao : save the post");
			
			return sqlSession.insert("board.savePost", no);
		}
		
		

	//http:localhost:8088/mysite5/board/edit
	//글 수정
		public int editPost(BoardVo boardVo) {
			System.out.println("BoadDao: edit the post");
			
			return sqlSession.update("board.edit", boardVo);
		}
		
	//http:localhost:8088/mysite5/board/remove
	//글 삭제
		public int remove(int no) {
			System.out.println("boardDao : remove");
			
			return sqlSession.delete("board.remove", no);
		}

	

		
		


	
		}
