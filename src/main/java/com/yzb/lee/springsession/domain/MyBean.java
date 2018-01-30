package com.yzb.lee.springsession.domain;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

// 一般用不到， 用于接收启动命令带的参数
@Component
public class MyBean {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MyBean.class);
	
	// The ApplicationArguments interface provides access to both the raw String[] arguments as well as parsed option and non-option arguments
	@Autowired
	public MyBean(ApplicationArguments args) {
		boolean debug = args.containsOption("debug");
		List<String> files = args.getNonOptionArgs();
		// if run with "--debug logfile.txt" debug=true, files=["logfile.txt"]
		LOGGER.info("获取命令参数, debug={}", JSON.toJSONString(debug));
		LOGGER.info("获取命令参数, 文件路径数组={}",JSON.toJSONString(files, true));
	}
}
