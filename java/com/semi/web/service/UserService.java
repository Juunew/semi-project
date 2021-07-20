package com.semi.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.semi.web.dao.UserDAO;
import com.semi.web.vo.UserVO;

@Service
public class UserService {

	@Autowired
	UserDAO dao;
	
	public void insertUser(UserVO vo) throws Exception {
		dao.insertUser(vo);
	}
	
	public String login(UserVO vo) throws Exception {
		return dao.login(vo);
	}
	
	public void deleteUser(UserVO vo) throws Exception {
		dao.deleteUser(vo);
	}
}
