package com.semi.web.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.semi.web.vo.UserVO;

@Repository
public class UserDAO {

	@Autowired
	SqlSession sqlSession;
	
	public void insertUser(UserVO vo) {
		sqlSession.insert("mapper.user.insertUser", vo);
	}
	
	public String login(UserVO vo) {
		return sqlSession.selectOne("mapper.user.login", vo);
	}
	
	public void deleteUser(UserVO vo) {
		sqlSession.delete("mapper.user.deleteUser", vo);
	}
	
}
