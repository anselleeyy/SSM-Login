package com.ansel.service;

import com.ansel.bean.User;

public interface IUserService {
	
	
	public User checkUser(String username);
	
	public int insertNewUser(User user);

}
