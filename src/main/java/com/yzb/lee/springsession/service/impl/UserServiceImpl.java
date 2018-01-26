package com.yzb.lee.springsession.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.yzb.lee.springsession.dao.IUserDao;
import com.yzb.lee.springsession.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserDao userDao;
	
	@Override
	@Cacheable(cacheNames = {"name"})
	public String getName() {
		System.out.println("调用了dao层");
		return userDao.getName();
	}

}
