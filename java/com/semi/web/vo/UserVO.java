package com.semi.web.vo;

import com.semi.web.util.MyException;

public class UserVO {

	private String name;
	private String id;
	private String pw;
	
	public UserVO(String id, String pw) throws MyException {
		super();
		setId(id);
		setPw(pw);
	}
	
	public UserVO(String name, String id, String pw) throws MyException {
		this(id, pw);
		setName(name);
	}
	
	public UserVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) throws MyException {
		// 유효성 검사
		if(id != null) {
			this.id = id;
		} else {
			throw new MyException("id를 확인해주세요.");
		}
	}
	
	public String getPw() {
		return pw;
	}
	
	public void setPw(String pw) {
		this.pw = pw;
	}

	@Override
	public String toString() {
		return "UserVO [name=" + name + ", id=" + id + ", pw=" + pw + "]";
	}
	
}
