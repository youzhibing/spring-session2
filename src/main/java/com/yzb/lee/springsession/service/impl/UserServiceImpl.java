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
	// @CachePut 能够根据方法的请求参数对其结果进行缓存，和 @Cacheable 不同的是，它每次都会触发真实方法的调用
	// @CachEvict 能够根据一定的条件对缓存进行清空
	public String getName(int id) {
		LOGGER.info("调用了dao层");
		return userDao.getName();
	}

}
