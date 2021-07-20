package com.semi.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.semi.web.service.UserService;
import com.semi.web.util.MyException;
import com.semi.web.vo.UserVO;

@Controller
public class UserController {

	@Autowired
	UserService userService;
	
	@ResponseBody
	@RequestMapping(value = "insertUser.semi", method = {RequestMethod.POST}, produces = "application/text; charset=utf8")
	public String insertUser(@RequestParam("id") String id, @RequestParam("pw") String pw, @RequestParam(value = "name", required = false) String name, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		UserVO vo = new UserVO(id, pw, name);
		
		System.out.println(vo);
		try {
			userService.insertUser(vo);
			return name + "님 회원가입이 완료되었습니다.";
		} catch(Exception e) {
			return e.getMessage();
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "login.semi", method = {RequestMethod.POST}, produces = "application/text; charset=utf8")
	public String login(@RequestParam("id") String id, @RequestParam("pw") String pw, HttpServletRequest request, HttpServletResponse response) throws MyException {
		UserVO vo = new UserVO(id, pw);
		JSONObject json = new JSONObject();
		try {
			System.out.println(vo);
			
			String name = userService.login(vo);
			if(name != null) {
				HttpSession session = request.getSession();
				session.setAttribute("user", vo);
				json.put("name", name);
			} else {
				json.put("msg", "로그인 실패");
			}
		} catch(Exception e) {
			json.put("msg", e.getMessage());
		}
		return json.toJSONString();
	}
	
	@ResponseBody
	@RequestMapping(value = "logout.semi", method = {RequestMethod.POST}, produces = "application/text; charset=utf8")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession(false);
		session.invalidate();
		return "";
	}
	
	@ResponseBody
	@RequestMapping(value = "deleteUser.semi", method= {RequestMethod.POST}, produces = "application/text; charset=utf8")
	public String deleteUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession(false);
		UserVO vo = (UserVO) session.getAttribute("user");
		
		userService.deleteUser(vo);
		session.invalidate();
		return "";
	}
}
