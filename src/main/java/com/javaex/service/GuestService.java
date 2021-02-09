package com.javaex.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.javaex.dao.GuestDao;
import com.javaex.vo.GuestVo;

@Service
public class GuestService {

	@Autowired
	private GuestDao guestDao;
	
	@Autowired
	private GuestVo guestVo;

	SqlSession sqlSession;

	// List
	public List<GuestVo> getList(){
		System.out.println("[guestService] getList()");
		
		return guestDao.selectList();
	}

	// addList (글 저장)
	public int addList(GuestVo guestVo) {

		System.out.println("[guestbookService] add()");
		
		return guestDao.GuestInsert(guestVo);
	}

	// Delete
	public int delete(@PathVariable("password") GuestVo password) {
	GuestDao guestDao = new GuestDao();
	guestDao.guestDelete(password);

	
	return guestDao.guestDelete(guestVo);
			
			}
	
	/* ajax 글저장-->저장된 글 리턴 */
	public GuestVo writeResultVo(GuestVo guestVo) {
		//글저장-->번호
		
		//int no = guestDao.insertSelectKey(guestbookVo);
		
		guestDao.insertSelectKey(guestVo);
		int no =guestVo.getNo(); 
		
		//글 1개 가져오기 
		GuestVo Gvo = guestDao.selectOne(no);
		System.out.println(Gvo);
		return Gvo;
		
	}
	
}
