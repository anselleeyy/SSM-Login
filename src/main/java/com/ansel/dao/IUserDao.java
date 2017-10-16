package com.ansel.dao;

import com.ansel.bean.User;

public interface IUserDao {
	
	int insert(User record);

	User selectByPrimaryKey(Integer id);
  
	User checkByUserName(String username);

}