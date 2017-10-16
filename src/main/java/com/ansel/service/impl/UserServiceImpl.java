package com.ansel.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ansel.bean.User;
import com.ansel.dao.IUserDao;
import com.ansel.service.IUserService;

@Service("userService")
public class UserServiceImpl implements IUserService {
	@Resource
	private IUserDao userDao;
	@Override
	public User checkUser(String username) {
		// TODO Auto-generated method stub
		
		return this.userDao.checkByUserName(username);
	}
	@Override
	public int insertNewUser(User user) {
		// TODO Auto-generated method stub
		return this.userDao.insert(user);

	}

}