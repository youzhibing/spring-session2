package com.yzb.lee.springsession.domain;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("test/test.properties")
@ConfigurationProperties(prefix="domain.test")
public class DomainTest {
	// test.properties中domain.test.name的值会自动注入到name属性中，domain.test.password的值会自动注入到password属性中, @Value都可以省略
	private String name;
	private String password;

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
