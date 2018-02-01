package com.yzb.lee.springsession.service;

import java.util.List;

public interface IRedisDataStructureService {
	
	/**
	 * list 添加元素
	 * @param userId
	 * @param message
	 * @return
	 */
	long pushLink(String userId, String message);
	
	/**
	 * list 移除元素
	 * @param userId
	 * @return
	 */
	String popLink(String userId);
	
	/**
	 * list 元素个数
	 * @param userId
	 * @return
	 */
	long sizeLink(String userId);
	
	/**
	 * 全部元素
	 * @param userId
	 * @return
	 */
	List<String> allLink(String userId);
}
