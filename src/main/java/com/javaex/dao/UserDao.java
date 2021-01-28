package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;

@Repository 
public class UserDao {
	
	
	@Autowired
	private SqlSession sqlSession;
	//회원가입 ---> 회원정보 저장 
	public int insert(UserVo userVo) {
		System.out.println("user dao insert");
		System.out.println(userVo.toString());
		
		int count = sqlSession.insert("user.insert", userVo);
		
		
		return count;
	}

	//로그인
	public UserVo selectUser(UserVo userVo){
		System.out.println("user dao selectUser");
		System.out.println(userVo.toString());
		
		//UserVo vo = sqlSession.selectOne("user.selectUser", userVo);
		//System.out.println(vo.toString());
		
		//UserVo authUser = sqlSession.selectOne(userVo);
		//System.out.println("controller--->", authUser.toString());
		
		
	return sqlSession.selectOne("user.selectUser", userVo);
		
	}
	
	//수정폼 --> 회원정보 1명 가져오기
		public UserVo selectOne(int no) {
			System.out.println("onemodify" + no);
					
			return sqlSession.selectOne("user.selectOne", no);
		}
		
		//수정
		public void modify(UserVo userVo) {
			System.out.println("modify" + userVo);
			
			sqlSession.update("user.modify", userVo);
		}


}
	










