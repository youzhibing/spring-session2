package com.yzb.lee.springsession.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yzb.lee.springsession.service.IRedisDataStructureService;

@RestController
@RequestMapping("/redisDateStructure")
public class RedisDataStructureController {
	
	@Autowired
	private IRedisDataStructureService redisDataStructureService;

	@RequestMapping(value={"/push"}, method=RequestMethod.POST)
	public long push(@RequestParam(required=true) String userId, @RequestParam(required=true) String message) {
		return redisDataStructureService.pushLink(userId, message);
	}
	
	@RequestMapping(value={"/pop"}, method=RequestMethod.POST)
	public String pop(@RequestParam(required=true) String userId) {
		return redisDataStructureService.popLink(userId);
	}
	
	@RequestMapping(value={"/size"}, method=RequestMethod.POST)
	public long size(@RequestParam(required=true) String userId) {
		return redisDataStructureService.sizeLink(userId);
	}
	
	@RequestMapping(value={"/all"}, method=RequestMethod.POST)
	public List<String> all(@RequestParam(required=true) String userId) {
		return redisDataStructureService.allLink(userId);
	}
}
