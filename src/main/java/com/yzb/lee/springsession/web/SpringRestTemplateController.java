package com.yzb.lee.springsession.web;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.yzb.lee.springsession.domain.ConsumerLoanInfo;

@RestController
@RequestMapping("/rest")
public class SpringRestTemplateController {
	
	// @RequestBody ConsumerLoanInfo loanInfo可以改写成@RequestBody JSONObject requestBody, JSONObject是一种通用写法， 优先选用前者
	@RequestMapping("/handle")
	String consumerLoanHandle(@RequestBody ConsumerLoanInfo loanInfo) throws InterruptedException {
		System.out.println(JSON.toJSONString(loanInfo,true));
		Thread.sleep(10000);
		return "success";
	}
}
