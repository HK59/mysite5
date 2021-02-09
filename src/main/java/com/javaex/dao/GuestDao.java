package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GuestVo;

@Repository
public class GuestDao {

	@Autowired // 자동으로 연결해 달라고
	private SqlSession sqlSession;

	public List<GuestVo> selectList() {
		System.out.println("[guestDao] selectList()");
		return sqlSession.selectList("guestbook.selectList");
		
		
	}
	
	// 방명록 등록
	public int GuestInsert(GuestVo guestVo) {
		int count = sqlSession.insert("guestbook.guestinsert", guestVo);
		System.out.println("insert");
		return count;
	}
	//방명록 삭제
	 public int guestDelete(GuestVo guestVo) {
		 int count =sqlSession.delete("guestbook.delete", guestVo);
		 return count;
	 
	 }
	 /* 글 저장(selectkey) */
		public void insertSelectKey(GuestVo guestVo) {
			System.out.println("[guestDao] insertSelectKey()");
			
			System.out.println("xml실행전-->" + guestVo);
			sqlSession.insert("guestbook.insertSelectKey", guestVo);
			System.out.println("xml실행후-->" + guestVo);
			
		}
		
		/* 글 1개 가져오기 */
		public GuestVo selectOne(int no) {
			System.out.println("[guestbookDao] selectOne()");
			return sqlSession.selectOne("guestbook.select", no);
		}
		

}
