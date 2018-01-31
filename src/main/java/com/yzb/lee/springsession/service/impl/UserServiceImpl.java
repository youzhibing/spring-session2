package com.yzb.lee.springsession.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.yzb.lee.springsession.dao.IUserDao;
import com.yzb.lee.springsession.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private IUserDao userDao;
	
	@Override
	@Cacheable(value="nameCache",key="#id + 'getName'") 
	public String getName(int id) {
		LOGGER.info("调用了dao层");
		return userDao.getName();
	}

}
