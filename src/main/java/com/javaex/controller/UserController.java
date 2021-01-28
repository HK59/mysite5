package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	private UserDao userDao;

	// 회원가입 폼
	@RequestMapping(value = "/joinForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String joinForm() {
		System.out.println("/user/joinForm");
		return "/user/joinForm";

	}

	// 회원가입
	@RequestMapping(value = "/join", method = { RequestMethod.GET, RequestMethod.POST })
	public String join(@ModelAttribute UserVo userVo) {
		System.out.println("/user/join");
		System.out.println(userVo.toString());

		userDao.insert(userVo);
		return "user/joinOk";

	}

	// 로그인폼
	@RequestMapping(value= "/loginForm", method = {RequestMethod.GET, RequestMethod.POST})
	public String loginForm() {
		System.out.println("/user/loginForm");
		return "/user/loginForm";
		
	//로그인
		@RequestMapping(value="/login" , method= {RequestMethod.GET ,RequestMethod.POST})
		public String login(@ModelAttribute UserVo userVo, HttpSession session) {
			System.out.println("/user/login");		
			System.out.println("login userVo : "+userVo);
			
			UserVo authUser = userDao.selectUser(userVo); //id, password값으로 해당 no,name 불러옴
			//System.out.println("controller-->" + authUser.toString());
			
			if(authUser == null) {//실패했을때
				//로그인폼 result = fail
				System.out.println("로그인실패");
				return "redirect:/user/loginForm?result=fail";
			}else{//성공했을때			
				System.out.println("로그인 성공-->" + authUser.toString());	
				session.setAttribute("authUser", authUser); //로그인한 정보 header에 표시하기위에 정보 꺼내서야함		
				return "redirect:/";			
			}
		}

	// 로그아웃
	@RequestMapping(value = "/logout", method = { RequestMethod.GET, RequestMethod.POST })
	public String logout(HttpSession session) {
		System.out.println("/user/logout");

		session.removeAttribute("authUser");
		session.invalidate();

		return "redirect:/";
	}

	// 회원정보 수정폼
	@RequestMapping(value = "/modifyForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String modifyForm(HttpSession session, Model model) {
		System.out.println("/user/modifyForm");

		UserVo authVo = (UserVo) session.getAttribute("authUser");

		model.addAttribute("userVo", userDao.selectOne(authVo.getNo()));

		return "user/modifyForm";
	}

	// 회원정보 수정
	@RequestMapping(value = "/modify", method = { RequestMethod.GET, RequestMethod.POST })
	public String modify(@ModelAttribute UserVo userVo, HttpSession session) {
		System.out.println("/user/modify");

		userDao.modify(userVo);// 수정
		System.out.println("modify controller userVo :" + userVo);

		// 수정된 값을 다른곳에도 불러와야함 (이름)
		// 세션에 저장된 authUser값을 불러와서 이름만 변경
		UserVo authVo = (UserVo) session.getAttribute("authUser");
		authVo.setName(userVo.getName());

		return "redirect:/"; // 메인으로 갔을때 이름 변경되어야함
	}
}
