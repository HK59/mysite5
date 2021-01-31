package com.javaex.service;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;

	SqlSession sqlSession;

	// 회원가입
	public int join(UserVo userVo) {
		int count;
		return userDao.insert(userVo);
	}

	// 로그인
	public String login(UserVo userVo, HttpSession session) {

		UserVo authUser = userDao.selectUser(userVo);
		if (authUser == null) {// 실패했을때
			// 로그인폼 result = fail
			System.out.println("로그인실패");
			return "redirect:/user/loginForm?result=fail";
		} else {// 성공했을때
			System.out.println("로그인 성공-->" + authUser.toString());
			session.setAttribute("authUser", authUser); // 로그인한 정보 header에 표시하기위에 정보 꺼내서야함
			return "redirect:/";
		}

	}

	// 회원정보 수정 폼
	public String modify(HttpSession session, Model model) {
		UserVo authVo = (UserVo) session.getAttribute("authUser");

		model.addAttribute("userVo", userDao.selectOne(authVo.getNo()));

		return "user/modifyForm";
		}


	//회원정보 수정
	public String modifyForm(UserVo userVo, HttpSession session, Model model) {
		UserVo authVo = (UserVo) session.getAttribute("authUser");
		authVo.setName(userVo.getName());
	
		return "redirect:/";
		
	}
	
	//로그아웃
	public String logout(HttpSession session) {
		session.removeAttribute("authUser");
		session.invalidate();
		return "redirect:/";
	}
	}