package com.yzb.lee.springsession.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.yzb.lee.springsession.service.IRedisDataStructureService;

@Service
public class RedisDataStructureServiceImpl implements IRedisDataStructureService {
	
	@Autowired
	@Qualifier("redisTemplate")					// 这里需要指定名称；spring默认会启动stringRedisTemplate，RedisConfig中配置了redisTemplate， spring不知道在两者之中选择谁
	private RedisTemplate<String, String> template;

	// inject the template as ListOperations
	@Resource(name = "redisTemplate")
	private ListOperations<String, String> listOps;

	@Override
	public long pushLink(String userId, String message) {
		return listOps.leftPush(userId, message);
	}

	@Override
	public String popLink(String userId) {
		return listOps.rightPop(userId);
	}

	@Override
	public long sizeLink(String userId) {
		return listOps.size(userId);
	}

	@Override
	public List<String> allLink(String userId) {
		return listOps.range(userId, 0, listOps.size(userId));
	}
}
