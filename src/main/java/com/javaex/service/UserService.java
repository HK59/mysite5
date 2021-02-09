package com.javaex.service;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	private SqlSession sqlSession;
	
	//회원가입
	public int join(UserVo userVo) {
		System.out.println("uderService join()");
		//서비스가 dao에게 값을 던진다.
		return userDao.insert(userVo);
	}
	
	///회원가입- 아이디체크
	public String idcheck(String id) {
		System.out.println("userService idCheck() " + id);
		UserVo userVo = userDao.selectOne(id);
		
		String result ="";
		
		if(userVo==null) {
			//사용할수있는 id
			result = "can";
		}else {
			//사용할수없는 id
			result = "cant";
		}
		
		return result;
	}
	
	//로그인
	public UserVo login(UserVo userVo) {
		System.out.println("userService login()");
		return userDao.selectUser(userVo);
	}
	//회원정보 수정 폼
	public UserVo modifyForm(int no) {
		System.out.println("uderService modifyForm");
		 
		return userDao.selectOne(no);
	}
	
	//회원정보 수정
	public int modify(UserVo userVo) {
		System.out.println("userService modify");
		//다오에게 값을 넘기고 있다.
		return userDao.modify(userVo);
	}
	
	
	}