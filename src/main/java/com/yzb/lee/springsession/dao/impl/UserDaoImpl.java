package com.yzb.lee.springsession.dao.impl;

import org.springframework.stereotype.Repository;

import com.yzb.lee.springsession.dao.IUserDao;

@Repository
public class UserDaoImpl implements IUserDao {

	@Override
	public String getName() {
		return "name:" + System.currentTimeMillis();
	}

}
