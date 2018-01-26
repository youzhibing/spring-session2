package com.yzb.lee.springsession.init;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(value = 1)
public class DaoSomethingAfterAppStart implements CommandLineRunner {

	//@Value("${spring.redis.host}")
	private String redisHost;
	
	@Override
	public void run(String... args) throws Exception {
		System.out.println("应用启动之后做某些特定的事; order=1; args是应用启动时附带的参数" + ", redis:" + redisHost);
	}

}
