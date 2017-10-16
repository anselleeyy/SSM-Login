package com.ansel.bean;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class User implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6551106323558516531L;

	@Size(min = 4, max = 20, message = "用户名长度应该在4-20字符之间")
	private String username;
	
	@Email
	private String email;

	@Pattern(regexp = "^[0-9]{3,14}$", message = "密码长度为3-14，仅包含数字")
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public User(String username, String email, String password) {
		// TODO Auto-generated constructor stub
		this.username = username;
		this.email = email;
		this.password = password;
	}
	
	public User() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString()
	{
		return "[user: " + username + "]";
	}
	
}
