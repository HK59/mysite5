package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javaex.service.GuestService;
import com.javaex.vo.GuestVo;


@Controller
@RequestMapping(value = "/api/guestbook" )
public class ApiGuestbookController {

	@Autowired
	private GuestService guestService;
	
	//글작성(ajax)
	@RequestMapping(value = "/write")
	public String write(@ModelAttribute GuestVo guestVo) {
		System.out.println("[ApiGuestbookController] /write");
		System.out.println(guestVo);
		
		 
		//입력된vo전달하고 저장vo를 받아야함
		guestService.writeResultVo(guestVo);
		
		return "";
	}
	
	
}