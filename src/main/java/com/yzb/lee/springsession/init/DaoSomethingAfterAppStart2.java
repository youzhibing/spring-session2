package com.yzb.lee.springsession.init;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(value = 2)
public class DaoSomethingAfterAppStart2 implements ApplicationRunner {

	private static final Logger LOGGER = LoggerFactory.getLogger(DaoSomethingAfterAppStart2.class);
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		LOGGER.info("应用启动之后做某些特定的事; order=2; args是应用启动时附带的参数");
		
	}

}
