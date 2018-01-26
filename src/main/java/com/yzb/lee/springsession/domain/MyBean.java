package com.yzb.lee.springsession.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

@Component
public class MyBean {
	
	// The ApplicationArguments interface provides access to both the raw String[] arguments as well as parsed option and non-option arguments
	@Autowired
	public MyBean(ApplicationArguments args) {
		boolean debug = args.containsOption("debug");
		List<String> files = args.getNonOptionArgs();
		// if run with "--debug logfile.txt" debug=true, files=["logfile.txt"]
		System.out.println(JSON.toJSONString(debug));
		System.out.println(JSON.toJSONString(files, true));
	}
}
