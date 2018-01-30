package com.yzb.lee.springsession.init;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(value = 1)
public class DaoSomethingAfterAppStart implements CommandLineRunner {

	private static final Logger LOGGER = LoggerFactory.getLogger(DaoSomethingAfterAppStart.class);
	
	@Value("${spring.redis.host}")
	private String redisHost;
	
	@Override
	public void run(String... args) throws Exception {
		LOGGER.info("应用启动之后做某些特定的事; order=1; args是应用启动时附带的参数, redis:{}",redisHost);
	}

}
